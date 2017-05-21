package nc.view;

import nc.model.ModelOutput;

/**
 * Created by samok on 20.05.2017.
 */
public class NetView implements ModelView, Observer {
    private ModelOutput model;
    private String[]output;
    public NetView( ModelOutput model){
        this.model = model;
    }
    @Override
    public void update(String[] output) {
        this.output = output;
        printOutput(output);
    }

    @Override
    public void printOutput(String[] output) {
        for (String out:output
             ) {
            System.out.println(out);
        }
    }
}
