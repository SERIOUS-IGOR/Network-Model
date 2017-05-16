package NC.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samok on 20.04.2017.
 */
public class Network {
    protected HashMap<Integer, PathElement> PathElements = new HashMap<>();
    private String name;

    public Network() {
    }

    public Network(String name) {
        this.name = name;
    }

    public String getName() {
        String other = name;
        return other;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void AddPathElement(PathElement element) {
        PathElements.put(element.getID(), element);
    }

    public void DeletePathElement(PathElement element) {
        PathElements.remove(element.getID(), element);
    }

    public void printPathElements() {
        for (Map.Entry entry : PathElements.entrySet()) {
            System.out.println(entry.getKey() + " "
                    + entry.getValue());
        }
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
}
