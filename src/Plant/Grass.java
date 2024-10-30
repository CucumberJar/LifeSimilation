package Plant;

public class Grass extends Plant implements PlantRules{

    public Grass(char icon, int nutritionalValue, int growthRate) {
        super(icon, nutritionalValue, growthRate);
    }

    @Override
    public void grow() {

    }


}
