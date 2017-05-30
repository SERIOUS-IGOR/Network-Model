package nc.control;

import java.util.*;

import nc.model.*;
import nc.model.active.ActiveElement;
import nc.model.active.*;
import nc.model.exeptions.RouteNotFoundExeption;
import nc.model.network.*;
import static nc.control.StringConsts.*;

/**
 * Created by samok on 17.05.2017.
 */
public class Controller implements ModelController {
    private ModelOutput model;
    private HashMap<String, String> help = new HashMap<String, String>() {{
        put(CREATE_PC, "PC" + CREATING);
        put(CREATE_SWITCH, "Switch" + CREATING);
        put(CREATE_ROUTER, "Router" + CREATING);
        put(CREATE_NETWORK, "network" + CREATING);
        put(DELETE_ELEMENT, "deleting element from network");
        put(DELETE_NETWORK, "deleting network");
        put(INFO, "info get all information about element such as IP, cost, delay etc.");
        put(FIND_COST, FINDING +" by cost");
        put(FIND_DELAY, FINDING +"  by delay");
        put(FIND, FINDING);
        put(EXIT, "this is the end");
        put(HELP, "help [ /create[network/element] / delete[network/element] / info / findRoute/ add / exit]\n" +
                "see that message or help by any of command from help");
    }};

    public Controller(ModelOutput model) {
        this.model = model;
    }

    @Override
    public ArrayList<String> getHelp() {
        ArrayList<String>  help = new ArrayList(12);
        int helpIterator = 0;
        for (Map.Entry entry : this.help.entrySet()
                ) {
            help.add(entry.getKey() + " - " + entry.getValue());
        }
        return help;
    }

