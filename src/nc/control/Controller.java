package nc.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import nc.model.*;

/**
 * Created by samok on 17.05.2017.
 */
public class Controller implements ModelController {
    private ModelOutput model;
    private HashMap<String, String> help = new HashMap<String, String>() {{
        put("create pc", "Creating new element PC, you will asked for parametrs after this command");
        put("create switch", "Creating new element switch, you will asked for parametrs after this command");
        put("create router", "Creating new element router, you will asked for parametrs after this command");
        put("create network", "Creating new network, you will asked for parametrs after this command");
        put("delete element", "deletining element from network");
        put("delete network", "deleting network");
        put("info", "info get all information about element such as IP, cost, delay etc.");
        put("findroutebycost", "finding route between ip1 and ip2 in \"NetworkName\" network by cost of elements");
        put("findroutebydelay", "finding route between ip1 and ip2 in \"NetworkName\" network by delay of elements");
        put("findroute", "finding route between ip1 and ip2 in \"NetworkName\" network");
        put("exit", "this is the end");
        put("help", "help [ /create[network/element] / delete[network/element] / info / findRoute/ add / exit]\n" +
                "see that message or help by any of command from help");
    }};
    public Controller (ModelOutput model){
        this.model = model;
    }
    @Override
    public String[] getHelp() {
        String[] help = new String[12];
        int helpIterator = 0;
        for (Map.Entry entry:this.help.entrySet()
             ) {
            help[helpIterator++] = entry.getKey() + " - " + entry.getValue();
        }
        return help;
    }

