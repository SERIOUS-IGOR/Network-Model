package nc.model.exeptions;

/**
 * Created by samok on 13.05.2017.
 */
public class RouteNotFoundExeption extends RuntimeException {
   public RouteNotFoundExeption(){super();}
   public RouteNotFoundExeption(String message){super(message);}
   public RouteNotFoundExeption(String message, Throwable cause){super(message,cause);}
}
