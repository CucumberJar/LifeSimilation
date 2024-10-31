package Map;

import Animal.Animal;
import Plant.Plant;


public class Cell {
    private Plant plant;
    private Animal animal;
    private int level;
    private boolean havePlant;
    private boolean haveAnimal;
    private int positionX;
    private int positionY;

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
        this.havePlant = true;
        System.out.print("\033[" + (plant.getPositionX()+1) + ";" + (plant.getPositionY()*2+1) + "H🌱");
        System.out.flush();

    }

    public void delPlant() {
        System.out.print("\033[" + (plant.getPositionX()+1) + ";" + (plant.getPositionY()*2+1) + "H  ");
        System.out.flush();
        this.plant = null;
        this.havePlant = false;
    }

    public void setAnimal(Animal animal) {
        System.out.print("\033[" + (animal.getPositionX()+1) + ";" + ((animal.getPositionY())*2+1) + "H🐰");
        System.out.flush();
        this.animal = animal;
        this.haveAnimal = true;
    }

    public void delAnimal() {
        System.out.print("\033[" + (animal.getPositionX()+1) + ";" + ((animal.getPositionY())*2+1) + "H  ");
        System.out.flush();
        this.animal = null;
        this.haveAnimal = false;
    }

    public Cell(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
       this.level = level;
    }

    public Plant getPlant() {
        return plant;
    }
    public boolean isHaveAnimal() {
        return this.haveAnimal;
    }
    public boolean isHavePlant() {
        return this.havePlant;
    }


}