    @Override
    public void start() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            model.setOutput("First step is creating new network");
            model.setOutput("Input network name:");
            String name = input.nextLine();
            try {
                Networks.createNetwork(name);
                flag = false;
            } catch (IllegalArgumentException e) {
                model.setOutput(e.getMessage());
                model.setOutput("Try again");
            }
            model.setOutput("network was successfully created");
            model.setOutput("\n");
        }
        model.setOutput("Input command, type \"help\" to see help list");
        String command = input.nextLine();
        while (!EXIT.equals(command.toLowerCase())) {
            if (help.containsKey(command.toLowerCase())) {
                switch (command) {
                    case (CREATE_PC): {
                        createPC();
                        break;
                    }
                    case (CREATE_SWITCH): {
                        createSwitch();
                        break;
                    }
                    case (CREATE_ROUTER): {
                        createRouter();
                        break;
                    }
                    case (CREATE_NETWORK): {
                        createNetwork();
                        break;
                    }
                    case (DELETE_ELEMENT): {
                        deleteElementFromNetwork();
                        break;
                    }
                    case (DELETE_NETWORK): {
                        deleteNetwork();
                        break;
                    }
                    case (INFO): {
                        info();
                        break;
                    }
                    case (FIND_COST): {
                        findRouteByCost();
                        break;
                    }
                    case (FIND_DELAY): {
                        findRouteByDelay();
                        break;
                    }
                    case (FIND): {
                        findRoute();
                        break;
                    }
                    case (HELP): {
                        model.setOutput(getHelp());
                        break;
                    }
                    case (EXIT): {
                        break;
                    }
                }
            }
            else model.setOutput("Unknown command");
            if (!EXIT.equals(command)) command = input.nextLine();
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
            if (CANCEL.equals(netName)) {
                model.setOutput("canceled");
            } else if (Networks.doesNetworkExist(name)) {
                model.setOutput("network with name \"" + name + "\" already exist");
            } else {
                model.setOutput(Networks.createNetwork(name));
            }
        }
    }

    @Override
    public void createPC() {
        Scanner input = new Scanner(System.in);
        model.setOutput(INPUT_IP);
        String ip = input.nextLine();
        model.setOutput("Default gateway " + INPUT_IP);
        String def = input.nextLine();
        model.setOutput(INPUT_COST);
        String cost = input.nextLine();
        model.setOutput(INPUT_DELAY);
        String delay = input.nextLine();
        model.setOutput(CONNECT_TYPE);
        String type = input.nextLine();
        model.setOutput(INPUT_NAME);
        String name = input.nextLine();
        try {
            PC pc = new PC(ip, def, Double.parseDouble(cost), Double.parseDouble(delay), type, name);
            model.setOutput("PC was successfully created");
            model.setOutput("You must add new element to any network. Input network name: ");
            boolean flag = true;
            while (flag) {
                String netName = input.nextLine();
                if (CANCEL.equals(netName)) {
                    break;
                }
                if (Networks.doesNetworkExist(netName)) {
                    addElement(pc, Networks.getNetwork(netName));
                    model.setOutput("PC was successfully added to network " + netName);
                    flag = false;
                } else
                    model.setOutput("This network doesn't exist, try again or write \"cancel\" to cancel creating PC");
            }

        } catch (IllegalArgumentException e) {
            model.setOutput(e.getMessage());
        }
    }

    @Override
    public void createSwitch() {
        Scanner input = new Scanner(System.in);
        model.setOutput(INPUT_IP);
        String ip = input.nextLine();
        model.setOutput("Default gateway " + INPUT_IP);
        String def = input.nextLine();
        model.setOutput(INPUT_COST);
        String cost = input.nextLine();
        model.setOutput(INPUT_DELAY);
        String delay = input.nextLine();
        model.setOutput(CONNECT_TYPE);
        String type = input.nextLine();
        model.setOutput(INPUT_NAME);
        String name = input.nextLine();
        try {
            Switch swit = new Switch(ip, def, Double.parseDouble(cost), Double.parseDouble(delay), type, name);
            model.setOutput("Switch was successfully created");
            model.setOutput("You must add new element to any network. Input network name: ");
            boolean flag = true;
            while (flag) {
                String netName = input.nextLine();
                if (CANCEL.equals(netName)) {
                    break;
                }
                if (Networks.doesNetworkExist(netName)) {
                    addElement(swit, Networks.getNetwork(netName));
                    model.setOutput("Switch was successfully added to network " + netName);
                    flag = false;
                } else
                    model.setOutput("This network doesn't exist, try again or write \"cancel\" to cancel creating Switch");
            }

        } catch (IllegalArgumentException e) {
            model.setOutput(e.getMessage());
        }
    }

    @Override
    public void createRouter() {
        Scanner input = new Scanner(System.in);
        model.setOutput(INPUT_IP);
        String ip = input.nextLine();
        model.setOutput("Default gateway " + INPUT_IP);
        String def = input.nextLine();
        model.setOutput(INPUT_COST);
        String cost = input.nextLine();
        model.setOutput(INPUT_DELAY);
        String delay = input.nextLine();
        model.setOutput(CONNECT_TYPE);
        String type = input.nextLine();
        model.setOutput(INPUT_NAME);
        String name = input.nextLine();
        try {
            Router router = new Router(ip, def, Double.parseDouble(cost), Double.parseDouble(delay), type, name);
            model.setOutput("Router was successfully created");
            model.setOutput("You must add new element to any network. Input network name: ");
            boolean flag = true;
            while (flag) {
                String netName = input.nextLine();
                if (CANCEL.equals(netName)) {
                    break;
                }
                if (Networks.doesNetworkExist(netName)) {
                    addElement(router, Networks.getNetwork(netName));
                    model.setOutput("Router was successfully added to network " + netName);
                    flag = false;
                } else
                    model.setOutput("This network doesn't exist, try again or write \"cancel\" to cancel creating Router");
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
            model.setOutput("Input element IP");
            String ip = input.nextLine();
            model.setOutput("Input network name");
            String netName = input.nextLine();
            if(IPaddress.isIpCorrect(ip)) {
                try {
                    Network temp = Networks.getNetwork(netName);
                    temp.deletePathElement(temp.getNetworkElement(temp.containsIP(ip)));
                    flag = false;
                } catch (IllegalArgumentException e) {
                    model.setOutput(e.getMessage());
                    model.setOutput("press Enter to try again or type \"cancel\" to cancel");
                    String cancel = input.nextLine();
                    if (CANCEL.equals(cancel.toLowerCase()))flag = false;
                }
            }
            else model.setOutput("Incorrect IP address");
        }
    }

    @Override
    public String addElement(ActiveElement elem, Network net) {
        if (net.containsIP(elem.getIp()) > 0)
            return "Element with such IP already added in this network, try again with another IP";
        else {
            net.addPathElement(elem);
            return "Element was successfully added to network" + net.getName();
        }
    }

    @Override
    public void deleteNetwork() {
        if (Networks.getNetworks().isEmpty()) model.setOutput("There is nothing to delete");
        else if (Networks.getNetworks().size() == 1) model.setOutput("You can't delete last network");
        else {
            model.setOutput("Which network do you want to delete?");
            for (Map.Entry entry : Networks.getNetworks().entrySet()
                    ) {
                model.setOutput(((Network) entry.getValue()).getName() + " ");
            }
            Scanner input = new Scanner(System.in);
            boolean flag = true;
            while (flag) {
                model.setOutput("Input network name or \"cancel\" to cancel");
                String netName = input.nextLine();
                if (CANCEL.equals(netName)) {
                    break;
                } else {
                    try {
                        Networks.deleteNetwork(netName);
                        model.setOutput("network was successfully deleted");
                    } catch (IllegalArgumentException e) {
                        model.setOutput(e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void info() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            model.setOutput(INPUT_IP);
            String ip = input.nextLine();
            model.setOutput("Input network name");
            String netName = input.nextLine();
            try {
                Network temp = Networks.getNetwork(netName);
                model.setOutput(temp.getNetworkElement(temp.containsIP(ip)).getAllInfo());
                model.setOutput("\n");
                flag = false;
            } catch (IllegalArgumentException e) {
                model.setOutput(e.getMessage());
                model.setOutput("press Enter to try again or type \"cancel\" to cancel");
                String cancel = input.nextLine();
                if (CANCEL.equals(cancel.toLowerCase())) flag = false;
            }
        }
    }

    @Override
    public void findRouteByCost() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            model.setOutput("First " +INPUT_IP);
            String ip1 = input.nextLine();
            model.setOutput("Second " + INPUT_IP);
            String ip2 = input.nextLine();
            model.setOutput("Input network name");
            String netName = input.nextLine();
            try {
                Network temp = Networks.getNetwork(netName);
                ArrayList<PathElement> path = (ArrayList<PathElement>) NetworkTest.getPathByCost(
                        temp,
                        temp.getNetworkElement(temp.containsIP(ip1)),
                        temp.getNetworkElement(temp.containsIP(ip2)));
                for (int i = path.size();i>0;i--) {
                        model.setOutput(((ActiveElement)path.get(i)).getIp().toString());
                }
                model.setOutput("Path finded");
                model.setOutput("\n");
                flag = false;
            } catch (IllegalArgumentException e) {
                model.setOutput(e.getMessage());
                model.setOutput("press Enter to try again or type \"cancel\" to cancel");
                String cancel = input.nextLine();
                if (CANCEL.equals(cancel.toLowerCase())) flag = false;
            } catch (RouteNotFoundExeption r) {
                model.setOutput(r.getMessage());
                model.setOutput("press Enter to try again or type \"cancel\" to cancel");
                String cancel = input.nextLine();
                if (CANCEL.equals(cancel.toLowerCase())) flag = false;
            }
        }
    }

    @Override
    public void findRouteByDelay() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            model.setOutput("First " +INPUT_IP);
            String ip1 = input.nextLine();
            model.setOutput("Second " + INPUT_IP);
            String ip2 = input.nextLine();
            model.setOutput("Input network name");
            String netName = input.nextLine();
            try {
                Network temp = Networks.getNetwork(netName);
                ArrayList<PathElement> path = (ArrayList<PathElement>) NetworkTest.getPathByDelay(
                        temp,
                        temp.getNetworkElement(temp.containsIP(ip1)),
                        temp.getNetworkElement(temp.containsIP(ip2)));
                for (PathElement elem : path
                        ) {
                    model.setOutput(((ActiveElement) elem).getIp().toString() + "\n");
                }
                model.setOutput("Path finded");
                model.setOutput("\n");
                flag = false;
            } catch (IllegalArgumentException e) {
                model.setOutput(e.getMessage());
                model.setOutput("press Enter to try again or type \"cancel\" to cancel");
                String cancel = input.nextLine();
                if (CANCEL.equals(cancel.toLowerCase())) flag = false;
            } catch (RouteNotFoundExeption r) {
                model.setOutput(r.getMessage());
                model.setOutput("press Enter to try again or type \"cancel\" to cancel");
                String cancel = input.nextLine();
                if (CANCEL.equals(cancel.toLowerCase())) flag = false;
            }
        }
    }

    @Override
    public void findRoute() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            model.setOutput("First " +INPUT_IP);
            String ip1 = input.nextLine();
            model.setOutput("Second " + INPUT_IP);
            String ip2 = input.nextLine();
            model.setOutput("Input network name");
            String netName = input.nextLine();
            try {
                Network temp = Networks.getNetwork(netName);
                ArrayList<PathElement> path = (ArrayList<PathElement>) NetworkTest.getPath(
                        temp,
                        temp.getNetworkElement(temp.containsIP(ip1)),
                        temp.getNetworkElement(temp.containsIP(ip2)));
                for (PathElement elem : path
                        ) {
                    model.setOutput(((ActiveElement) elem).getIp().toString() + "\n");
                }
                model.setOutput("Path finded");
                model.setOutput("\n");
                flag = false;
            } catch (IllegalArgumentException e) {
                model.setOutput(e.getMessage());
                model.setOutput("press Enter to try again or type \"cancel\" to cancel");
                String cancel = input.nextLine();
                if (CANCEL.equals(cancel.toLowerCase())) flag = false;
            } catch (RouteNotFoundExeption r) {
                model.setOutput(r.getMessage());
                model.setOutput("press Enter to try again or type \"cancel\" to cancel");
                String cancel = input.nextLine();
                if (CANCEL.equals(cancel.toLowerCase())) flag = false;
            }
        }
    }
}

