import Animal.Animal;
import Animal.Rabbit;
import Map.Map;
import Map.Cell;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Map map = new Map(20, 50);
        map.generate(); // Генерация карты
        map.print();
        Rabbit rabbit = new Rabbit(10,2.0,6,6);
        while (true) {
            map.growGrass();

            for (int i = 0; i < rabbit.getRabbits().size(); i++) {
                rabbit.getRabbits().get(i).moveAndEat(map.getMap());
            }

            try {
                Thread.sleep(100); // 1 секунда
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
