package nc.model.passive;

import nc.model.PathElement;

/**
 * Created by samok on 26.04.2017.
 */
public class PassiveElement implements PathElement {
    public PassiveElement(double cost,double delay){
        this.cost = cost;
        this.delay = delay;
    }
    private double cost;
    private double delay;
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
