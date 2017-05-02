package NC.Model;

import com.sun.org.apache.xpath.internal.operations.NotEquals;
import sun.nio.ch.Net;
import sun.security.x509.IPAddressName;

import java.util.Collection;

/**
 * Created by samok on 17.04.2017.
 */
public class ActiveElement implements PathElement {

    private IPadress ip = new IPadress();
    private IPadress defaultGateway = new IPadress();
    private double cost = 0.0;
    private double timeDelay = 0.0;
    private String info = null;
    private int ID = 0;
    private Class<? extends PassiveElement> connectedOver;
    protected ActiveElement() {
    }
    protected ActiveElement(String ip,
                            String defaultGateway,
                            double cost,
                            double timeDelay,
                            String info,
                            int ID) {
        setIP(ip);
        setDefaultGateway(defaultGateway);
        this.cost = cost;
        this.timeDelay = timeDelay;
        this.info = info;
        this.ID = ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public void getAllInfo(){
        System.out.println(getIp());
        System.out.println(getDefaultGateway());
        System.out.println(getCost());
        System.out.println(getTimeDelay());
        System.out.println(info);
    }
    public IPadress getIp() {
        IPadress another = ip;
        return another;
    }

    public void setIp(IPadress ip) {
        this.ip = ip;
    }
    public void setIP(String textIP) {
        this.ip = new IPadress(textIP);
    }


    public IPadress getDefaultGateway() {
        IPadress cloneDG =  new IPadress();
        return cloneDG;
    }

    public void setDefaultGateway(IPadress defaultGateway) {
        this.defaultGateway = defaultGateway;
    }
    public void setDefaultGateway(String textIP) {
        this.defaultGateway = new IPadress(textIP);
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setTimeDelay(double timeDelay) {
        this.timeDelay = timeDelay;
    }

    @Override
    public double getTimeDelay() {
        double cloneTimeDelay = timeDelay;
        return cloneTimeDelay;
    }

    @Override
    public double getCost() {
        double cloneCost= cost;
        return cloneCost;
    }

    public Collection<PathElement> getConnections() {
        return null;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public Integer getID() {
        int copyID = ID;
        return copyID;
    }
    @Override
    public String toString(){
        String another = ip.getIp();
        return another;
    }
}
