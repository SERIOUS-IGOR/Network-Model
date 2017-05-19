package nc.model;

/**
 * Created by samok on 17.04.2017.
 */
public class IPadress implements Cloneable {
    private String ip;
    public IPadress(){
        ip = "0.0.0.0";
    }
    public IPadress(String ip){
        this.ip = ip;
    }
    public String getIp() {
        String another = ip;
        return another;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        String another = ip;
        return another;
    }
}
