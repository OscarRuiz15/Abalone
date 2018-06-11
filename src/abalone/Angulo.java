package abalone;

public class Angulo {

    private int angulo45;
    private int angulo135;

    public Angulo() {
    }

    public int getAngulo45(int x, int y) {
        angulo45 = x + y - 1;
        return angulo45;
    }

    public void setAngulo45(int angulo45) {
        this.angulo45 = angulo45;
    }

    public int getAngulo135(int x, int y) {
        angulo135 = x - y + 1;
        return angulo135;
    }

    public void setAngulo135(int angulo135) {
        this.angulo135 = angulo135;
    }

}
