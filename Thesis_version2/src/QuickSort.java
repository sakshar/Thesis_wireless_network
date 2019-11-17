import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Laboni on 3/16/2018.
 */
public class QuickSort {
    int partition(ArrayList<SensorWithOrientation>targets,int low,int high)
    {
        //System.out.println("in quick sort "+targets.size());
        int pivot=targets.get(high).numberOftagetsCovered;
        int i=low-1;
        for(int j=low;j<high;j++)
        {
            if(targets.get(j).numberOftagetsCovered>=pivot)
            {
               i++;
               SensorWithOrientation temp=targets.get(j);
               targets.remove(j);
               targets.add(j,targets.get(i));
               targets.remove(i);
               targets.add(i,temp);
            }
        }
        if(high!=i+1)
        {
            SensorWithOrientation temp=targets.get(high);
            targets.remove(high);
            targets.add(high,targets.get(i+1));
            targets.remove(i+1);
            targets.add(i+1,temp);
        }
        return i+1;
    }
    void sort(ArrayList<SensorWithOrientation>targets,int low,int high)
    {
       // System.out.println("in sort "+targets.size());
        if(low<high)
        {
            int p=partition(targets,low,high);
            sort(targets,0,p-1);
            sort(targets,p+1,high);
        }
    }
    void print(ArrayList<SensorWithOrientation>sensorWithOrientationArrayList)
    {
        System.out.println("size "+sensorWithOrientationArrayList.size());
        for(int i=0;i<sensorWithOrientationArrayList.size();i++)
        {
            System.out.print("id "+sensorWithOrientationArrayList.get(i).sensorID+" ori "+sensorWithOrientationArrayList.get(i).orientation+" total "+sensorWithOrientationArrayList.get(i).numberOftagetsCovered);
            System.out.print(" (");;
            for(int j=0;j<sensorWithOrientationArrayList.get(i).numberOftagetsCovered;j++)
            {

                    System.out.print(sensorWithOrientationArrayList.get(i).targetArrayList.get(j).targetID+" ,");
            }
            System.out.println(" ) ");
        }



    }
}
