package Animal;
import Map.Cell;

import java.util.ArrayList;
import java.util.Random;

public class Rabbit extends Animal {
    private int energy=10;
    private String icon="🐰";
    private static final int ENERGY_TO_REPRODUCE = 10;
    private static final int ENERGY_GAIN_FROM_GRASS = 1;
    private static   ArrayList<Rabbit> rabbits = new ArrayList<>();
    public Rabbit(int MAX_AGE, double speed, int positionX, int positionY) {
        super(MAX_AGE, speed, positionX, positionY);
      //  System.out.println("**/*/*/*//");
       rabbits.add(this);
    }

    public void moveAndEat(Cell[][] map) {
        Random random = new Random();
        Cell targetCell = map[getPositionX()][getPositionY()];
        if (targetCell.isHaveAnimal()){targetCell.delAnimal();
            //if (canReproduce())reproduce(10,2.0,getPositionX(),getPositionY());
        }

        switch (random.nextInt(4)){
            case 0:
                if (getPositionY()-1>=0&& !map[getPositionX()][getPositionY()-1].isHaveAnimal()) {
                    setPositionY(getPositionY()-1);
                    targetCell = map[getPositionX()][getPositionY()];
                    targetCell.setAnimal(this);

                }
                break;
            case 1:
                if (getPositionY()+1<=12&& !map[getPositionX()][getPositionY()+1].isHaveAnimal()) {
                    setPositionY(getPositionY()+1);
                    targetCell = map[getPositionX()][getPositionY()];
                    targetCell.setAnimal(this);

                }
                break;
            case 2:
                if (getPositionX()-1>=0&& !map[getPositionX()-1][getPositionY()].isHaveAnimal()) {
                    setPositionX(getPositionX()-1);
                    targetCell = map[getPositionX()][getPositionY()];
                    targetCell.setAnimal(this);

                }
                break;
            case 3:
                if (getPositionX()+1<=12&& !map[getPositionX()+1][getPositionY()].isHaveAnimal()) {
                    setPositionX(getPositionX()+1);
                    targetCell = map[getPositionX()][getPositionY()];
                    targetCell.setAnimal(this);

                }
                break;
        }

            if (targetCell.isHavePlant()) {
                targetCell.getPlant(); // Заяц съедает траву
               // System.out.println(energy);
                energy += ENERGY_GAIN_FROM_GRASS;

                targetCell.delPlant(); // Удаляем траву после поедания
            } else {
                energy--; // Теряет энергию, если травы нет
            }

    }

    public  ArrayList<Rabbit> getRabbits() {
        return rabbits;
    }

    public String getIcon() {
        return icon; // Иконка зайца
    }

    public boolean canReproduce() {
        return energy >= ENERGY_TO_REPRODUCE;
    }

    public Rabbit reproduce(int MAX_AGE, double speed, int positionX, int positionY) {
        if (canReproduce()) {
            energy /= 2; // Делим энергию между собой и потомком

            return new Rabbit(MAX_AGE,speed,positionX,positionY);
        }
        return null;
    }
}