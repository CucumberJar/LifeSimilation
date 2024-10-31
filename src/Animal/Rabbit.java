package Animal;
import Map.Cell;

import java.util.ArrayList;
import java.util.Random;

public class Rabbit extends Animal {
    private static final int MAX_LIFE_ENERGY =20;
    private int age=0;
    private static final int MAX_AGE =20;
    private int energy=0;
    private String icon="🐰";
    private static final int ENERGY_TO_REPRODUCE = 10;
    private static final int ENERGY_GAIN_FROM_GRASS = 1;
    private int LIFE_ENERGY = 10;

    private static   ArrayList<Rabbit> rabbits = new ArrayList<>();
    public Rabbit(int MAX_AGE, double speed, int positionX, int positionY) {
        super(MAX_AGE, speed, positionX, positionY);
       rabbits.add(this);
    }

    public void moveAndEat(Cell[][] map) {
        age++;
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
        if (LIFE_ENERGY <= 0|| age>=MAX_AGE) {
            Cell currentCell = map[getPositionX()][getPositionY()];
            rabbits.remove(this);
            currentCell.delAnimal();
        }
    }

    private void moveToGrass(int targetX, int targetY, Cell[][] map) {
        int deltaX = targetX - getPositionX();
        int deltaY = targetY - getPositionY();

        int stepX = Integer.compare(deltaX, 0);
        int stepY = Integer.compare(deltaY, 0);

        if (stepX != 0 && getPositionX() + stepX >= 0 && getPositionX() + stepX < map.length
                && !map[getPositionX() + stepX][getPositionY()].isHaveAnimal()) {
            setPositionX(getPositionX() + stepX);
        }
        // Перемещаемся в направлении травы по оси Y, если не смогли переместиться по X
        else if (stepY != 0 && getPositionY() + stepY >= 0 && getPositionY() + stepY < map[0].length
                && !map[getPositionX()][getPositionY() + stepY].isHaveAnimal()) {
            setPositionY(getPositionY() + stepY);
        }

        // Проверяем, находится ли кролик на траве, и ест, если да
        Cell targetCell = map[getPositionX()][getPositionY()];
        if (targetCell.isHavePlant()) {
            targetCell.getPlant();
            energy += ENERGY_GAIN_FROM_GRASS;
            LIFE_ENERGY = Math.min(LIFE_ENERGY + ENERGY_GAIN_FROM_GRASS, MAX_LIFE_ENERGY);
            targetCell.delPlant(); // Удаляем траву после еды
        } else {
            LIFE_ENERGY--; // Уменьшаем жизненную энергию, если травы нет
        }

        // Обновляем позицию кролика на текущей клетке
        targetCell.setAnimal(this);
    }

    private void moveRandomly(Cell[][] map) {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0:
                if (getPositionX() - 1 >= 0 && !map[getPositionX() - 1][getPositionY()].isHaveAnimal()) {
                    setPositionX(getPositionX() - 1);
                }
                break;
            case 1:
                if (getPositionX() + 1 < map.length && !map[getPositionX() + 1][getPositionY()].isHaveAnimal()) {
                    setPositionX(getPositionX() + 1);
                }
                break;
            case 2:
                if (getPositionY() - 1 >= 0 && !map[getPositionX()][getPositionY() - 1].isHaveAnimal()) {
                    setPositionY(getPositionY() - 1);
                }
                break;
            case 3:
                if (getPositionY() + 1 < map[0].length && !map[getPositionX()][getPositionY() + 1].isHaveAnimal()) {
                    setPositionY(getPositionY() + 1);
                }
                break;
        }
        LIFE_ENERGY--;
    }
    public  ArrayList<Rabbit> getRabbits() {return rabbits;}
    public String getIcon() {return icon;}
    public boolean canReproduce() {return energy >= ENERGY_TO_REPRODUCE;}

    public Rabbit reproduce(int MAX_AGE, double speed, int positionX, int positionY) {
        if (canReproduce()) {energy /= 2;return new Rabbit(MAX_AGE,speed,positionX,positionY);
        }return null;
    }
}