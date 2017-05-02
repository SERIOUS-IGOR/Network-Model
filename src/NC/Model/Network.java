package NC.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samok on 20.04.2017.
 */
public class Network {
    private HashMap<Integer, PathElement> PathElements = new HashMap<>();
    private String name;
    public Network(String name){
        this.name = name;
    }
    public String getName() {
        String other = name;
        return other;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, PathElement> getPathElements() {
        HashMap clone = (HashMap) PathElements.clone();
        return clone;
    }

    public void AddPathElement(PathElement element) {
        PathElements.put(element.getID(), element);
    }

    public void DeletePathElement(PathElement element) {
        PathElements.remove(element.getID(), element);
    }

    public void printPathElements() {
        for (Map.Entry entry : PathElements.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
    }
    public int size(){
        return PathElements.size();
    }
}
