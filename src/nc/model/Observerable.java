package nc.model;
import nc.view.Observer;

/**
 * Created by samok on 26.04.2017.
 */
public interface Observerable {
    void addObserver(Observer o);

    void deleteObserver(Observer o);

    void notifyObservers();
}
