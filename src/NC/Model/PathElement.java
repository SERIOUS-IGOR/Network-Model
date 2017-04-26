package NC.Model;

import java.util.Collection;

/**
 * Created by samok on 17.04.2017.
 */
public interface PathElement {
    double getTimeDelay();
    double getCost();
    String getInfo();
    Integer getID();
}
