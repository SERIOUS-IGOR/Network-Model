package NC.Model;

import java.util.Collection;

/**
 * Created by samok on 17.04.2017.
 */
public class ActiveElement implements PathElement {
    private IPadress IP = new IPadress();
    private IPadress DefaultGateway = new IPadress();
    private double Cost = 0.0;
    private double TimeDelay = 0.0;
    private String info = null;
    private int ID = 0;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void getAllInfo(){
        System.out.println(getIP().toString());
        System.out.println(getDefaultGateway().toString());
        System.out.println(getCost());
        System.out.println(getTimeDelay());
        System.out.println(info);
    }
    public IPadress getIP() {
        IPadress clone = this.IP.clone();
        return clone;
    }

    public void setIP(IPadress IP) {
        this.IP = IP;
    }
    public void setIP(String textIP) {
        this.IP = new IPadress(textIP);
    }


    public IPadress getDefaultGateway() {
        IPadress cloneDG =  new IPadress();
        return cloneDG;
    }

    public void setDefaultGateway(IPadress defaultGateway) {
        this.DefaultGateway = defaultGateway;
    }
    public void setDefaultGateway(String textIP) {
        this.DefaultGateway = new IPadress(textIP);
    }
    public void setCost(double cost) {
        this.Cost = cost;
    }

    public void setTimeDelay(double timeDelay) {
        this.TimeDelay = timeDelay;
    }

    @Override
    public double getTimeDelay() {
        double cloneTimeDelay = TimeDelay;
        return cloneTimeDelay;
    }

    @Override
    public double getCost() {
        double cloneCost=Cost;
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
}
