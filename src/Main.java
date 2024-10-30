import Animal.Animal;
import Animal.Rabbit;
import Map.Map;
import Map.Cell;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Map map = new Map(13, 13);
        map.generate(); // Генерация карты
        map.print();
        Rabbit rabbit = new Rabbit(10,2.0,6,6);


        while (true) {
            for (int i = 0; i < rabbit.getRabbits().size(); i++) {
                rabbit.getRabbits().get(i).moveAndEat(map.getMap());

//                System.out.println(rabbit.getRabbits().size());
            }
            rabbit.moveAndEat(map.getMap());
            try {
                Thread.sleep(1000); // 1 секунда
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
