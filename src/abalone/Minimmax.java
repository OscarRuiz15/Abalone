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
    List<Nodo> nodos = new ArrayList<>();

    public Minimmax(int[][] tablero) {
        this.tablero = tablero;

    }

    public void generarArbol(int ficha, int fichacontraria) {

        Nodo n = new Nodo(0, tablero, 0, 0, 0, 0, false, 0);
        nodos.add(n);
        int contador = 0;
        while (contador < 3) {
            int limite = nodos.size();
            for (int i = 0; i < limite; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    for (int k = 0; k < tablero[0].length; k++) {
                        if (tablero[j][k] == ficha) {

                            int movimientos[] = posiblesMovimientos(tablero, 1, ficha, fichacontraria, j, k, -1, 1);

                            //Movimiento 45positivo
                            if (movimientos[4] != 1) {
                                crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);
                            }

                            //Movimiento 45negativo
                            movimientos = posiblesMovimientos(tablero, 1, ficha, fichacontraria, j, k, 1, -1);
                            if (movimientos[4] != 1) {
                                crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);

                            }
                            //Movimiento 135positivo
                            movimientos = posiblesMovimientos(tablero, 1, ficha, fichacontraria, j, k, 1, 1);
                            if (movimientos[4] != 1) {
                                crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);

                            }
                            //Movimiento 135negativo
                            movimientos = posiblesMovimientos(tablero, 1, ficha, fichacontraria, j, k, -1, -1);
                            if (movimientos[4] != 1) {
                                crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);

                            }
                            //Movimiento Xpositivo
                            movimientos = posiblesMovimientos(tablero, 1, ficha, fichacontraria, j, k, 0, 2);
                            if (movimientos[4] != 1) {
                                crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);

                            }
                            //Movimiento Xnegativo
                            movimientos = posiblesMovimientos(tablero, 1, ficha, fichacontraria, j, k, 0, -2);
                            if (movimientos[4] != 1) {
                                crearMovimientos(tablero, ficha, fichacontraria, j, k, movimientos, i);

                            }

                        }

                    }

                }

            }
            contador++;
        }
    }

    //Recibe el tablero, un contador para saber cuantas empuja, el id de la ficha, la contraria, la posicion x, y
    //n1 y n2 es para saber si es angulo 45, 135 u horizontalmente
    public int[] posiblesMovimientos(int tablero[][], int contador, int ficha, int fichacontraria, int x, int y, int n1, int n2) {
        int[] v = new int[5];
        if (contador > 3) {
            v[4] = 1;
        } else if (tablero[x][y] == 0) {
            v[0] = x;
            v[1] = y;
        } else if (tablero[x][y] == ficha) {
            v = posiblesMovimientos(tablero, contador++, ficha, fichacontraria, x + n1, y + n2, n1, n2);
        } else if (tablero[x][y] == fichacontraria) {
            v = posibleEmpuje(tablero, contador, ficha, fichacontraria, x + n1, y + n2, n1, n2, 0, v);
        } else {
            v[4] = 1;
        }

        return v;

    }

    private int[] posibleEmpuje(int[][] tablero, int contador, int ficha, int fichacontraria, int x, int y, int n1, int n2, int contadorcontrarias, int v[]) {

        if (contadorcontrarias == 3) {
            v[4] = 1;
        } else if (tablero[x][y] == fichacontraria) {
            v = posibleEmpuje(tablero, contador, ficha, fichacontraria, x + n1, y + n2, n1, n2, contadorcontrarias++, v);
        } else if ((tablero[x][y] == 0 || tablero[x][y] > 2) && contador > contadorcontrarias) {
            v[2] = x;
            v[3] = y;
        } else {
            v[4] = 1;
        }

        return v;
    }

    private int heuristicaCentro(int[][] tablero, int ficha) {
        int cantidad = 0;
        for (int i = 0; i < xcentro.length; i++) {
            if (tablero[xcentro[i]][ycentro[i]] == ficha) {
                cantidad++;
            }

        }
        return cantidad;
    }

    private int heuristicaBorde(int[][] tablero, int ficha) {
        int cantidad = 0;
        for (int i = 0; i < xborde.length; i++) {
            if (tablero[xborde[i]][yborde[i]] == ficha) {
                cantidad++;
            }

        }
        return cantidad;
    }

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

    /*private int heuristica3EnLinea(int[][] tablero, int ficha,int contador){
        int cantidad=0;
        if (contador==3) {
            cantidad++;
        }else{
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {
                    
                    
                }
                
            }
        }
        
        return cantidad;
    }*/
    private void crearMovimientos(int[][] tablero, int ficha, int fichacontraria, int j, int k, int[] movimientos, int idpadre) {
        if (movimientos[4] != 1) {
            int tab[][] = tablero.clone();

            for (int z = 0; z < tab.length; z++) {
                tab[z] = tablero[z].clone();

            }
            if (movimientos[2] == 0 && movimientos[3] == 0) {
                tab[j][k] = 0;
                tab[movimientos[0]][movimientos[1]] = ficha;
            } else {
                tab[j][k] = 0;
                tab[movimientos[0]][movimientos[1]] = ficha;
                tab[movimientos[2]][movimientos[3]] = ficha;
            }
            int hcentro = (heuristicaCentro(tab, ficha) - heuristicaCentro(tab, fichacontraria)) * 2;
            int hborde = heuristicaBorde(tab, ficha) - heuristicaBorde(tab, fichacontraria);
            int diferencia = diferenciaFichas(tab, ficha, fichacontraria);

            int heuristica = hcentro + hborde + diferencia;
            verTablero(tab);
            Nodo n = new Nodo(nodos.size(), tab, hcentro, hborde, heuristica, diferencia, false, idpadre);
            nodos.add(n);

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

}