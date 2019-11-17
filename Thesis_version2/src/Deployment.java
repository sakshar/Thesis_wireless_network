import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Laboni on 1/30/2018.
 */
public class Deployment extends ArrayList<Target> {
    int length;
    int width;
    int numberOfSensors;
    int numberOfTargets;
    ArrayList<Sensor>sensorArrayList=new ArrayList<>();
    ArrayList<Target>targetArrayList=new ArrayList<>();

    public Deployment(int length,int width,int numberOfSensors,int numberOfTargets)
    {
        this.length=length;
        this.width=width;
        this.numberOfSensors=numberOfSensors;
        this.numberOfTargets=numberOfTargets;
    }
    public void randomDeploymentSensors(double range,double theta)
    {
        ArrayList<Point> sensorPositions = new ArrayList<>();
        Random random=new Random();
        for(int i=0;i<numberOfSensors;i++)
        {
            Sensor sensor=new Sensor();
            sensor.sensorID=i;
            sensor.range=range;
            sensor.theta=theta;
            double x = random.nextInt(length);
            double y = random.nextInt(width);
            while(sensorPositions.contains(new Point(x, y))){
                x = random.nextInt(length);
                y = random.nextInt(width);
            }
            sensor.x = x;
            sensor.y = y;
            sensorArrayList.add(sensor);
           // System.out.println(sensor);
        }
    }
    public void randomDeploymentTargets()
    {
        ArrayList<Point> targetPositions = new ArrayList<>();
        Random random=new Random();
        for(int i=0;i<numberOfTargets;i++)
        {
            Target target=new Target();
            target.targetID=i;
            double x = random.nextInt(length);
            double y = random.nextInt(width);
            while(targetPositions.contains(new Point(x, y))){
                x = random.nextInt(length);
                y = random.nextInt(width);
            }
            target.x = x;
            target.y = y;
            targetArrayList.add(target);
           // System.out.println(target);
        }
    }

    public void deploySensorsTargets(BufferedReader brSensor, BufferedReader brTarget, double range, double theta){
        String line;
        try {
            for (int i = 0; i < numberOfSensors; i++) {
                line = brSensor.readLine();
                //System.out.println(i+"----------deploying sensors---------");
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                Sensor sensor=new Sensor();
                sensor.x = Double.parseDouble(stringTokenizer.nextToken());
                sensor.y = Double.parseDouble(stringTokenizer.nextToken());
                sensor.sensorID=i;
                sensor.range=range;
                sensor.theta=theta;
                sensorArrayList.add(sensor);
            }
            for(int i = 0; i < numberOfTargets; i++){
                line = brTarget.readLine();
                //System.out.println(i+"----------deploying targets---------");
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                Target target = new Target();
                target.x = Double.parseDouble(stringTokenizer.nextToken());
                target.y = Double.parseDouble(stringTokenizer.nextToken());
                target.targetID=i;
                targetArrayList.add(target);
            }
        }
        catch (Exception e){
            System.err.println(e.getStackTrace());
        }
    }
}
