package nc.model;

import nc.view.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samok on 21.05.2017.
 */
public class ModelOutput implements Observerable {
    private String[] output;
    private List<Observer> observers;
    public ModelOutput(){
        observers = new ArrayList<Observer>();
    }
    @Override
    public void addObserver(Observer o) {
        if(o!=null)observers.add(o);
    }

    public void setOutput(String[] output) {
        this.output = output;
        notifyObservers();
    }
    public void setOutput(String output){
        String[] convert = {output};
        this.output = convert;
        notifyObservers();
    }

    @Override
    public void deleteObserver(Observer o) {
        if(o!=null)observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
        {
            observer.update(output);
        }
    }
}
