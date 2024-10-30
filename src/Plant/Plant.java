package Plant;

import java.util.ArrayList;
import java.util.Random;

public class Plant {
    static String[] textures = {"🌱","🌷","🌻","🍀","🌾"};
    static ArrayList<Plant> plants = new ArrayList<Plant>();
    private char icon;
    private int nutritionalValue;
    private int growthRate;
    private String color;
    Random random = new Random();
    public Plant(char icon, int nutritionalValue, int growthRate) {

        this.icon = icon;
        this.nutritionalValue = nutritionalValue;
        this.growthRate = growthRate;
        plants.add(this);
    }


    public String getIcon() {
        return textures[random.nextInt(5)];
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIcon(char icon) {
        this.icon = icon;
    }

    // Геттер для питательной ценности
    public int getNutritionalValue() {
        return nutritionalValue;
    }

    // Сеттер для питательной ценности
    public void setNutritionalValue(int nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    // Геттер для скорости роста
    public int getGrowthRate() {
        return growthRate;
    }

    // Сеттер для скорости роста
    public void setGrowthRate(int growthRate) {
        this.growthRate = growthRate;
    }

    // Абстрактный метод роста, который будет реализован в подклассах
}