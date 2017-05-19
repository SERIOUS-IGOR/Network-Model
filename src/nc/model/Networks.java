package nc.model;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samok on 17.05.2017.
 */
public class Networks {
    private static HashMap<Integer,Network> networks;
    private static int networksIterator=0;
    static public String CreateNetwork(String name){
        if(networks.containsKey(name)){
            try {
                Network net = new Network(name);
            }
            catch (IllegalArgumentException e){return "Invalid name";}
            return "Network with name \""+name+ "\" already exist";
        }
        else return "Network ";
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
    static public boolean deleteNetwork(String name){
        if(Networks.doesNetworkExist(name)){
            networks.remove(name,networks.get(name));
            return true;
        }
        else return false;
    }
}
