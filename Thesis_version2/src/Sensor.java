/**
 * Created by Laboni on 1/30/2018.
 */

import java.lang.Math;
import java.util.ArrayList;

public class Sensor {
    double x;
    double y;
    double range;
    int sensorID;
    double theta;
    ArrayList<Target>[] inRangeTargets;

    Sensor(){}

    Sensor(double x, double y, double range, int sensorID, double theta){
        this.x = x;
        this.y = y;
        this.range = range;
        this.sensorID = sensorID;
        this.theta = theta;
    }

    public boolean targetInSector(Target target, int i){
        /*if(Math.sqrt((x-target.x)*(x-target.x)+(y-target.y)*(y-target.y)) <= range)
            return true;
        return false;*/
        if(target==null)return false;
        Vector t = new Vector(new Point(target.x,target.y));
        Vector s = new Vector(new Point(this.x, this.y));

        Vector distance = t.minus(s);

       // System.out.println("Target: "+t+", Sensor: "+s+", distance"+distance);
        /*System.out.println(currentDirectionVector(i));
        System.out.println(currentDirectionVector(i).times(distance.magnitude()).dot(distance));
        System.out.println(currentDirectionVector(i).dot(distance));
        System.out.println(Math.cos(Math.toRadians(theta/2.0))*distance.magnitude()*distance.magnitude());
        System.out.println(distance.magnitude());
        System.out.println(range);*/
        if(currentDirectionVector(i).dot(distance) >= Math.cos(Math.toRadians(theta/2.0))*distance.magnitude()
                && distance.magnitude()<= range)
            return true;
        return false;
    }

    public Vector currentDirectionVector(int i){
        Point p = new Point();
        p.x = Math.cos(Math.toRadians(i*theta + theta/2.0));
        p.y = Math.sin(Math.toRadians(i*theta + theta/2.0));

        //return new Vector(p).direction();
        return new Vector(p);
    }

    public String toString()
    {
        return "id: "+sensorID+" x: "+x+" y: "+y+"\n";
    }
}
