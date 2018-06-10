package abalone;

public class Movimiento {

    public Movimiento() {

    }

    //Recibe el tablero, un contador para saber cuantas empuja, el id de la ficha, la contraria, la posicion x, y
    //n1 y n2 es para saber si es angulo 45, 135 u horizontalmente
    public Integer[] posiblesMovimientos(int tablero[][], int contador, int ficha, int fichacontraria, int x, int y, int n1, int n2) {
        int c = contador;
        Integer[] v = new Integer[5];
        if (x > 10 || y > 18 || x < 0 || y < 0) {
            v[4] = 1;
        } else if (contador > 3) {
            v[4] = 1;
        } else if (tablero[x][y] == 0) {
            v[0] = x;
            v[1] = y;
            v[2] = 0;
            v[3] = 0;
            v[4] = 0;

        } else if (tablero[x][y] == ficha) {
            c++;
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

    private Integer[] posibleEmpuje(int[][] tablero, int contador, int ficha, int fichacontraria, int x, int y, int n1, int n2, int contadorcontrarias, Integer v[]) {

        if ((x >= 10 || y >= 18 || x <= 0 || y <= 0) && contador > contadorcontrarias) {
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

//    public boolean adyacencia135Positivo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
//        boolean res = false;
//        for (int i = 1; i <= n; i++) {
//            if ((tablero[viejox + i][viejoy + i] == 0 && n == i && viejox + i == nuevox && viejoy + i == nuevoy) || tablero[viejox + i][viejoy + i] == 1 || tablero[viejox + i][viejoy + i] == 2) {
//                res = true;
//            } else {
//                res = false;
//                break;
//            }
//
//        }
//        return res;
//
//    }
//
//    public boolean adyacencia45Positivo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
//        boolean res = false;
//        for (int i = 1; i <= n; i++) {
//            if (tablero[viejox - i][viejoy + i] == 0 || tablero[viejox + -i][viejoy + i] == 1 || tablero[viejox + -i][viejoy + i] == 2) {
//                res = true;
//            } else {
//                res = false;
//                break;
//            }
//
//        }
//        return res;
//
//    }
//
//    public boolean adyacenciaXPositivo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
//        return (nuevox == viejox && nuevoy + n * 2 == viejoy);
//
//    }
//
//    public boolean adyacencia135Negativo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
//        return (nuevox - n == viejox && nuevoy - n == viejoy);
//
//    }
//
//    public boolean adyacencia45Negativo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
//        return (nuevox + n == viejox && nuevoy - n == viejoy);
//
//    }
//
//    public boolean adyacenciaXNegativo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
//        return (nuevox == viejox && nuevoy - n * 2 == viejoy);
//
//    }
//
//    public int cantidadRivales45(int xrival, int yrival, int ficha) {
//        int cont = 1;
//
//        while (cont < 3) {
//            if (tablero[xrival - cont][yrival + cont] == ficha || tablero[xrival + cont][yrival - cont] == ficha) {
//                cont++;
//            } else {
//                break;
//            }
//
//        }
//
//        return cont;
//    }
//
//    public int cantidadRivales135(int xrival, int yrival, int ficha) {
//        int cont = 1;
//
//        while (cont < 3) {
//            if (tablero[xrival + cont][yrival + cont] == ficha || tablero[xrival - cont][yrival - cont] == ficha) {
//                cont++;
//            } else {
//                break;
//            }
//
//        }
//
//        return cont;
//    }
//
//    public int cantidadRivalesX(int xrival, int yrival, int ficha) {
//        int cont = 1;
//
//        while (cont < 3) {
//            if (tablero[xrival][yrival + cont * 2] == ficha || tablero[xrival][yrival - cont * 2] == ficha) {
//                cont++;
//            } else {
//                break;
//            }
//
//        }
//
//        return cont;
//    }
//
//    public int[][] moverFicha(int posX, int posY, int presX, int presY, int ficha, int tablero[][]) {
//        tablero[posX][posY] = ficha;
//
//        tablero[presX][presY] = 0;
//        return tablero;
//
//    }
//
//    public int empujarRival(int posX, int posY, int empujaX, int empujaY, int ficha, int fichaenemiga, int tablero[][]) {
//        int sale = 0;
//        if (empujaX <= 0 || empujaX > 17 || empujaY <= 0 || empujaY > 17) {
//            sale = 1;
//            moverFicha(posX, posY, presX, presY, ficha, tablero);
//        } else if (tablero[empujaX][empujaY] != ficha && tablero[posX - 1][posY + 1] > 2) {
//            sale = 1;
//            moverFicha(posX, posY, presX, presY, ficha, tablero);
//        } else if (tablero[empujaX][empujaY] == 0) {
//            moverFicha(empujaX, empujaY, posX, posY, fichaenemiga, tablero);
//            moverFicha(posX, posY, presX, presY, ficha, tablero);
//        }
//        return sale;
//    }
}
