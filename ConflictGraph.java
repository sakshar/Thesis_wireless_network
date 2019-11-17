import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Laboni on 3/24/2018.
 */
public class ConflictGraph {
    MultiEdge[][] graphMatrix;
    int numberOfSensors;
    public ConflictGraph(int numberOfSensors)
    {
        this.numberOfSensors=numberOfSensors;
        graphMatrix=new MultiEdge[numberOfSensors][numberOfSensors];
        for(int i=0;i<numberOfSensors;i++)
        {
            for(int j=0;j<numberOfSensors;j++)
            {
                if(j==i||i>j)
                    graphMatrix[i][i]=null;
                else
                    graphMatrix[i][j]=new MultiEdge();
            }

        }
    }
    public void GraphModeling(Deployment deployment,int N)
    {
      /*  for(Target target:deployment.targetArrayList) {
            System.out.println("Target id " + target.targetID);
            for (int i = 0; i < target.sensorOrientations.size(); i++) {
                System.out.print("(sensor " + target.sensorOrientations.get(i).SensorId + ", ori " + target.sensorOrientations.get(i).Orientation+" )");
            }
            System.out.println();
        }*/
        //System.out.println("size "+graphMatrix.length);
       for(Target target:deployment.targetArrayList)
       {
           for(int i=0;i<target.sensorOrientations.size();i++)
           {
               for(int j=0;j<target.sensorOrientations.size();j++)
               {
                   int s1=target.sensorOrientations.get(i).SensorId;
                   int s2=target.sensorOrientations.get(j).SensorId;
                   int ori1=i;
                   int ori2=j;
                 //  System.out.println("====target id "+target.targetID+"========"+" sen1 "+s1+" sen2 "+s2);
                   if(s1==s2||s1>s2)
                       continue;
                   if(s1>s2)
                   {
                       int temp=s2;
                       s2=s1;
                       s1=temp;
                       int temp1=ori1;
                       ori1=ori2;
                       ori2=temp1;
                   }
                  // System.out.println("Ori1 "+target.sensorOrientations.get(ori1).Orientation+" Ori2 "+target.sensorOrientations.get(ori2).Orientation);
                   int key=target.sensorOrientations.get(ori1).Orientation*N+target.sensorOrientations.get(ori2).Orientation;
                   graphMatrix[s1][s2].totalConflict++;
                   //System.out.println("key is "+key+" in s1 "+s1+" s2 "+s2);
                   if(graphMatrix[s1][s2].edgeIntegerHashMap.get(key)!=null){
                      // System.out.println("check "+graphMatrix[s1][s2].edgeIntegerHashMap.get(key).intValue());
                       int p=graphMatrix[s1][s2].edgeIntegerHashMap.get(key).intValue();
                     //  System.out.print("before value "+p);
                       graphMatrix[s1][s2].edgeIntegerHashMap.put(key,p+1);
                       //System.out.println("after value "+graphMatrix[s1][s2].edgeIntegerHashMap.get(key));
                   }
                   //System.out.println("before "+graphMatrix[s1][s2].edgeIntegerHashMap.get(key));
                  else
                   {
                       graphMatrix[s1][s2].edgeIntegerHashMap.put(key,1);
                   }
                   //System.out.println("in s1 "+s1+" s2 "+s2+" val "+graphMatrix[s1][s2].edgeIntegerHashMap.get(key));

               }
           }
       }
       //System.out.println("finished");
       for(int i=0;i<numberOfSensors;i++)
       {
           for(int j=i+1;j<numberOfSensors;j++)
           {
              // if(graphMatrix[i][j]==null)continue;
               System.out.print("Sensor1 "+i+" Sensor2 "+j);
               System.out.println("  Total conflict : "+graphMatrix[i][j].totalConflict);
               Set set=graphMatrix[i][j].edgeIntegerHashMap.entrySet();
               Iterator iterator=set.iterator();
               while (iterator.hasNext())
               {
                   Map.Entry mentry=(Map.Entry)iterator.next();
                   int key=(int)mentry.getKey();
                   System.out.print("( Ori1: "+key/N+" Ori2: "+key%N);
                   System.out.println(" Value: "+mentry.getValue()+" ) ");
               }
           }
       }
        //System.out.println("finished 2");

    }
}
