package NC.Model;

import com.sun.org.apache.xpath.internal.operations.NotEquals;
import sun.nio.ch.Net;
import sun.security.x509.IPAddressName;

import java.util.Collection;
import java.util.Map;

/**
 * Created by samok on 17.04.2017.
 */
public class ActiveElement implements PathElement {

    private IPadress ip = new IPadress();
    private IPadress defaultGateway = new IPadress();
    private double cost = 0.0;
    private double timeDelay = 0.0;
    private String info = null;
    private static int idIterator=1;
    private int ID=idIterator;
    private Class<? extends PassiveElement> connectedOver;
    protected ActiveElement() {
        idIterator++;
    }
    protected ActiveElement(String ip,
                            String defaultGateway,
                            double cost,
                            double timeDelay,
                            String info){
        setIP(ip);
        setDefaultGateway(defaultGateway);
        this.cost = cost;
        this.timeDelay = timeDelay;
        this.info = info;
        idIterator++;
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
        IPadress another = defaultGateway;
        return another;
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
class Main{
    public static void main(String [] args){
        Router r1 = new Router("192.168.0.1","85.210.15.17",24.0,60.0,"Its Router");
        PC pc1 = new PC("192.168.0.2","192.168.0.4",24.0,60.0,"Its first PC");
        PC pc2 = new PC("192.168.0.3","192.168.0.4",24.0,60.0,"Its second PC");
        Switch sw1 = new Switch("192.168.0.4","192.168.0.1",24.0,60.0,"Its switch");
        Switch sw2 = new Switch("192.168.1.4","192.168.0.1",80.0,25.0,"Its expensive switch");
        PC pc3 = new PC("192.168.1.2","192.168.1.4",24.0,60.0,"Its third PC");
        PC pc4 = new PC("192.168.1.3","192.168.1.4",24.0,60.0,"Its forth PC");
        Network net = new Network("test");
        net.AddPathElement(r1);
        net.AddPathElement(sw1);
        net.AddPathElement(pc1);
        net.AddPathElement(pc2);
        net.AddPathElement(sw2);
        net.AddPathElement(pc3);
        net.AddPathElement(pc4);
        net.printPathElements();
        System.out.println("______________________________________________");
        NetworkTest.netHelper(net);

    }
}
