package abalone;

public class Movimiento {

    public Movimiento() {

    }

    //METODO POSIBLES MOVIMIENTOS SOLO TIENE EN CUENTA LOS DOS PRIMEROS VALORES DEL VECTOR, INDICANDO EL X,Y A DONDE SE PODRIA MOVER
    //METODO POSIBLE EMPUJE SOLO TIENE EN CUENTA LOS DOS SIGUIENTES VALORES DEL VECTOR, INDICANDO EL X,Y DEL RIVAL QUE PODRIA EMPUJAR
    
    //Entonces, el vector V que retorna esta clase contiene:
    //v[0]-> Posicion X a donde se puede mover
    //v[1]-> Posicion Y a donde se puede mover
    //v[2]-> Posicion X del rival que podria empujar
    //v[3]-> Posicion Y del rival que podria empujar
    //v[4]-> 0 si es valido, 1 si no es valido 
    
    
    //Recibe el tablero, un contador para saber cuantas empuja, el id de la ficha, el id de la ficha contraria, la posicion x, y
    //n1 y n2 es para saber si es angulo 45, 135 u horizontalmente
    public Integer[] posiblesMovimientos(int tablero[][], int contador, int ficha, int fichacontraria, int x, int y, int n1, int n2) {
        int c = contador;
        Integer[] v = new Integer[5];
        //Si se desborda entonces no hay movimientos
        if (x > 10 || y > 18 || x < 0 || y < 0) {
            v[4] = 1;
        } //Si el contador de empuje es mayor a 3 entonces no hay movimientos
        else if (contador > 3) {
            v[4] = 1;
        } //Si el valor en la posicion es 0 entonces se guarda a donde se puede mover
        else if (tablero[x][y] == 0) {
            v[0] = x;
            v[1] = y;
            v[2] = 0;
            v[3] = 0;
            v[4] = 0;
        } //Si el valor en la posicion es igual a la ficha entonces sigue buscando movimientos
        else if (tablero[x][y] == ficha) {
            c++;
            v = posiblesMovimientos(tablero, contador + 1, ficha, fichacontraria, x + n1, y + n2, n1, n2);
        } //Si el valor en la posicion es igual a la ficha rival, valida si se puede empujar
        else if (tablero[x][y] == fichacontraria) {
            int validacion = posibleEmpuje(tablero, contador, ficha, fichacontraria, x + n1, y + n2, n1, n2, 1, v)[4];
            //Si validacion es diferente de 0, entonces se guarda a donde se puede mover
            if (validacion != 1) {
                v[0] = x;
                v[1] = y;
            }

        } //Si nada de lo anterior se cumple, se guarda un 1 (no es posible movimientos en el angulo recibido)
        else {
            v[4] = 1;
        }

        return v;

    }

    private Integer[] posibleEmpuje(int[][] tablero, int contador, int ficha, int fichacontraria, int x, int y, int n1, int n2, int contadorcontrarias, Integer v[]) {
        //Si se encuentra en un borde
        if ((x >= 10 || y >= 18 || x <= 0 || y <= 0) && contador > contadorcontrarias) {
            v[2] = 0;
            v[3] = 0;
            v[4] = 0;
        } //Si el contador de fichas rivales es 3, no se puede empujar
        else if (contadorcontrarias == 3) {
            v[4] = 1;
        } //Si el valor en la posicion es igual al rival, sigue buscando posibles empujes
        else if (tablero[x][y] == fichacontraria) {
            v = posibleEmpuje(tablero, contador, ficha, fichacontraria, x + n1, y + n2, n1, n2, contadorcontrarias + 1, v);
        } //Si el valor en la posicion es igual a 0 y el contador recibido es mayor al contador de rivales
        //Guarda el valor x, y del que puede empujar
        else if ((tablero[x][y] == 0) && contador > contadorcontrarias) {
            v[2] = x;
            v[3] = y;
            v[4] = 0;
        } //Si el valor en la posicion es >2 (no es posible) y el contador recibido es mayor al contador de rivales
        //Guarda el valor x, y 0 porque no se puede empujar a nadie
        else if (tablero[x][y] > 2 && contador > contadorcontrarias) {
            v[2] = 0;
            v[3] = 0;
            v[4] = 0;
        } //Si nada de lo anterior se cumple, se guarda un 1 (no es posible movimientos en el angulo recibido)
        else {
            v[4] = 1;
        }

        return v;
    }

}
