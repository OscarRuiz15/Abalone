package abalone;

public class Nodo {
    private int id;
    private int[][]tablero;
    int heuristicacentro;
    int heuristicaborde;
    int heuristicatotal;
    int diferenciafichas;
    private boolean expandido;
    private int idpadre;

    

    public Nodo(int id, int[][] tablero, int heuristicacentro, int heuristicaborde, int heuristicatotal,int diferenciafichas, boolean expandido, int idpadre) {
        this.id = id;
        this.tablero = tablero;
        this.heuristicacentro = heuristicacentro;
        this.heuristicaborde = heuristicaborde;
        this.heuristicatotal = heuristicatotal;
        this.diferenciafichas=diferenciafichas;
        this.expandido = expandido;
        this.idpadre = idpadre;
    
    }

    public int getDiferenciafichas() {
        return diferenciafichas;
    }

    public void setDiferenciafichas(int diferenciafichas) {
        this.diferenciafichas = diferenciafichas;
    }
    

    public int getHeuristicaborde() {
        return heuristicaborde;
    }

    public void setHeuristicaborde(int heuristicaborde) {
        this.heuristicaborde = heuristicaborde;
    }

    public int getHeuristicatotal() {
        return heuristicatotal;
    }

    public void setHeuristicatotal(int heuristicatotal) {
        this.heuristicatotal = heuristicatotal;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public int getHeuristicacentro() {
        return heuristicacentro;
    }

    public void setHeuristicacentro(int heuristicacentro) {
        this.heuristicacentro = heuristicacentro;
    }

    public boolean isExpandido() {
        return expandido;
    }

    public void setExpandido(boolean expandido) {
        this.expandido = expandido;
    }

    public int getIdpadre() {
        return idpadre;
    }

    public void setIdpadre(int idpadre) {
        this.idpadre = idpadre;
    }
    
    
}
