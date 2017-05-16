package NC.Model;

/**
 * Created by samok on 13.05.2017.
 */
public class RouteNotFoundExeption extends RuntimeException {
    RouteNotFoundExeption(){super();}
    RouteNotFoundExeption(String message){super(message);}
    RouteNotFoundExeption(String message, Throwable cause){super(message,cause);}
}
