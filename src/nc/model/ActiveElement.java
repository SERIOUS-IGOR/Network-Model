package nc.model;

import java.util.ArrayList;

/**
 * Created by samok on 17.04.2017.
 */
public class ActiveElement implements PathElement {

    private IPadress ip = new IPadress();
    private IPadress defaultGateway = new IPadress();
    private double cost = 0.0;
    private double timeDelay = 0.0;
    private String name = null;
    private static int idIterator=1;
    private int ID=idIterator;
    private String connectedOver = "Cable";
    protected ActiveElement() {
        idIterator++;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected ActiveElement(String ip,
                            String defaultGateway,
                            double cost,
                            double timeDelay,
                            String connectedOver,
                            String name){
        setIP(ip);
        setDefaultGateway(defaultGateway);
        setCost(cost);
        setTimeDelay(timeDelay);
        if(connectedOver.toLowerCase().equals("cable")||connectedOver.toLowerCase().equals("hub")){
            this.connectedOver=connectedOver;
        }
        else throw new IllegalArgumentException("WTF MAN");
        setName(name);
        idIterator++;
    }

    public String[] getAllInfo(){
        String[] info = {
                "Element: " + this.getClass().getSimpleName(),
                "Name: "+ this.getName(),
                "IP:" + this.getIp(),
                "default gateway: " + this.getDefaultGateway(),
                "cost: " + this.getCost(),
                "delay: "+ this.getTimeDelay(),
                "connected over: " + this.connectedOver.getClass().getSimpleName()
        };
        return info;
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

    public String getName() {
        return name;
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