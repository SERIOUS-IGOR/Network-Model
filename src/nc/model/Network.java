package nc.model;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samok on 20.04.2017.
 */
public class Network  {
    protected HashMap<Integer, PathElement> PathElements = new HashMap<>();
    private String name;

    public Network() {
    }

    public Network(String name) {
        setName(name.toLowerCase());
    }

    public String getName() {
        String other = name;
        return other;
    }

    public void setName(String name) {
        if(name==null)throw new IllegalArgumentException("Incorrect name: name cant be null");
        else if(name.length()<4)throw new IllegalArgumentException("Incorrect name: "+ name+", name must be longer than 4 characters");
        else if(name.contains(" "))throw new IllegalArgumentException("Incorrect name: "+ name+ ", name cant contain spaces");
        else this.name=name;
    }

    public void addPathElement(PathElement element) {
        PathElements.put(element.getID(), element);
    }

    public void deletePathElement(PathElement element) {
        if (PathElements.containsValue(element)) {
            PathElements.remove(element.getID(), element);
        }else throw  new IllegalArgumentException("No such element in this Network");
    }

    public int containsIP(IPadress findIP) {
        String find = findIP.toString();
        for (Map.Entry entry : PathElements.entrySet()) {
            if (((ActiveElement) entry.getValue()).getIp().toString().equals(find)) {
                return (int) entry.getKey();
            }
        }
        return 0;
    }
    public int containsIP(String findIP) {
        for (Map.Entry entry : PathElements.entrySet()) {
            if (((ActiveElement) entry.getValue()).getIp().toString().equals(findIP)) {
                return (int) entry.getKey();
            }
        }
        return 0;
    }
    public ActiveElement getNetworkElement(int id){
        if(PathElements.get(id)!=null)return (ActiveElement)PathElements.get(id);
        else throw new IllegalArgumentException("No such element in Network");
    }
}
