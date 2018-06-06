package abalone;

public class Movimiento {

    private int[][] tablero;
    private int presX;
    private int presY;

    public Movimiento(int[][] tablero, int presX, int presY) {
        this.tablero = tablero;
        this.presX = presX;
        this.presY = presY;

    }

    public boolean adyacencia135Positivo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
        boolean res = false;
        for (int i = 1; i <= n; i++) {
            if ((tablero[viejox + i][viejoy + i] == 0 && n == i && viejox + i == nuevox && viejoy + i == nuevoy) || tablero[viejox + i][viejoy + i] == 1 || tablero[viejox + i][viejoy + i] == 2) {
                res = true;
            } else {
                res = false;
                break;
            }

        }
        return res;

    }

    public boolean adyacencia45Positivo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
        boolean res = false;
        for (int i = 1; i <= n; i++) {
            if (tablero[viejox - i][viejoy + i] == 0 || tablero[viejox + -i][viejoy + i] == 1 || tablero[viejox + -i][viejoy + i] == 2) {
                res = true;
            } else {
                res = false;
                break;
            }

        }
        return res;

    }

    public boolean adyacenciaXPositivo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
        return (nuevox == viejox && nuevoy + n * 2 == viejoy);

    }

    public boolean adyacencia135Negativo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
        return (nuevox - n == viejox && nuevoy - n == viejoy);

    }

    public boolean adyacencia45Negativo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
        return (nuevox + n == viejox && nuevoy - n == viejoy);

    }

    public boolean adyacenciaXNegativo(int nuevox, int nuevoy, int viejox, int viejoy, int n) {
        return (nuevox == viejox && nuevoy - n * 2 == viejoy);

    }

    public int cantidadRivales45(int xrival, int yrival, int ficha) {
        int cont = 1;

        while (cont < 3) {
            if (tablero[xrival - cont][yrival + cont] == ficha || tablero[xrival + cont][yrival - cont] == ficha) {
                cont++;
            } else {
                break;
            }

        }

        return cont;
    }

    public int cantidadRivales135(int xrival, int yrival, int ficha) {
        int cont = 1;

        while (cont < 3) {
            if (tablero[xrival + cont][yrival + cont] == ficha || tablero[xrival - cont][yrival - cont] == ficha) {
                cont++;
            } else {
                break;
            }

        }

        return cont;
    }

    public int cantidadRivalesX(int xrival, int yrival, int ficha) {
        int cont = 1;

        while (cont < 3) {
            if (tablero[xrival][yrival + cont * 2] == ficha || tablero[xrival][yrival - cont * 2] == ficha) {
                cont++;
            } else {
                break;
            }

        }

        return cont;
    }

    public int[][] moverFicha(int posX, int posY, int presX, int presY, int ficha, int tablero[][]) {
        tablero[posX][posY] = ficha;

        tablero[presX][presY] = 0;
        return tablero;

    }

    public int empujarRival(int posX, int posY, int empujaX, int empujaY, int ficha, int fichaenemiga, int tablero[][]) {
        int sale = 0;
        if (empujaX <= 0 || empujaX > 17 || empujaY <= 0 || empujaY > 17) {
            sale = 1;
            moverFicha(posX, posY, presX, presY, ficha, tablero);
        } else if (tablero[empujaX][empujaY] != ficha && tablero[posX - 1][posY + 1] > 2) {
            sale = 1;
            moverFicha(posX, posY, presX, presY, ficha, tablero);
        } else if (tablero[empujaX][empujaY] == 0) {
            moverFicha(empujaX, empujaY, posX, posY, fichaenemiga, tablero);
            moverFicha(posX, posY, presX, presY, ficha, tablero);
        }
        return sale;
    }

}
