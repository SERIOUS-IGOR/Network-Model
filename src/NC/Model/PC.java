package NC.Model;

/**
 * Created by samok on 17.04.2017.
 */
public class PC extends ActiveElement {
    public PC() {
    }

    protected PC(String ip,
                 String defaultGateway,
                 double cost,
                 double timeDelay,
                 String info,
                 int ID){
        super(ip, defaultGateway, cost, timeDelay, info, ID);
    }
}
