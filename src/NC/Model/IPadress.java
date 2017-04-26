package NC.Model;

/**
 * Created by samok on 17.04.2017.
 */
public class IPadress implements Cloneable {
    final static int IP_LENGHT = 4;
    byte[] IP;

    public IPadress() {
        this.IP = new byte[IP_LENGHT];
        for (int i : IP) {
            IP[i] = 0;
        }
    }
    public IPadress(byte addr[]) {
        this.IP = new byte[IP_LENGHT];
        if (addr != null) {
            if (addr.length == IP_LENGHT) {
                IP = addr;
            }
        }
        else throw new IllegalArgumentException();
    }
    public IPadress(String addr) {
        this.IP = new byte[IP_LENGHT];
        String [] temp = addr.split("\\.");
        for(int i = 0;i<IP_LENGHT;i++){
            IP[i]= (byte)Integer.parseInt(temp[i]);
        }
    }
    public IPadress clone() {
            IPadress clone = new IPadress();
            for(int i=0;i < IP_LENGHT;i++){
                clone.IP[i]=IP[i];
            }
            return clone;
    }

    @Override
    public String toString() {
        return (IP[0] & 0xff) + "." + (IP[1] & 0xff) + "." + (IP[2] & 0xff) + "." + (IP[3] & 0xff);
    }


}