    @Override
    public void start() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            model.setOutput("First step is creating new network");
            model.setOutput("Input network name:");
            String name =input.nextLine();
            try{
                Networks.createNetwork(name);
                flag = false;
            }
            catch (IllegalArgumentException e){
                model.setOutput(e.getMessage());
                model.setOutput("Try again");
            }
            model.setOutput("Network was successfully created");
            model.setOutput("\n");
        }
        model.setOutput("Input command, type \"help\" to see help list");
        String command = input.nextLine();
        while (!command.toLowerCase().equals("exit")) {
            if(help.containsKey(command.toLowerCase())){
                switch (command){
                    case("create pc"):{
                        createPC();
                    }
                    case("create switch"):{
                        createSwitch();
                    }
                    case("create router"):{
                        createRouter();
                    }
                    case("create network"):{
                        createNetwork();
                    }
                    case("delete element"):{
                        deleteElementFromNetwork();
                    }
                    case("delete network"):{

                    }
                    case("info"):{

                    }
                    case("findroutebycost"):{

                    }
                    case("findroutebydelay"):{

                    }
                    case("findroute"):{

                    }
                    case("help"):{

                    }
                    case("exit"):{
                        break;
                    }
                }
            }
            if(!command.equals("exit"))command = input.nextLine();
        }
        System.out.println("exiting. . .\n Have a nice day!");
    }

    @Override
    public void createNetwork() {
        Scanner input = new Scanner(System.in);
        model.setOutput("Input network name: ");
        String name = input.nextLine();
        model.setOutput("\n");
        boolean flag = true;
        while (flag) {
            String netName = input.nextLine();
            if (netName.equals("cancel")) {
                model.setOutput("canceled");
            }
            else if (Networks.doesNetworkExist(name)) {
                model.setOutput("Network with name \"" + name + "\" already exist");
            }
            else {
                model.setOutput(Networks.createNetwork(name));
            }
        }
    }

    @Override
    public void createPC() {
        Scanner input = new Scanner(System.in);
        model.setOutput("Input PC IP, example: 192.168.0.1");
        String ip = input.nextLine();
        model.setOutput("Input PC default gateway IP, example: 192.168.0.1");
        String def = input.nextLine();
        model.setOutput("Input PC cost, example: 10.0");
        String cost = input.nextLine();
        model.setOutput("Input PC delay, example: 65.0");
        String delay = input.nextLine();
        model.setOutput("Input type of connection (Cable or hub");
        String type = input.nextLine();
        model.setOutput("Input name for new PC, example: \"HomePC\"");
        String name = input.nextLine();
        try {
            PC pc = new PC(ip, def, Double.parseDouble(cost), Double.parseDouble(delay), type, name);
            model.setOutput("PC was successfully created");
            model.setOutput("You must add new element to any network. Input network name: ");
            boolean flag = true;
            while (flag) {
                String netName = input.nextLine();
                if(netName.equals("cancel")){
                    break;
                }
                if (Networks.doesNetworkExist(netName)) {
                    addElement(pc, Networks.getNetwork(netName));
                    model.setOutput("PC was successfully added to Network " + netName);
                    flag = false;
                } else model.setOutput("This network doesn't exist, try again or write \"cancel\" to cancel creating PC");
            }

        } catch (IllegalArgumentException e) {
           model.setOutput(e.getMessage());
        }
    }

    @Override
    public void createSwitch() {
        Scanner input = new Scanner(System.in);
        model.setOutput("Input Switch IP, example: 192.168.0.1");
        String ip = input.nextLine();
        model.setOutput("Input Switch default gateway IP, example: 192.168.0.1");
        String def = input.nextLine();
        model.setOutput("Input Switch cost, example: 10.0");
        String cost = input.nextLine();
        model.setOutput("Input Switch delay, example: 65.0");
        String delay = input.nextLine();
        model.setOutput("Input type of connection (Cable or hub");
        String type = input.nextLine();
        model.setOutput("Input name for new Switch, example: \"HomeSwitch\"");
        String name = input.nextLine();
        try {
            Switch swit = new Switch(ip, def, Double.parseDouble(cost), Double.parseDouble(delay), type, name);
            model.setOutput("Switch was successfully created");
            model.setOutput("You must add new element to any network. Input network name: ");
            boolean flag = true;
            while (flag) {
                String netName = input.nextLine();
                if(netName.equals("cancel")){
                    break;
                }
                if (Networks.doesNetworkExist(netName)) {
                    addElement(swit, Networks.getNetwork(netName));
                    model.setOutput("Switch was successfully added to Network " + netName);
                    flag = false;
                } else model.setOutput("This network doesn't exist, try again or write \"cancel\" to cancel creating Switch");
            }

        } catch (IllegalArgumentException e) {
            model.setOutput(e.getMessage());
        }
    }

    @Override
    public void createRouter() {
        Scanner input = new Scanner(System.in);
        model.setOutput("Input Router IP, example: 192.168.0.1");
        String ip = input.nextLine();
        model.setOutput("Input Router default gateway IP, example: 192.168.0.1");
        String def = input.nextLine();
        model.setOutput("Input Router cost, example: 10.0");
        String cost = input.nextLine();
        model.setOutput("Input Router delay, example: 65.0");
        String delay = input.nextLine();
        model.setOutput("Input type of connection (Cable or hub");
        String type = input.nextLine();
        model.setOutput("Input name for new Router, example: \"HomeRouter\"");
        String name = input.nextLine();
        try {
            Router router = new Router(ip, def, Double.parseDouble(cost), Double.parseDouble(delay), type, name);
            model.setOutput("Router was successfully created");
            model.setOutput("You must add new element to any network. Input network name: ");
            boolean flag = true;
            while (flag) {
                String netName = input.nextLine();
                if(netName.equals("cancel")){
                    break;
                }
                if (Networks.doesNetworkExist(netName)) {
                    addElement(router, Networks.getNetwork(netName));
                    model.setOutput("Router was successfully added to Network " + netName);
                    flag = false;
                } else model.setOutput("This network doesn't exist, try again or write \"cancel\" to cancel creating Router");
            }

        } catch (IllegalArgumentException e) {
            model.setOutput(e.getMessage());
        }
    }

    @Override
    public void deleteElementFromNetwork() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            model.setOutput("Input element IP, example: 192.168.0.1");
            String elementIp = input.nextLine();
            model.setOutput("network name");
            String netName = input.nextLine();
            if(netName.equals("cancel")){
                break;
            }
            if (Networks.doesNetworkExist(netName)) {
        Network net = Networks.getNetwork(netName);
        if (net != null) {
            int i = net.containsIP(elementIp);
            if (i > 0) {
                net.deletePathElement(net.getNetworkElement(i));
                model.setOutput("Element with IP:" + elementIp + " was successfully deleted from Network: " + netName);
            } else model.setOutput("IP is not in this Network");
        } else model.setOutput("This network does not exist");
    }

    @Override
    public String addElement(ActiveElement elem, Network net) {
        if(net.containsIP(elem.getIp())>0)return "Element with such IP already added in this network, try again with another IP";
        else {
            net.addPathElement(elem);
            return "Element was successfully added to Network" + net.getName();
        }
    }
}

