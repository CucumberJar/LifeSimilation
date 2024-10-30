package Map;
import Animal.Rabbit;
import Plant.Grass;

import java.util.Random;

public class Map {
///
///
/// //
///
///
///
///
    private final int SIZE_X;
    private final int SIZE_Y;
    private final Cell[][] map;
    private final Random random = new Random();
    private final int waterLevel = 4;  // Порог для уровня воды

    public Map(int sizeX, int sizeY) {
        this.SIZE_X = sizeX;
        this.SIZE_Y = sizeY;
        this.map = new Cell[SIZE_X][SIZE_Y];
    }

    // Генерация карты с плавным градиентом высоты
    public void generate() {
        // Устанавливаем точки контроля высоты по краям карты
        int[][] controlPoints = new int[SIZE_X][SIZE_Y];

        // Заполняем контрольные точки случайными значениями высоты
        for (int i = 0; i < SIZE_X; i += SIZE_X / 5) {
            for (int j = 0; j < SIZE_Y; j += SIZE_Y / 5) {
                controlPoints[i][j] = random.nextInt(15) + 1;  // Значение высоты от 1 до 15
            }
        }

        // Интерполяция высоты для каждой клетки карты
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                map[i][j] = new Cell(interpolateHeight(i, j, controlPoints));
            }
        }
    }

    // Интерполяция высоты для плавного перехода между контрольными точками
    private int interpolateHeight(int x, int y, int[][] controlPoints) {
        int cellX = x / (SIZE_X / 5);
        int cellY = y / (SIZE_Y / 5);

        int left = controlPoints[cellX * (SIZE_X / 5)][cellY * (SIZE_Y / 5)];
        int right = controlPoints[(cellX + 1) * (SIZE_X / 5) % SIZE_X][cellY * (SIZE_Y / 5)];
        int top = controlPoints[cellX * (SIZE_X / 5)][(cellY + 1) * (SIZE_Y / 5) % SIZE_Y];
        int bottom = controlPoints[(cellX + 1) * (SIZE_X / 5) % SIZE_X][(cellY + 1) * (SIZE_Y / 5) % SIZE_Y];

        // Линейная интерполяция между контрольными точками
        double tx = (double) (x % (SIZE_X / 5)) / (SIZE_X / 5);
        double ty = (double) (y % (SIZE_Y / 5)) / (SIZE_Y / 5);

        // Интерполяция высоты с шумом
        int topValue = (int) (left * (1 - tx) + right * tx);
        int bottomValue = (int) (top * (1 - tx) + bottom * tx);
        return (int) (topValue * (1 - ty) + bottomValue * ty + (random.nextDouble() - 0.5) * 2);
    }

    // Печать карты с учетом биомов и текущего сезона
    public void print() {
        // Скрываем курсор
        System.out.print("\033[?25l");
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                printTile(map[i][j]); // Печатаем ячейку
            }
            System.out.println();
        }
    }

    // Метод для печати ячейки карты с учетом уровня высоты
    private void printTile(Cell cell) {
        String resetColor = "\u001B[0m";
        int level = cell.getLevel();
        cell.setPlant(new Grass('@',2,2));
        System.out.print("\u001B[32m🌱" + resetColor);
       /* if (level < waterLevel) {
            System.out.print("\u001B[34m💧" + resetColor);  // Вода
        } else if (level == 4) {
            // Низкая трава
        } else if (level == 5) {
            System.out.print("\u001B[92m🌾" + resetColor);  // Поля
        } else {
            System.out.print("  ");  // Леса
        }*/
    }

    public Cell[][] getMap() {
        return map;
    }

    // Метод для обновления только позиции зайца
    public void updateRabbitPosition(int rabbitX, int rabbitY, int previousRabbitX, int previousRabbitY) {
        // Очищаем предыдущую позицию зайца
        System.out.print("\033[" + (previousRabbitX + 1) + ";" + (previousRabbitY * 2 + 1) + "H  "); // Очищаем ячейку
        // Печатаем зайца на новой позиции
        System.out.print("\033[" + (rabbitX + 1) + ";" + (rabbitY * 2 + 1) + "H\uD83D\uDC30"); // Печатаем зайца
        System.out.flush(); // Обновляем вывод
    }
}
