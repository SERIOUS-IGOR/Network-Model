package nc.model;

/**
 * Created by samok on 26.04.2017.
 */
public class PassiveElement implements PathElement{
    double Cost = 0.0;
    double TimeDelay = 0.0;
    String info = "No information detected";

    @Override
    public double getTimeDelay() {
        double cloneTimeDelay = TimeDelay;
        return cloneTimeDelay;
    }

    @Override
    public double getCost() {
        double cloneCost=Cost;
        return cloneCost;
    }

    public String getName() {
        return null;
    }

    @Override
    public Integer getID() {
        return null;
    }
}
