package nc.model.network;

/**
 * Created by samok on 15.05.2017.
 */
 class HelpNode {
    private int weight;
    private int parentID;
    private int ID;

    public HelpNode(int ID) {
        weight = 10000;
        parentID = -10;
        this.ID = ID;
    }

    static HelpNode getMinWeightNode(HelpNode[] arr) {
        int temp = 100000;
        HelpNode result = null;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==null)continue;
            if (temp > arr[i].weight) {
                temp = arr[i].weight;
                result = arr[i];
            }
        }
        return result;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getID() {
        return ID;
    }
}