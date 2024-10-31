package Map;
import Plant.Grass;
import Plant.Plant;

import java.util.ArrayList;
import java.util.Random;
public class Map {
    private ArrayList <Cell> cells;// Список крайних трав
    private final int SIZE_X;
    private final int SIZE_Y;
    private final Cell[][] map;
    private final Random random = new Random();
    private final int waterLevel = 4;  // Порог для уровня воды

    public Map(int sizeX, int sizeY) {
        this.SIZE_X = sizeX;
        this.SIZE_Y = sizeY;
        this.map = new Cell[SIZE_X][SIZE_Y];
        cells = new ArrayList<>();
    }

    public void generate() {
        int[][] controlPoints = new int[SIZE_X][SIZE_Y];
        for (int i = 0; i < SIZE_X; i += SIZE_X / 5) {
            for (int j = 0; j < SIZE_Y; j += SIZE_Y / 5) {
                controlPoints[i][j] = random.nextInt(15) + 1;  // Значение высоты от 1 до 15
            }
        }
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                map[i][j] = new Cell(interpolateHeight(i, j, controlPoints));
            }
        }
    }

    private void updateEdgeCells() {
        cells.clear(); // Очищаем список перед обновлением
        for (int i = 1; i < map.length-1; i++) {
            for (int j = 1; j < map[i].length-1; j++) {
                if (!map[i][j].isHavePlant() && isEdgeGrass(map[i][j])) {
                    cells.add(map[i][j]);
                    map[i][j].setPositionX(i);
                    map[i][j].setPositionX(j);
                }
            }
        }
    }

    public void growGrass() {
        for (int i = 0; i < 3; i++) {
            updateEdgeCells(); // Обновляем список крайних трав
            if (!cells.isEmpty()) {setFreeCell(cells.get(random.nextInt(cells.size())));}
        }

    }

    private boolean isEdgeGrass(Cell cell) {
        if (cell.getPositionX()!=0){
        int x = cell.getPositionX();
        int y = cell.getPositionY();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    x += i;
                    y += j;
                    if (map[x][y].isHavePlant()) {return true;}
                }
            }
        }}
        return false;
    }
    private void setFreeCell(Cell cell) {
        int x = -1;
        int y = -1;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {x += i;y += j;}
                if ( !map[x][y].isHavePlant()) {cell.setPlant(new Grass(cell.getPositionX()+x,cell.getPositionY()+y));return;}
            }
        }

    }

    private int interpolateHeight(int x, int y, int[][] controlPoints) {
        int cellX = x / (SIZE_X / 5);
        int cellY = y / (SIZE_Y / 5);

        int left = controlPoints[cellX * (SIZE_X / 5)][cellY * (SIZE_Y / 5)];
        int right = controlPoints[(cellX + 1) * (SIZE_X / 5) % SIZE_X][cellY * (SIZE_Y / 5)];
        int top = controlPoints[cellX * (SIZE_X / 5)][(cellY + 1) * (SIZE_Y / 5) % SIZE_Y];
        int bottom = controlPoints[(cellX + 1) * (SIZE_X / 5) % SIZE_X][(cellY + 1) * (SIZE_Y / 5) % SIZE_Y];


        double tx = (double) (x % (SIZE_X / 5)) / (SIZE_X / 5);
        double ty = (double) (y % (SIZE_Y / 5)) / (SIZE_Y / 5);


        int topValue = (int) (left * (1 - tx) + right * tx);
        int bottomValue = (int) (top * (1 - tx) + bottom * tx);
        return (int) (topValue * (1 - ty) + bottomValue * ty + (random.nextDouble() - 0.5) * 2);
    }

    public void print() {
        System.out.print("\033[?25l");
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (map[i][j].getLevel()==waterLevel+1) {map[i][j].setPlant(new Grass(i,j));}
                printTile(map[i][j]);
            }
            System.out.println();
        }
    }


    private void printTile(Cell cell) {
        if (cell.isHavePlant()) System.out.print(cell.getPlant().getIcon());
        else System.out.print("  ");}


    public Cell[][] getMap() {
        return map;
    }

}


