package Plant;

import java.util.ArrayList;
import java.util.Random;

public class Plant {
    static String[] textures = {"🌱","🌷","🌻","🍀","🌾"};
    private int positionX;
    private int positionY;

    public Plant(int positionX,int positionY) {
        this.positionX=positionX;
        this.positionY=positionY;
    }
    public String getIcon() {return"🌱";}
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
}