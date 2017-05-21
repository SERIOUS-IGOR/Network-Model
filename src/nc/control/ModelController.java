package nc.control;

import nc.model.ActiveElement;
import nc.model.Network;

import java.util.HashMap;

/**
 * Created by samok on 20.05.2017.
 */
public interface ModelController {
    public void start();
    public String[] getHelp();
    public void createNetwork();
    public void createPC();
    public void createSwitch();
    public void createRouter();
    public void deleteElementFromNetwork();
    public String addElement(ActiveElement elem, Network net);
}
