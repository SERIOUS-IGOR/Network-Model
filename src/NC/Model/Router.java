package NC.Model;

/**
 * Created by samok on 26.04.2017.
 */
public class Router extends ActiveElement {
    protected Router(String ip,
                 String defaultGateway,
                 double cost,
                 double timeDelay,
                 String info,
                 int ID){
        super(ip, defaultGateway, cost, timeDelay, info, ID);
    }
}
