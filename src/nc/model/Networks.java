package nc.model;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samok on 17.05.2017.
 */
public class Networks {
    private static HashMap<Integer,Network> networks = new HashMap<>();
    private static int networksIterator=0;
    static public String createNetwork(String name){
        if(!networks.containsKey(name)){
            try {
                Network net = new Network(name);
            }
            catch (IllegalArgumentException e){return "Invalid name";}
            return "Network with name \""+name+ "\" already exist";
        }
        else return "Network with name: " + name+ " was successfully created";
    }
    static public boolean doesNetworkExist(String networkName){
        for (Map.Entry entry:networks.entrySet()
             ) {
            if(((Network)entry.getValue()).
                    getName().
                    equals(networkName.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public static HashMap<Integer, Network> getNetworks() {
        HashMap<Integer,Network> other = new HashMap<Integer,Network>(networks);
        return other;
    }

    static public Network getNetwork(String name){
        for (Map.Entry entry:networks.entrySet()
             ) {
            if(((Network)entry.getValue()).getName().equals(name)) {
                return (Network) entry.getValue();
            }
        }
        return null;
    }
    static public boolean deleteNetwork(String name){
        if(Networks.doesNetworkExist(name)){
            networks.remove(name,networks.get(name));
            return true;
        }
        else return false;
    }
}
