package Animal;

public class Animal  {
    int MAX_AGE;
    double speed;
    private int positionX;
    private int positionY;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Animal(int MAX_AGE, double speed, int positionX, int positionY) {
        this.MAX_AGE = MAX_AGE;
        this.speed = speed;
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
