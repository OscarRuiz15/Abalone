package abalone;

import java.util.ArrayList;
import java.util.List;

public class Minimmax {

    private int[][] tablero;
    //Centro
    //(4,4),(4,6),(4,8),(4,10),(4,12),(4,14)
    //(5,5),(5,7),(5,9),(5,11),(5,13)
    //(6,4),(6,6),(6,8),(6,10),(6,12),(6,14)
    private final int[] xcentro = {4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6};
    private final int[] ycentro = {4, 6, 8, 10, 12, 14, 5, 7, 9, 11, 13, 4, 6, 8, 10, 12, 14};
    ////////////////////////////////////////////////////////////////////
    //Borde
    //(1,5),(1,7),(1,9),(1,11),(1,13)
    //(2,4),(2,14)
    //(3,3),(3,15)
    //(4,2),(4,16)
    //(5,1),(5,17)
    //(6,2),(6,16)
    //(7,3),(7,15)
    //(8,4),(8,14)
    //(9,5),(9,7)(9,9),(9,11),(9,13)
    //////////////////////////////////////////////////////////////////

    private final int[] xborde = {1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9};
    private final int[] yborde = {5, 7, 9, 11, 13, 4, 14, 3, 15, 2, 16, 1, 17, 2, 16, 3, 15, 4, 14, 5, 7, 9, 11, 13};
    private int heuristicaactual;
    private int jugadas;
    private int contador = 0;
    private int profundidad = 0;
    private final Movimiento mv = new Movimiento();
    List<Nodo> nodos = new ArrayList<>();
    String jugada;
    private String posiciones[][];

    public Minimmax(int[][] tablero, String posiciones[][]) {
        this.tablero = tablero;
        this.posiciones = posiciones;
    }

