package NC.Model;

/**
 * Created by samok on 26.04.2017.
 */
public class Firewall extends ActiveElement  {
    protected Firewall(String ip,
                 String defaultGateway,
                 double cost,
                 double timeDelay,
                 String info,
                 int ID){
        super(ip, defaultGateway, cost, timeDelay, info, ID);
    }
}
