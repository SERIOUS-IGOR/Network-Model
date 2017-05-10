package NC.Model;

/**
 * Created by samok on 26.04.2017.
 */
public class Switch extends ActiveElement  {
    protected Switch(String ip,
                 String defaultGateway,
                 double cost,
                 double timeDelay,
                 String info){
        super(ip, defaultGateway, cost, timeDelay, info);
    }
}
