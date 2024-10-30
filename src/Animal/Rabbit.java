package Animal;
import Map.Cell;

import java.util.ArrayList;
import java.util.Random;

public class Rabbit extends Animal {
    private static final int MAX_LIFE_ENERGY =30;
    private int energy=0;
    private String icon="🐰";
    private static final int ENERGY_TO_REPRODUCE = 10;
    private static final int ENERGY_GAIN_FROM_GRASS = 1;
    private int LIFE_ENERGY = 20;

    private static   ArrayList<Rabbit> rabbits = new ArrayList<>();
    public Rabbit(int MAX_AGE, double speed, int positionX, int positionY) {
        super(MAX_AGE, speed, positionX, positionY);
      //  System.out.println("**/*/*/*//");
       rabbits.add(this);
    }

    public void moveAndEat(Cell[][] map) {
       // map[getPositionX()][getPositionY()].setAnimal(this);
        int targetX = -1;
        int targetY = -1;
        int minDistance = Integer.MAX_VALUE;
        int searchRadius = 10; // Радиус поиска травы
        for (int x = Math.max(0, getPositionX() - searchRadius); x <= Math.min(map.length - 1, getPositionX() + searchRadius); x++) {
            for (int y = Math.max(0, getPositionY() - searchRadius); y <= Math.min(map[x].length - 1, getPositionY() + searchRadius); y++) {
                if (map[x][y].isHavePlant()) {
                    int distance = Math.abs(getPositionX() - x) + Math.abs(getPositionY() - y);
                    if (distance < minDistance) {
                        minDistance = distance;
                        targetX = x;
                        targetY = y;
                    }
                }
            }
        }
        if ( map[getPositionX()][getPositionY()].isHaveAnimal()) map[getPositionX()][getPositionY()].delAnimal();
        if (targetX != -1 && targetY != -1) {
            moveToGrass(targetX, targetY, map);
        } else {
            moveRandomly(map);
        }
        map[getPositionX()][getPositionY()].setAnimal(this);
        if (canReproduce()){
            reproduce(10,2.0,getPositionX(),getPositionY());
        }
        // Проверка энергии и удаление зайца
        if (LIFE_ENERGY <= 0) {
            Cell currentCell = map[getPositionX()][getPositionY()];
            rabbits.remove(this); // Удаляем зайца из коллекции
            currentCell.delAnimal(); // Удаляем зайца с клетки карты
        }
    }

    private void moveToGrass(int targetX, int targetY, Cell[][] map) {
        Cell currentCell = map[getPositionX()][getPositionY()];

        // Движение к траве
        if (getPositionX() < targetX && getPositionX() + 1 < map.length && !map[getPositionX() + 1][getPositionY()].isHaveAnimal()) {
            setPositionX(getPositionX() + 1);
        } else if (getPositionX() > targetX && getPositionX() - 1 >= 0 && !map[getPositionX() - 1][getPositionY()].isHaveAnimal()) {
            setPositionX(getPositionX() - 1);
        } else if (getPositionY() < targetY && getPositionY() + 1 < map[0].length && !map[getPositionX()][getPositionY() + 1].isHaveAnimal()) {
            setPositionY(getPositionY() + 1);
        } else if (getPositionY() > targetY && getPositionY() - 1 >= 0 && !map[getPositionX()][getPositionY() - 1].isHaveAnimal()) {
            setPositionY(getPositionY() - 1);
        }

        // Логика поедания
        Cell targetCell = map[getPositionX()][getPositionY()];
        if (targetCell.isHavePlant()) {
            targetCell.getPlant(); // Заяц ест траву
            energy += ENERGY_GAIN_FROM_GRASS; // Восстановление энергии
            LIFE_ENERGY = Math.min(LIFE_ENERGY + ENERGY_GAIN_FROM_GRASS, MAX_LIFE_ENERGY); // Ограничение максимальной энергии
            targetCell.delPlant(); // Удаляем траву после поедания
        } else {
            LIFE_ENERGY--; // Заяц теряет энергию, если травы нет
        }
    }

    private void moveRandomly(Cell[][] map) {
        Random random = new Random();
        int direction = random.nextInt(4); // Случайное направление: 0 - вверх, 1 - вниз, 2 - влево, 3 - вправо

        switch (direction) {
            case 0: // Вверх
                if (getPositionX() - 1 >= 0 && !map[getPositionX() - 1][getPositionY()].isHaveAnimal()) {
                    setPositionX(getPositionX() - 1);
                }
                break;
            case 1: // Вниз
                if (getPositionX() + 1 < map.length && !map[getPositionX() + 1][getPositionY()].isHaveAnimal()) {
                    setPositionX(getPositionX() + 1);
                }
                break;
            case 2: // Влево
                if (getPositionY() - 1 >= 0 && !map[getPositionX()][getPositionY() - 1].isHaveAnimal()) {
                    setPositionY(getPositionY() - 1);
                }
                break;
            case 3: // Вправо
                if (getPositionY() + 1 < map[0].length && !map[getPositionX()][getPositionY() + 1].isHaveAnimal()) {
                    setPositionY(getPositionY() + 1);
                }
                break;
        }

        LIFE_ENERGY--; // Заяц теряет энергию за движение
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