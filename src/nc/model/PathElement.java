package nc.model;

/**
 * Created by samok on 17.04.2017.
 */
public interface PathElement {
    double getTimeDelay();
    double getCost();
    String getName();
    Integer getID();
}
