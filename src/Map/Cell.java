package Map;

import Animal.Animal;
import Plant.Grass;
import Plant.Plant;


public class Cell {
    private Plant plant;
    private Animal animal;
    private int level;
    private boolean havePlant;
    private boolean haveAnimal;

    public void setPlant(Plant plant) {
        this.plant = plant;
        this.havePlant = true;
    }

    public void delPlant() {
        this.plant = null;
        this.havePlant = false;
    }

    public void setAnimal(Animal animal) {
        System.out.print("\033[" + (animal.getPositionX()+2) + ";" + (animal.getPositionY()*2+1) + "H🐰");
        System.out.flush();
        this.animal = animal;
        this.haveAnimal = true;
    }

    public void delAnimal() {
        System.out.print("\033[" + (animal.getPositionX()+2) + ";" + (animal.getPositionY()*2+1) + "H  ");
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
        if (level == 4 || level == 5) {
            this.setPlant(new Grass('"', 2, 3));
        }

        this.level = level;
    }

    public Plant getPlant() {
        return this.plant;
    }

    public boolean isHaveAnimal() {
        return this.haveAnimal;
    }

    public void setHaveAnimal(boolean haveAnimal) {
        this.haveAnimal = haveAnimal;
    }

    public boolean isHavePlant() {
        return this.havePlant;
    }

    public void setHavePlant(boolean havePlant) {
        this.havePlant = havePlant;
    }
}