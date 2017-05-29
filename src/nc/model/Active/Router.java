package nc.model.Active;

/**
 * Created by samok on 26.04.2017.
 */
public class Router extends ActiveElement {
    public Router(){}
    public Router(String ip,
                  String defaultGateway,
                  double cost,
                  double timeDelay,
                  String connectedOver,
                  String info) {
        super(ip, defaultGateway, cost, timeDelay, connectedOver, info);
    }
}
