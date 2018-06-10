package abalone;

public class Posiciones {

    String[][] posiciones;
    private int[][] tablero;

    public Posiciones(int[][] tablero) {

        this.tablero = tablero;
        posiciones = new String[tablero.length][tablero[0].length];

    }

    public String[][] coordenadasPosiciones() {
        
        for (int i = 0; i < tablero[0].length; i++) {
            String cadena = "";
            switch (i) {
                case 1:
                    cadena += "a";
                    break;
                case 2:
                    cadena += "b";
                    break;
                case 3:
                    cadena += "c";
                    break;
                case 4:
                    cadena += "d";
                    break;
                case 5:
                    cadena += "e";
                    break;
                case 6:
                    cadena += "f";
                    break;
                case 7:
                    cadena += "g";
                    break;
                case 8:
                    cadena += "h";
                    break;
                case 9:
                    cadena += "i";
                    break;
                case 10:
                    cadena += "j";
                    break;
                case 11:
                    cadena += "k";
                    break;
                case 12:
                    cadena += "l";
                    break;
                case 13:
                    cadena += "m";
                    break;
                case 14:
                    cadena += "n";
                    break;
                case 15:
                    cadena += "o";
                    break;
                case 16:
                    cadena += "p";
                    break;
                case 17:
                    cadena += "q";
                    break;
                default:
                    break;
            }
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[j][i] < 3) {
                    String aux=cadena;
                    cadena+=j;
                    posiciones[j][i]=cadena;
                    System.out.println(cadena);
                    cadena=aux;
                }

            }

        }
        return posiciones;
    }

}
