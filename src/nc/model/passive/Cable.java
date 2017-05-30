package nc.model.passive;

/**
 * Created by samok on 13.05.2017.
 */
public class Cable extends PassiveElement {
   private static final Cable cable = new Cable(10.0, 2.0);

   private Cable(double cost, double delay) {
      super(cost, delay);
   }

   public static Cable getInstance() {
      return cable;
   }
}