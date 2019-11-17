import java.util.*;

/**
 * Created by Laboni on 1/30/2018.
 */
public class Main {
    public static void main(String[] args) {
        int numberOfSensors,numberOfTargets,length;
        double range,theta;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Number of Sensors: ");
        numberOfSensors=scanner.nextInt();
        System.out.println("Number of Targets: ");
        numberOfTargets=scanner.nextInt();
        System.out.println("Length: ");
        length=scanner.nextInt();
        System.out.println("Range: ");
        range=scanner.nextInt();
        System.out.println("Theta: ");
        theta=scanner.nextInt();
        Deployment deployment=new Deployment(length,length,numberOfSensors,numberOfTargets);
        deployment.randomDeploymentSensors(range,theta);
        deployment.randomDeploymentTargets();
        double d = 360/theta;
        int n = (int)  d; //Number of sectors in a sensor
        ArrayList<Target>targetArrayListCopy=new ArrayList<>();
        ArrayList<Target>targetArrayListCopy1=new ArrayList<>();
        for(Target target: deployment.targetArrayList)
        {
            targetArrayListCopy.add(target);
            targetArrayListCopy1.add(target);
        }
        for (Sensor sensor: deployment.sensorArrayList) {
            sensor.inRangeTargets = new ArrayList[n];
            for(int i = 0; i<n; i++)
                sensor.inRangeTargets[i] = new ArrayList<>();
            for(Target target: deployment.targetArrayList){
               // targetArrayListCopy.add(target);
                for(int i = 0; i<n; i++) {
                    boolean b = sensor.targetInSector(target, i);
                   // System.out.println("sector "+i+": "+b);
                    if (sensor.targetInSector(target,i))
                    {
                        //System.out.println(sensor.targetInSector(target,i));
                        sensor.inRangeTargets[i].add(target);
                        SensorOrientation sensorOrientation=new SensorOrientation();
                        sensorOrientation.SensorId=sensor.sensorID;
                        sensorOrientation.Orientation=i;
                        target.sensorOrientations.add(sensorOrientation);
                    }
                }
            }
        }
        //For Greedy Approach Array making
      //  SensorWithOrientation[]sensorWithOrientations=new SensorWithOrientation[numberOfSensors*n];
        ArrayList<SensorWithOrientation>sensorWithOrientationArrayList=new ArrayList<>();
        ArrayList<Sensor>sensorList=new ArrayList<>();
        for (Sensor sensor: deployment.sensorArrayList) {
            System.out.println("sensor id "+sensor.sensorID);
            sensorList.add(sensor);
            for(int i=0;i<n;i++) {
                    SensorWithOrientation sensorWithOrientation=new SensorWithOrientation();
                    sensorWithOrientation.sensorID=sensor.sensorID;
                    sensorWithOrientation.numberOftagetsCovered = sensor.inRangeTargets[i].size();
                   // sensorWithOrientation.targetList=new Target[sensorWithOrientation.numberOftagetsCovered];
                    sensorWithOrientation.targetArrayList=new ArrayList<>();
                    //for printing target id
                for(int j=0;j<sensor.inRangeTargets[i].size();j++)
                {
                 //   System.out.print("target in sector "+i+" : "+sensor.inRangeTargets[i].get(j).targetID+" ");
                   // sensorWithOrientation.targetList[j]=sensor.inRangeTargets[i].get(j);
                    sensorWithOrientation.targetArrayList.add(sensor.inRangeTargets[i].get(j));
                }

              //  System.out.println();
                //printing target id
                    sensorWithOrientation.orientation = i;
                    sensorWithOrientationArrayList.add(sensorWithOrientation);
            }
        }
        QuickSort quickSort=new QuickSort();
        quickSort.print(sensorWithOrientationArrayList);
        //======================for conflict approach======================================
        ConflictGraph conflictGraph=new ConflictGraph(numberOfSensors);
        conflictGraph.GraphModeling(deployment,n);
        //int targetsCoveredInConflictGraph=0;
        while(targetArrayListCopy1.size()!=0&&sensorList.size()!=0)
        {
            //int max=-9999;
            int min=9999;
            int orientationKey=-5;
            int s1=-5;
            for(int i=0;i<numberOfSensors;i++)
            {
                for(int j=i+1;j<numberOfSensors;j++)
                {
                    if(conflictGraph.graphMatrix[i][j]==null)continue;
                    Set set=conflictGraph.graphMatrix[i][j].edgeIntegerHashMap.entrySet();
                    Iterator iterator=set.iterator();
                    while (iterator.hasNext())
                    {
                        Map.Entry mentry=(Map.Entry)iterator.next();
                        int key=(int)mentry.getKey();
                        if(min>(int)mentry.getValue())
                        {
                            min=(int)mentry.getValue();
                            orientationKey=key;
                            s1=i;
                        }
                    }
                }


            }
           // if(max>0)
            if(orientationKey>0)
            {

                System.out.println("Selected sensor "+s1+" orientation "+orientationKey/n+" max conflict "+min);
                int ori=orientationKey/n;
                //  targetsCoveredInConflictGraph=targetsCoveredInConflictGraph+deployment.sensorArrayList.get(i).inRangeTargets[ori].size();
                for(int t=0;t<deployment.sensorArrayList.get(s1).inRangeTargets[ori].size();t++)
                {
                    targetArrayListCopy1.remove(deployment.sensorArrayList.get(s1).inRangeTargets[ori].get(t));
                }
                sensorList.remove(deployment.sensorArrayList.get(s1));
                for(int check=s1+1;check<numberOfSensors;check++)
                    conflictGraph.graphMatrix[s1][check]=null;
                //System.out.println("number of targets covered "+targetsCoveredInConflictGraph);
            }
            else
            {
                System.out.println("now removing "+sensorList.get(0).sensorID);
                for(int t=0;t<sensorList.get(0).inRangeTargets[0].size();t++)
                {
                    targetArrayListCopy1.remove(sensorList.get(0).inRangeTargets[0].get(t));
                }
                sensorList.remove(0);
            }
        }

        System.out.println("targets covered "+(numberOfTargets-targetArrayListCopy1.size()));
        System.out.println("Remaining uncovered "+targetArrayListCopy1.size());
        //end of conflict approach

        //******Greedy Approach*****
        quickSort.sort(sensorWithOrientationArrayList,0,sensorWithOrientationArrayList.size()-1);
        System.out.println("================Sorted===================");
        quickSort.print(sensorWithOrientationArrayList);
        int totalCoveredTargets=0;
		int sensorUsed=0;
        while(true)
        {
            totalCoveredTargets=totalCoveredTargets+sensorWithOrientationArrayList.get(0).numberOftagetsCovered;
			//newly added for the used sensor count
			if(sensorWithOrientationArrayList.get(0).numberOftagetsCovere!=0)
			sensorUsed++;
			//==========================================
            SensorWithOrientation sensorWithOrientation=sensorWithOrientationArrayList.get(0);
            Sensor sensor=deployment.sensorArrayList.get(sensorWithOrientationArrayList.get(0).sensorID);
            System.out.println("Selected Sensor "+sensor.sensorID);
            int orientation=sensorWithOrientationArrayList.get(0).orientation;
            int targetsCovered=sensorWithOrientationArrayList.get(0).numberOftagetsCovered;
            int s=sensorWithOrientationArrayList.size();
            ArrayList<Integer> indexes=new ArrayList<Integer>();
            for(int i=0;i<s;i++)
            {
                if(sensorWithOrientationArrayList.get(i).sensorID==sensor.sensorID)
                    indexes.add(i);
            }
          //  System.out.println("indexes size "+indexes.size());
            for(int i=0;i<indexes.size();i++)
            {
              //  System.out.println("index "+indexes.get(i));
                sensorWithOrientationArrayList.remove(indexes.get(i).intValue()-i);
              //  quickSort.print(sensorWithOrientationArrayList);
            }
            System.out.println("After removing "+sensor.sensorID);
            quickSort.print(sensorWithOrientationArrayList);
            System.out.println("Targets covered "+targetsCovered+" from sensor "+sensor.sensorID);
            s=sensorWithOrientationArrayList.size();
           // System.out.println("-------------------problem zone-----------------------------");
            for(int i=0;i<targetsCovered;i++)
            {
              //  System.out.println("target from removed sensor :: "+sensorWithOrientation.targetList[i].targetID);
              //  System.out.println("target from removed sensor :: "+sensorWithOrientation.targetArrayList.get(i).targetID);
                for(int j=0;j<s;j++)
                {
                   // System.out.print("checking "+sensorWithOrientationArrayList.get(j).sensorID+" orientation "+sensorWithOrientationArrayList.get(j).orientation);
                    boolean b=deployment.sensorArrayList.get(sensorWithOrientationArrayList.get(j).sensorID).targetInSector(sensorWithOrientation.targetArrayList.get(i),sensorWithOrientationArrayList.get(j).orientation);
                  //  System.out.println(" target present "+b);
                    if(b)
                    {
                        sensorWithOrientationArrayList.get(j).numberOftagetsCovered--;
                        sensorWithOrientationArrayList.get(j).targetArrayList.remove(sensorWithOrientation.targetArrayList.get(i));
                    }
                 //   quickSort.print(sensorWithOrientationArrayList);

                }
                //System.out.println("target removed "+sensor.inRangeTargets[orientation].get(i).targetID);
                //System.out.println("size of targetlist "+targetArrayListCopy.size()+" removed "+sensorWithOrientation.targetList[i].targetID);
                targetArrayListCopy.remove(sensorWithOrientation.targetArrayList.get(i));
                //System.out.println("after removing size of targetlist "+targetArrayListCopy.size());
            }
          //  System.out.println("---------------end problem zone----------------------------");
            System.out.println("After removing targets ");
            quickSort.print(sensorWithOrientationArrayList);
            if(targetArrayListCopy.size()==0||sensorWithOrientationArrayList.size()==0)
                break;
            quickSort.sort(sensorWithOrientationArrayList,0,sensorWithOrientationArrayList.size()-1);
            System.out.println("After sorting ");
            quickSort.print(sensorWithOrientationArrayList);
			
        }
        System.out.println("Total covered finally "+totalCoveredTargets);
        System.out.println("Total uncovered "+(numberOfTargets-totalCoveredTargets));
		System.out.println("sensors Used "+sensorUsed+" unused sensor "+(numberOfSensors-sensorUSed);
        //================================
        /* for (Sensor sensor: deployment.sensorArrayList) {
            System.out.println(sensor.sensorID+": ");
            for(int i = 0; i<n; i++) {
                System.out.print("Sector " +i+": ");
                for (Target target : sensor.inRangeTargets[i]) {
                    System.out.print(target.targetID + ", ");
                }
                System.out.println();
            }
            System.out.println();
        }*/
    }
}
