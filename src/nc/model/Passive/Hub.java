package nc.model.Passive;

/**
 * Created by samok on 13.05.2017.
 */
public class Hub extends PassiveElement {
   private static final Hub hub = new Hub(10.0,2.0);

   private Hub(double cost,double delay){
      super(cost, delay);
   }

   public static Hub getInstance(){
      return hub;
   }
}
