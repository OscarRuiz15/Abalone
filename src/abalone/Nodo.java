package abalone;

public class Nodo {
    private int id;
    private int[][]tablero;
    int heuristicacentro;
    int heuristicaborde;
    int heuristicatotal;
    int diferenciafichas;
    int tresenlinea;
    private boolean expandido;
    private int profundidad;
    private String jugada;
    private int idpadre;

    

    public Nodo(int id, int[][] tablero, int heuristicacentro, int heuristicaborde, int heuristicatotal,int diferenciafichas,int tresenlinea, boolean expandido,int profundidad,String jugada, int idpadre) {
        this.id = id;
        this.tablero = tablero;
        this.heuristicacentro = heuristicacentro;
        this.heuristicaborde = heuristicaborde;
        this.heuristicatotal = heuristicatotal;
        this.diferenciafichas=diferenciafichas;
        this.tresenlinea=tresenlinea;
        this.expandido = expandido;
        this.profundidad=profundidad;
        this.jugada=jugada;
        this.idpadre = idpadre;
    
    }

    public String getJugada() {
        return jugada;
    }

    public void setJugada(String jugada) {
        this.jugada = jugada;
    }
    

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }
    

    public int getTresenlinea() {
        return tresenlinea;
    }

    public void setTresenlinea(int tresenlinea) {
        this.tresenlinea = tresenlinea;
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
