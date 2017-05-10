package NC.Model;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * Created by samok on 01.05.2017.
 */
public class NetworkTest extends Network {
    public static void netHelper(Network testNet) {
        int[][] arr = new int[testNet.PathElements.size()][testNet.PathElements.size()];
        //Map.Entry entry = (Map.Entry) test.getPathElements().entrySet();
        Set<Map.Entry<Integer,PathElement>> setHelper = testNet.PathElements.entrySet();
        int check;
        for (Map.Entry entry:setHelper) {
            System.out.println("Working on: " + ((ActiveElement)entry.getValue()).getInfo() +
                    ", with ID: " + ((ActiveElement)entry.getValue()).getID());
            check = testNet.containsIP(((ActiveElement)entry.getValue()).getDefaultGateway());
            if (check==(int)entry.getKey()){
                System.out.println("Path element mustn't connect to itself");
                arrprint(arr);
                continue;
            }
            if(check>0){
               arr[((int)entry.getKey())-1][check-1]=1;
               arr[check-1][((int)entry.getKey())-1]=1;
            }
            arrprint(arr);
        }
        arrprint(arr);
    }

    static void arrprint(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]+" ");
            }
        }
        System.out.println();
        System.out.println("__________________________");
    }
}
