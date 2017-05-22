package nc.model;

/**
 * Created by samok on 26.04.2017.
 */
public class PassiveElement implements PathElement{
    double cost;
    double delay;
    public double getDelay() {
        return delay;
    }

    @Override
    public double getCost() {
        return cost;
    }

    public String getName() {
        return null;
    }

    @Override
    public Integer getID() {
        return null;
    }
}
