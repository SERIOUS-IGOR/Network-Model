package nc.model.Active;

import nc.model.*;
import nc.model.Network.IPaddress;
import nc.model.Network.Network;
import nc.model.Network.NetworkTest;
import nc.model.Passive.Cable;
import nc.model.Passive.Hub;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by samok on 17.04.2017.
 */
public class ActiveElement implements PathElement {

    private IPaddress ip = new IPaddress();
    private IPaddress defaultGateway = new IPaddress();
    private double cost = 0.0;
    private double timeDelay = 0.0;
    private String name = null;
    private static int idIterator = 1;
    private int ID = idIterator;
    private String connectedOver;

    protected ActiveElement() {
        idIterator++;
    }


    protected ActiveElement(String ip,
                            String defaultGateway,
                            double cost,
                            double timeDelay,
                            String connectedOver,
                            String name) {
        if (IPaddress.isIpCorrect(ip)) setIP(ip);
        if (IPaddress.isIpCorrect(ip)) setDefaultGateway(defaultGateway);
        if (isNumberCorrect(cost)) setCost(cost);
        if (isNumberCorrect(timeDelay)) setTimeDelay(timeDelay);
        if (connectedOver.toLowerCase().equals("cable") ||
                connectedOver.toLowerCase().equals("hub"))
            setConnectedOver(connectedOver);
        else throw new IllegalArgumentException("Incorrect connection type: " + connectedOver);
        if (isNameCorrect(name)) setName(name);
        idIterator++;
    }

    public ArrayList<String> getAllInfo() {
        ArrayList<String> info = new ArrayList<>(7);
        info.add("Element: " + this.getClass().getSimpleName());
        info.add("Name: " + this.getName());
        info.add("IP:" + this.getIp());
        info.add("default gateway: " + this.getDefaultGateway());
        info.add("cost: " + this.getCost());
        info.add("delay: " + this.getDelay());
        info.add("connected over: " + this.connectedOver.getClass().getSimpleName());
        return info;
    }

    public IPaddress getIp() {
        IPaddress another = ip;
        return another;
    }

    private void setIp(IPaddress ip) {
        this.ip = ip;
    }

    private void setIP(String textIP) {
        this.ip = new IPaddress(textIP);
    }


    public IPaddress getDefaultGateway() {
        IPaddress another = defaultGateway;
        return another;
    }

    public void setDefaultGateway(IPaddress defaultGateway) {
        this.defaultGateway = defaultGateway;
    }

    private void setDefaultGateway(String textIP) {
        this.defaultGateway = new IPaddress(textIP);
    }

    private void setCost(double cost) {
        this.cost = cost;
    }

    private void setTimeDelay(double timeDelay) {
        this.timeDelay = timeDelay;
    }

    public double getDelay() {
        return timeDelay;
    }

    @Override
    public double getCost() {
        return cost;
    }

    public void setConnectedOver(String connectedOver) {
        {
            this.connectedOver = connectedOver;
        }
    }

    public ArrayList<PathElement> getConnections(Network net) {
        if (net.containsIP(ip) < 0) throw new IllegalArgumentException(
                "This element is not connected to \"" +
                        net.getName() +
                        "\" network");
        else {
            HashMap<Integer, PathElement> pathElements = net.getPathElements();
            ArrayList<PathElement> connections = new ArrayList<>();
            int[][] matrix = NetworkTest.netMatrix(net);
            int count = 0;
            for (int i = 0, j = getID() - 1; i < pathElements.size(); i++) {
                if (matrix[i][j] == 0) continue;
                else {
                    connections.add(count, pathElements.get(i + 1));
                    count++;
                }
            }
            return connections;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public String toString() {
        String another = ip.getIp();
        return another;
    }

    public double getFullCost() {
        if (connectedOver.equals("cable")) return getCost() + Cable.getInstance().getCost();
        else return getCost() +Hub.getInstance().getCost();
    }

    public double getFullDelay() {
        if (connectedOver.equals("cable")) return getDelay() + Cable.getInstance().getDelay();
        else return getDelay() + Hub.getInstance().getDelay();
    }

    private boolean isNumberCorrect(double number) {
        if (number < 0) throw new IllegalArgumentException("Incorrect cost: " + number);
        else return true;
    }

    private boolean isNameCorrect(String name) {
        if (name == null) throw new IllegalArgumentException("Incorrect name: name cant be null");
        else if (name.length() < 4)
            throw new IllegalArgumentException("Incorrect name: " + name + ", name must be longer than 4 characters");
        else if (name.contains(" "))
            throw new IllegalArgumentException("Incorrect name: " + name + ", name cant contain spaces");
        else return true;
    }
}
