import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Laboni on 1/30/2018.
 */
public class Target {
    double x;
    double y;
    int targetID;
    //ArrayList<GraphNode> sensorWithOrientation;
    ArrayList<SensorOrientation> sensorOrientations=new ArrayList<>();
    HashMap<Integer,SensorOrientation>sensorOrientationHashMap=new HashMap<>();

    Target(){
        //sensorWithOrientation = new ArrayList<>();
    }

    Target(double x, double y, int targetID){
        this.x = x;
        this.y = y;
        this.targetID = targetID;
        //this.sensorWithOrientation = new ArrayList<>();
    }

    @Override
    public boolean equals(Object object){
        if (object != null && object instanceof Target)
        {
            if(this.targetID == ((Target) object).targetID )
                return true;
        }
        return false;
    }

    public String toString()
    {
        return "ID: "+targetID+" x: "+x+" y: "+y+"\n";
    }
}
