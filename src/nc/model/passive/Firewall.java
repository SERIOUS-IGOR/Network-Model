package nc.model.passive;

import nc.model.active.ActiveElement;

/**
 * Created by samok on 26.04.2017.
 */
public class Firewall extends ActiveElement {
    protected Firewall(String ip,
                       String defaultGateway,
                       double cost,
                       double timeDelay,
                       String connectedOver,
                       String info) {
        super(ip, defaultGateway, cost, timeDelay, connectedOver, info);
    }
}
