package nc.view;

import nc.model.ModelOutput;

import java.util.ArrayList;

/**
 * Created by samok on 20.05.2017.
 */
public class NetView implements ModelView, Observer {
    private ModelOutput model;
    private ArrayList<String> output;
    public NetView( ModelOutput model){
        this.model = model;
    }
    @Override
    public void update(ArrayList<String> output) {
        this.output = output;
        printOutput(output);
    }

    @Override
    public void printOutput(ArrayList<String> output) {
        for (String out:output
             ) {
            System.out.println(out);
        }
    }
}
