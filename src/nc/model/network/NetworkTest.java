package nc.model.network;

import nc.model.active.ActiveElement;
import nc.model.PathElement;
import nc.model.exeptions.RouteNotFoundExeption;

import java.util.*;

/**
 * Created by samok on 01.05.2017.
 */
 public class NetworkTest extends Network {


   public static int[][] netMatrix(Network testNet) {
       HashMap<Integer,PathElement> pathElements = testNet.getPathElements();
        int[][] arr = new int[pathElements.size()][pathElements.size()];
        Set<Map.Entry<Integer, PathElement>> setHelper = pathElements.entrySet();
        int check;
        for (Map.Entry entry : setHelper) {
            check = testNet.containsIP(((ActiveElement) entry.getValue()).getDefaultGateway());
            if (check == (int) entry.getKey()) {
                continue;
            } else if (check > 0) {
                arr[((int) entry.getKey()) - 1][check - 1] = 1;
                arr[check - 1][((int) entry.getKey()) - 1] = 1;
            }
        }
        return arr;
    }
    static double[][] netMatrixByCost(Network testNet) {
        HashMap<Integer,PathElement> pathElements = testNet.getPathElements();
        double[][] arr = new double[pathElements.size()][pathElements.size()];
        Set<Map.Entry<Integer, PathElement>> setHelper = pathElements.entrySet();
        int check;
        for (Map.Entry entry : setHelper) {
            check = testNet.containsIP(((ActiveElement) entry.getValue()).getDefaultGateway());
            if (check == (int) entry.getKey()) {
                continue;
            } else if (check > 0) {
                arr[((int) entry.getKey()) - 1][check - 1] = ((ActiveElement) entry.getValue()).getFullCost();
                arr[check - 1][((int) entry.getKey()) - 1] = ((ActiveElement) entry.getValue()).getFullCost();
            }
        }
        return arr;
    }
    static double[][] netMatrixByDelay(Network testNet) {
        HashMap<Integer,PathElement> pathElements = testNet.getPathElements();
        double[][] arr = new double[pathElements.size()][pathElements.size()];
        Set<Map.Entry<Integer, PathElement>> setHelper = pathElements.entrySet();
        int check;
        for (Map.Entry entry : setHelper) {
            check = testNet.containsIP(((ActiveElement) entry.getValue()).getDefaultGateway());
            if (check == (int) entry.getKey()) {
                continue;
            } else if (check > 0) {
                arr[((int) entry.getKey()) - 1][check - 1] = ((ActiveElement) entry.getValue()).getFullDelay();
                arr[check - 1][((int) entry.getKey()) - 1] = ((ActiveElement) entry.getValue()).getFullDelay();
            }
        }
        return arr;
    }

    static void arrprint(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println("__________________________");
    }
    static void arrprint(double arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println("__________________________");
    }

    public static Collection<PathElement> getPath(Network testNet, ActiveElement start, ActiveElement end) {
        HashMap<Integer,PathElement> pathElements = testNet.getPathElements();
        if ((testNet.containsIP(start.getIp()) == 0 | testNet.containsIP(end.getIp()) == 0) ||
                (testNet.containsIP(start.getIp()) == 0 || testNet.containsIP(end.getIp()) == 0))
            throw new RouteNotFoundExeption("This elements is in difference networks");
        else {
            int[][] matrix = netMatrix(testNet);
            HelpNode resultArr[]= getArcsByMatrix(matrix,start,end);
            int pathIndex=0;
            ArrayList<PathElement> Path = new ArrayList<>();
            Path.add(pathIndex,end);
            boolean flag = true;
            while (flag){
                int i=0;
                for(int j = i;j< resultArr.length;j++){
                    if(resultArr[j].getID()==Path.get(pathIndex).getID()){
                        Path.add(++pathIndex,pathElements.get(resultArr[j].getParentID()));
                        if(Path.get(pathIndex).getID()==start.getID()){
                            flag=false;
                            break;
                        }
                    }
                }
            }
            return Path;
        }
    }
    public static Collection<PathElement> getPathByCost(Network testNet, ActiveElement start, ActiveElement end) {
        HashMap<Integer,PathElement> pathElements = testNet.getPathElements();
        if ((testNet.containsIP(start.getIp()) == 0 | testNet.containsIP(end.getIp()) == 0) ||
                (testNet.containsIP(start.getIp()) == 0 || testNet.containsIP(end.getIp()) == 0))
            throw new RouteNotFoundExeption("This elements not in one network");
        else if(start==end)throw new RouteNotFoundExeption("Start point = and point");
        else {
            double[][] matrix = netMatrixByCost(testNet);
            HelpNode resultArr[]= getArcsByMatrix(matrix,start,end);
            int pathIndex=0;
            ArrayList<PathElement> Path = new ArrayList<>();
            Path.add(pathIndex,end);
            boolean flag = true;
            while (flag){
                int i=0;
                for(int j = i;j< resultArr.length;j++){
                    if(resultArr[j].getID()==Path.get(pathIndex).getID()){
                        Path.add(++pathIndex,pathElements.get(resultArr[j].getParentID()));
                        if(Path.get(pathIndex).getID()==start.getID()){
                            flag=false;
                            break;
                        }
                    }
                }
            }
            return Path;
        }
    }
    public static Collection<PathElement> getPathByDelay(Network testNet, ActiveElement start, ActiveElement end) {
        HashMap<Integer,PathElement> pathElements = testNet.getPathElements();
        if ((testNet.containsIP(start.getIp()) == 0 | testNet.containsIP(end.getIp()) == 0) ||
                (testNet.containsIP(start.getIp()) == 0 || testNet.containsIP(end.getIp()) == 0))
            throw new RouteNotFoundExeption("This elements is in difference networks");
        else {
            double[][] matrix = netMatrixByDelay(testNet);
            HelpNode resultArr[]= getArcsByMatrix(matrix,start,end);
            int pathIndex=0;
            ArrayList<PathElement> Path = new ArrayList<>();
            Path.add(pathIndex,end);
            boolean flag = true;
            while (flag){
                int i=0;
                for(int j = i;j< resultArr.length;j++){
                    if(resultArr[j].getID()==Path.get(pathIndex).getID()){
                        Path.add(++pathIndex,pathElements.get(resultArr[j].getParentID()));
                        if(Path.get(pathIndex).getID()==start.getID()){
                            flag=false;
                            break;
                        }
                    }
                }
            }
            return Path;
        }
    }
    private static HelpNode[] getArcsByMatrix(int[][] matrix, ActiveElement start, ActiveElement end){
        HelpNode[] tempArr = new HelpNode[matrix.length];
        for (int i = 0; i < tempArr.length; i++) {
            tempArr[i] = new HelpNode(i + 1);
        }
        tempArr[start.getID() - 1].setWeight(0);
        tempArr[end.getID() - 1].setWeight(9999);
        HelpNode[] resultArr = tempArr.clone();
        while (true) {
            HelpNode worksOn= HelpNode.getMinWeightNode(tempArr);
            if (worksOn==null)break;
            if (worksOn.getWeight() == 9999) break;
            int i = worksOn.getID()-1;
            for(int j = 0;j<matrix.length;j++){
                if(matrix[i][j]>0){
                    if(worksOn.getWeight()+matrix[i][j]<resultArr[j].getWeight()){
                        resultArr[j].setWeight(worksOn.getWeight()+matrix[i][j]);
                        resultArr[j].setParentID(worksOn.getID());
                    }
                }
            }
            tempArr[worksOn.getID()-1]=null;
        }
        return resultArr;
    }
    private static HelpNode[] getArcsByMatrix(double[][] matrix, ActiveElement start, ActiveElement end){
        HelpNode[] tempArr = new HelpNode[matrix.length];
        for (int i = 0; i < tempArr.length; i++) {
            tempArr[i] = new HelpNode(i + 1);
        }
        tempArr[start.getID() - 1].setWeight(0);
        tempArr[end.getID() - 1].setWeight(9999);
        HelpNode[] resultArr = tempArr.clone();
        while (true) {
            HelpNode worksOn= HelpNode.getMinWeightNode(tempArr);
            if (worksOn==null)break;
            if (worksOn.getWeight() == 9999) break;
            int i = worksOn.getID()-1;
            for(int j = 0;j<matrix.length;j++){
                if(matrix[i][j]>0){
                    if(worksOn.getWeight()+matrix[i][j]<resultArr[j].getWeight()){
                        resultArr[j].setWeight( (int)Math.round(worksOn.getWeight()+matrix[i][j]));
                        resultArr[j].setParentID(worksOn.getID());
                    }
                }
            }
            tempArr[worksOn.getID()-1]=null;
        }
        return resultArr;
    }
}
