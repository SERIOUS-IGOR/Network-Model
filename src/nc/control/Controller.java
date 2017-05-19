package nc.control;

import java.util.HashMap;
import nc.model.Networks;

/**
 * Created by samok on 17.05.2017.
 */
public class Controller {
    private HashMap<String, String> help = new HashMap<String,String>(){{
        put("create","create [network/element] - Creating new Network or new Network element");
        put("create element","create element [PC/switch/router] - Creating new element of network");
        put("delete element","delete element [ip_adress name] - deletining of element with these name and IP adress");
        put("info","info [ElementName] - get all information about element such as IP, cost, delay etc.");
        put("delete","delete [network/element] - deleting of exist network or elements;");
        put("create network","create network [name] - Creating new network with name \"name\"");
        put("delete network","delete network [name] - deliting network with name \"name\"");
        put("findRoute [ip1 ip2 NetworkName]","find route [ip1 ip2 NetworkName - finding route between ip1 and ip2 in \"NetworkName\" network");
        put("add","add [element NetworkName] - adding \"element\" element to \"NetworkName\" network");
        put("exit","this is the end");
        put("help", "help [ /create[network/element] / delete[network/element] / info / findRoute/ add / exit]\n"+
                "see that message or help by any of command from help");
    }};
    public HashMap getHelp(){
        HashMap<String,String> other  = new HashMap<>(help);
        return other;
    }
    public String getComandHelp (String command){
        if(help.containsKey(command)){
            return help.get(command);
        }
        else return "Unknown comand: " + command;
    }
    public String CreateNetwork(String name){
        if(Networks.doesNetworkExist(name))return "Network with name \""+name+"\" already exist";
        else {
            Networks.CreateNetwork(name);
            return "Network \""+ name+"\" was successfully created";
        }
    }

}
