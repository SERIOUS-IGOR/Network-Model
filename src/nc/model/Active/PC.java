package nc.model.Active;

/**
 * Created by samok on 17.04.2017.
 */
public class PC extends ActiveElement {
    public PC() {
    }

    public PC(String ip,
              String defaultGateway,
              double cost,
              double timeDelay,
              String connectedOver,
              String info) {
        super(ip, defaultGateway, cost, timeDelay, connectedOver, info);
    }
}
