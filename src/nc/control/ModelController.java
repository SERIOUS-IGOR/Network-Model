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
    public String getComandHelp (String command);
    public String createNetwork();
    public String createPC();
    public String createSwitch();
    public String createRouter();
    public String deleteElementFromNetwork(String pcIP, String netName);
    public String addElement(ActiveElement elem, Network net);
}
