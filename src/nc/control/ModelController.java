package nc.control;

import nc.model.ActiveElement;
import nc.model.Network;

import java.util.HashMap;

/**
 * Created by samok on 20.05.2017.
 */
public interface ModelController {
    void start();
    String[] getHelp();
    void createNetwork();
    void createPC();
    void createSwitch();
    void createRouter();
    void deleteElementFromNetwork();
    String addElement(ActiveElement elem, Network net);
    void deleteNetwork();
    void info();
    void findRouteByCost();
    void findRouteByDelay();
    void findRoute();
}
