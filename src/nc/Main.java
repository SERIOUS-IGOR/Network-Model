package nc;
import nc.control.*;
import nc.model.*;
import nc.view.*;

import javax.swing.text.View;
import java.util.Scanner;

/**
 * Created by samok on 17.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        ModelOutput model = new ModelOutput();
        Controller cont = new Controller(model);
        NetView view = new NetView(model);
        model.addObserver(view);
        cont.start();
    }
}
