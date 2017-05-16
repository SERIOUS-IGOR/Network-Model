package NC.Model;

import java.util.ArrayList;
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
    private static int idIterator=1;
    private int ID=idIterator;
    private String connectedOver = "Cable";
    protected ActiveElement() {
        idIterator++;
    }
    protected ActiveElement(String ip,
                            String defaultGateway,
                            double cost,
                            double timeDelay,
                            String connectedOver,
                            String info){
        setIP(ip);
        setDefaultGateway(defaultGateway);
        this.cost = cost;
        this.timeDelay = timeDelay;
        if(connectedOver.toLowerCase().equals("cable")||connectedOver.toLowerCase().equals("hub")){
            this.connectedOver=connectedOver;
        }
        else throw new IllegalArgumentException("WTF MAN");
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
        return timeDelay;
    }

    @Override
    public double getCost() {
       return cost;
    }

    public ArrayList<PathElement> getConnections(Network net) {
        if(net.containsIP(ip)<0)throw new IllegalArgumentException(
                "This element is not connected to \"" +
                        net.getName() +
                        "\" network");
        else{
            ArrayList<PathElement> connections = new ArrayList<>();
            int[][] matrix = NetworkTest.netMatrix(net);
            int count = 0;
            for(int i=0,j=getID()-1;i<net.PathElements.size();i++){
                if(matrix[i][j]==0)continue;
                else {
                    connections.add(count,net.PathElements.get(i+1));
                    count++;
                }
            }
            return connections;
        }
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public Integer getID() {
        return ID;
    }
    @Override
    public String toString(){
        String another = ip.getIp();
        return another;
    }
    public double getFullCost(){
        if (connectedOver.equals("cable"))return getCost()+Cable.cost;
        else return getCost()+Hub.cost;
    }
    public double getFullDelay(){
        if (connectedOver.equals("cable"))return getTimeDelay()+Cable.delay;
        else return getTimeDelay()+Hub.delay;
    }
}
class Main{
    public static void main(String [] args) {
        PC pc1 = new PC("192.168.0.1","192.168.0.254",1.0,60.0,"Cable","PC1");
        Router r1 = new Router("192.168.0.254","86.168.45.144",10.0,60.0,"Cable","R1");
        Switch sw1 = new Switch("192.168.0.4","192.168.0.254",4.0,60.0,"Cable","SW1");
        PC pc2 = new PC("192.168.0.2","192.168.0.4",5.0,60.0,"Cable","PC2");
        PC pc3 = new PC("192.168.0.3","192.168.0.2",5.0,60.0,"Cable","PC3(start)");
        PC pc4 = new PC("192.168.0.5","192.168.0.4",1.0,60.0,"Cable","PC4");
        PC pc5 = new PC("192.168.1.1","192.168.1.4",5.0,60.0,"Cable","PC5");
        PC pc6 = new PC("192.168.1.2","192.168.1.4",10.0,60.0,"Cable","PC6");
        Switch sw2 = new Switch("192.168.1.4","192.168.0.254",2.0,60.0,"Cable","PC6");
        Network net = new Network("test");
        net.AddPathElement(pc1);
        net.AddPathElement(pc2);
        net.AddPathElement(pc3);
        net.AddPathElement(pc4);
        net.AddPathElement(pc5);
        net.AddPathElement(pc6);
        net.AddPathElement(sw1);
        net.AddPathElement(sw2);
        net.AddPathElement(r1);
        System.out.println("______________________________________________");
        ArrayList<PathElement> path =(ArrayList<PathElement>) NetworkTest.getPathByDelay(pc3,pc6,net);
        for (int i=path.size();i>0;i--)
        {
            System.out.print(((ActiveElement)path.get(i-1)).getIp().toString()+"-" +((ActiveElement)path.get(i-1)).getID() + " ");
        }
        System.out.println();
        System.out.println("______________________________________________");
        path = (ArrayList<PathElement>) NetworkTest.getPath(pc3,pc6,net);
        for (int i=path.size();i>0;i--)
        {
            System.out.print(((ActiveElement)path.get(i-1)).getIp().toString()+"-" +((ActiveElement)path.get(i-1)).getID() + " ");
        }
    }
}
