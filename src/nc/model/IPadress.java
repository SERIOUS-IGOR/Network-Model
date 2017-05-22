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
    public static boolean isIpCorrect(String ip){
        if(ip==null){throw new IllegalArgumentException("Incorrect IP: IP cant be null");}
        String[] temp = ip.split("\\.");
        if(temp.length!=4){throw new IllegalArgumentException("Incorrect IP: "+ip);}
        if(temp[0].equals("0")){throw new IllegalArgumentException("Incorrect IP: "+ip+", IP cant start with 0");}
        for (String str:temp
             ) {
            if(Integer.parseInt(str)>254|| Integer.parseInt(str)<0){throw new IllegalArgumentException("Incorrect IP: "+ip);}
        }
        return true;
    }
}
