package nc.model;

/**
 * Created by samok on 26.04.2017.
 */
public class Router extends ActiveElement {
    protected Router(String ip,
                     String defaultGateway,
                     double cost,
                     double timeDelay,
                     String connectedOver,
                     String info) {
        super(ip, defaultGateway, cost, timeDelay, connectedOver, info);
    }
}