    //Recibe el id de la ficha, el id de la ficha contraria, y la cantidad de jugadas a realizar
    public int[][] generarArbol(int ficha, int fichacontraria, int cantidadjugadas) {
        //Inicializa nodo raiz con id 0, el tablero inicial
        //Heuristica centro 0, Heuristica borde 0, Heuristica total 0, 
        //Heuristica Diferencia Fichas 0, Heuristica 3 en linea 0
        //Boolean expandido false, Profundida 0, Jugada vacia, Id del Padre 0
        Nodo n = new Nodo(0, tablero, 0, 0, 0, 0, 0, false, 0, "", 0);
        nodos.add(n);
        heuristicaactual = mejorHeuristica();
        jugadas = cantidadjugadas;

        while (contador < 3) {
            if (contador != 0) {
                int mh = mejorHeuristica();
                int aux = ficha;
                ficha = fichacontraria;
                fichacontraria = aux;
            }
            contador++;
            int limite = nodos.size();
            //Recorrer todos los nodos
            for (int i = 0; i < limite; i++) {
                //Si el nodo no esta expandido, lo expande
                if (!nodos.get(i).isExpandido()) {
                    nodos.get(i).setExpandido(true);
                    if (i == 19) {
                        System.out.println("");
                    }
                    //Obtiene el tablero del nodo expandido
                    int[][] tablero = nodos.get(i).getTablero();

                    //Recorre el tablero
                    for (int j = 0; j < tablero.length; j++) {
                        for (int k = 0; k < tablero[0].length; k++) {
                            //Si la posicion del tablero es igual a la ficha (en la primera iteracion ficha es igual
                            //a la ficha rival (1)
                            if (tablero[j][k] == ficha) {
                                //Verificar movimiento en angulo 45 positivo y crearlos
                                int movimientos[] = posiblesMovimientos(tablero, 0, ficha, fichacontraria, j, k, -1, 1);
                                if (movimientos[4] != 1) {
                                    crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);
                                }

                                //Verificar movimiento en angulo 45 negativo y crearlos
                                movimientos = posiblesMovimientos(tablero, 0, ficha, fichacontraria, j, k, 1, -1);
                                if (movimientos[4] != 1) {
                                    crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);
                                }

                                //Verificar movimiento en angulo 135 negativo y crearlos
                                movimientos = posiblesMovimientos(tablero, 0, ficha, fichacontraria, j, k, 1, 1);
                                if (movimientos[4] != 1) {
                                    crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);
                                }

                                //Verificar movimiento en angulo 135 positivo y crearlos
                                movimientos = posiblesMovimientos(tablero, 0, ficha, fichacontraria, j, k, -1, -1);
                                if (movimientos[4] != 1) {
                                    crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);
                                }

                                //Verificar movimiento en angulo X positivo y crearlos
                                movimientos = posiblesMovimientos(tablero, 0, ficha, fichacontraria, j, k, 0, 2);
                                if (movimientos[4] != 1) {
                                    crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);
                                }

                                //Verificar movimiento en angulo X negativo y crearlos
                                movimientos = posiblesMovimientos(tablero, 0, ficha, fichacontraria, j, k, 0, -2);
                                if (movimientos[4] != 1) {
                                    crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        //Hace el minmax para calcular el tablero (nodo) con menor heuristica y lo retorna para mostrarlo en pantalla
        int[][] t = imprimirTablero();
        return t;
    }

    //Recibe el tablero, un contador para saber cuantas empuja, el id de la ficha, la contraria, la posicion x, y
    //n1 y n2 es para saber si es angulo 45, 135 u horizontalmente
    public int[] posiblesMovimientos(int tablero[][], int contador, int ficha, int fichacontraria, int x, int y, int n1, int n2) {
        int[] v = new int[5];
        if (x > 10 || y > 18 || x < 0 || y < 0) {
            v[4] = 1;
        } else if (contador > 3) {
            v[4] = 1;
        } else if (tablero[x][y] == 0) {
            v[0] = x;
            v[1] = y;
        } else if (tablero[x][y] == ficha) {
            v = posiblesMovimientos(tablero, contador + 1, ficha, fichacontraria, x + n1, y + n2, n1, n2);
        } else if (tablero[x][y] == fichacontraria) {
            int validacion = posibleEmpuje(tablero, contador, ficha, fichacontraria, x + n1, y + n2, n1, n2, 1, v)[4];
            if (validacion != 1) {
                v[0] = x;
                v[1] = y;
            }

        } else {
            v[4] = 1;
        }
        return v;

    }

    private int[] posibleEmpuje(int[][] tablero, int contador, int ficha, int fichacontraria, int x, int y, int n1, int n2, int contadorcontrarias, int v[]) {
        
        if(x > 10 || y > 18 || x < 0 || y < 0){
            v[4] = 1;
        }
        else if ((x >= 10 || y >= 18 || x <= 0 || y <= 0) && contador > contadorcontrarias) {
            v[2] = 0;
            v[3] = 0;
            v[4] = 0;
        } else if (contadorcontrarias == 3) {
            v[4] = 1;
        } else if (tablero[x][y] == fichacontraria) {
            v = posibleEmpuje(tablero, contador, ficha, fichacontraria, x + n1, y + n2, n1, n2, contadorcontrarias + 1, v);
        } else if ((tablero[x][y] == 0) && contador > contadorcontrarias) {
            v[2] = x;
            v[3] = y;
            v[4] = 0;
        } else if (tablero[x][y] > 2 && contador > contadorcontrarias) {
            v[2] = 0;
            v[3] = 0;
            v[4] = 0;
        } else {
            v[4] = 1;
        }
        return v;
    }

    //Calcula el valor de la heuristica centro segun el numero de fichas en el centro
    private int heuristicaCentro(int[][] tablero, int ficha) {
        int cantidad = 0;
        for (int i = 0; i < xcentro.length; i++) {
            if (tablero[xcentro[i]][ycentro[i]] == ficha) {
                cantidad++;
            }
        }
        return cantidad;
    }

    //Calcula el valor de la heuristica borde segun el numero de fichas en el borden
    private int heuristicaBorde(int[][] tablero, int ficha) {
        int cantidad = 0;
        for (int i = 0; i < xborde.length; i++) {
            if (tablero[xborde[i]][yborde[i]] == ficha) {
                cantidad++;
            }

        }
        return cantidad;
    }

    //Calcula el valor de la heuristica diferencia de fichas segun la diferencia de fichas mias y de la maquina
    private int diferenciaFichas(int[][] tablero, int ficha, int fichacontraria) {
        int cantidad = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == ficha) {
                    cantidad++;
                }
                if (tablero[i][j] == fichacontraria) {
                    cantidad--;
                }

            }

        }
        return cantidad;
    }

    //Calcula el valor de la heuristica 3 en linea segun las veces que hayan 3 fichas seguidas
    private int heuristica3EnLinea(int[][] tablero, int ficha) {
        int cantidad = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == ficha) {
                    if (contarFichas(tablero, i, j, ficha, -1, 1, 0)) {
                        cantidad++;
                    }
                    if (contarFichas(tablero, i, j, ficha, 1, 1, 0)) {
                        cantidad++;
                    }
                    if (contarFichas(tablero, i, j, ficha, 0, 2, 0)) {
                        cantidad++;
                    }
                }
            }

        }
        return cantidad;
    }

    //Recibe el tablero del nodo expandido, el id de la ficha, el id de la ficha contraria
    //j hace referencia al valor en la pos x, k hace referencia al valor en la pos y
    //un evector con los movimientos que se pueden hacer y el id del padre
    private void crearMovimientos(int[][] tablero, int ficha, int fichacontraria, int j, int k, int[] movimientos, int idpadre) {
        //Si movimientos[4] es diferente a 1 es porque se pueden hacer movimientos
        if (movimientos[4] != 1) {
            int tab[][] = tablero.clone();

            for (int z = 0; z < tab.length; z++) {
                tab[z] = tablero[z].clone();
            }

            //Si no hay rivales para empujar
            if (movimientos[2] == 0 && movimientos[3] == 0) {
                tab[j][k] = 0;
                tab[movimientos[0]][movimientos[1]] = ficha;
            } //Si se puede empujar un rival
            else {
                tab[j][k] = 0;
                tab[movimientos[0]][movimientos[1]] = ficha;
                tab[movimientos[2]][movimientos[3]] = fichacontraria;
            }

            //Calculo de heuristicas
            int hcentro = (heuristicaCentro(tab, ficha) - heuristicaCentro(tab, fichacontraria)) * 2;
            int hborde = heuristicaBorde(tab, fichacontraria) - heuristicaBorde(tab, ficha);
            int diferencia = diferenciaFichas(tab, ficha, fichacontraria);
            int tresenlinea = heuristica3EnLinea(tab, ficha) - heuristica3EnLinea(tab, fichacontraria);
            
            int mult;
            if (contador % 2 == 0) {
                mult = -1;
            } else {
                mult = 1;
            }
            
            int heuristica = (hcentro + hborde + diferencia + tresenlinea);
            heuristica *= mult;
            String jug = posiciones[j][k] + "-" + posiciones[movimientos[0]][movimientos[1]];
            
            //Crea el nodo con su id, tablero, heuristicas, su profundidad, la jugada que se realizo y el id del padre
            Nodo n = new Nodo(nodos.size(), tab, hcentro, hborde, heuristica, diferencia, tresenlinea, false, contador, jug, idpadre);
            nodos.add(n);
            if (profundidad < contador) {
                profundidad = contador;
            }
        }
    }

    public void verTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] > 2) {
                    System.out.print(" ");
                } else {
                    System.out.print(tablero[i][j]);
                }

            }
            System.out.println("");

        }
        System.out.println("");
    }

    public int mejorHeuristica() {
        int n = -20;
        for (int i = 0; i < nodos.size(); i++) {
            int valor = nodos.get(i).getHeuristicatotal();
            if (valor > n) {
                n = valor;
            }

        }
        return n;
    }

    private boolean contarFichas(int[][] tablero, int i, int j, int ficha, int n1, int n2, int contador) {
        int n = contador;
        boolean v;
        if (n == 3) {
            v = true;
        } else if (i > 0 && j > 0 && i < 11 && j < 17 && tablero[i][j] == ficha) {
            n++;
            v = contarFichas(tablero, i + n1, j + n2, ficha, n1, n2, n);
        } else {
            v = false;
        }
        return v;
    }

    public int[][] imprimirTablero() {
        int[][] t = new int[19][11];

        for (int i = 0; i < profundidad; i++) {
            for (int j = 0; j < nodos.size(); j++) {
                if (nodos.get(j).getProfundidad() == i && nodos.get(j).isExpandido()) {
                    if (i % 2 == 0) {
                        nodos.get(j).setHeuristicatotal(-1000);
                    } else {
                        nodos.get(j).setHeuristicatotal(1000);
                    }
                }
            }
        }
        
        for (int i = profundidad; i >= 0; i--) {
            for (int j = 0; j < nodos.size(); j++) {
                if (nodos.get(j).getProfundidad() == i) {
                    if (i == 1) {
                        System.out.println("");
                    }
                    if (i % 2 == 0) {
                        if (nodos.get(j).getHeuristicatotal() < nodos.get(nodos.get(j).getIdpadre()).getHeuristicatotal()) {
                            nodos.get(nodos.get(j).getIdpadre()).setHeuristicatotal(nodos.get(j).getHeuristicatotal());
                        }
                    } else if (nodos.get(j).getHeuristicatotal() > nodos.get(nodos.get(j).getIdpadre()).getHeuristicatotal()) {
                        nodos.get(nodos.get(j).getIdpadre()).setHeuristicatotal(nodos.get(j).getHeuristicatotal());
                    }

                    System.out.print(nodos.get(j).getId() + " ; " + nodos.get(j).getHeuristicatotal() + " ; " + nodos.get(j).getIdpadre() + " || ");
                }
            }
            
            System.out.println("\n");

        }
        System.out.println("ruta rapida: \nNodo" + nodos.get(0).getId());
        verTablero(nodos.get(0).getTablero());
        System.out.println("Centro: " + nodos.get(0).getHeuristicacentro());
        System.out.println("Borde: " + nodos.get(0).getHeuristicaborde());
        System.out.println("Diferencia: " + nodos.get(0).getDiferenciafichas());
        System.out.println("Cantidad de lineas de 3: " + nodos.get(0).getDiferenciafichas());
        System.out.println("Heuristica: " + nodos.get(0).getHeuristicatotal());
        int papa = 0;
        for (int i = 0; i < profundidad; i++) {

            for (int j = 0; j < nodos.size(); j++) {
                if (nodos.get(j).getProfundidad() == (i + 1) && nodos.get(j).getIdpadre() == nodos.get(papa).getId() && nodos.get(j).getHeuristicatotal() == nodos.get(papa).getHeuristicatotal()) {
                    papa = nodos.get(j).getId();
                    if (nodos.get(j).getProfundidad() == 1) {
                        System.out.println("Esta es la jugada");
                        verTablero(nodos.get(j).getTablero());
                        t = nodos.get(j).getTablero();
                        jugada = nodos.get(j).getJugada();
                    }
                    System.out.println("Nodo:" + nodos.get(j).getId());
                    verTablero(nodos.get(j).getTablero());
                    System.out.println("Centro: " + nodos.get(j).getHeuristicacentro());
                    System.out.println("Borde: " + nodos.get(j).getHeuristicaborde());
                    System.out.println("Diferencia: " + nodos.get(j).getDiferenciafichas());
                    System.out.println("Cantidad de lineas de 3: " + nodos.get(j).getDiferenciafichas());
                    System.out.println("Heuristica: " + nodos.get(j).getHeuristicatotal());
                    break;
                }
            }
        }
        return t;
    }

}
