import sun.management.Sensor;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Asus on 7/16/2018.
 */
public class GraphData {
    public static void main(String[] args) {
        int[] sensors = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400};
        int[] targets = {225};
        //int[] targets = {25, 50, 75, 100, 125, 150, 175, 200, 225};
        //int[] sensors = {300};
        try{
            File fileSensor = new File("data_varying_sensor\\new_result_max_conflict_targetoriented.txt");
            //File fileSensor = new File("E:\\My_Level_4_Term_2\\CSE_400_Thesis\\Thesis_version2\\sensorPositions.txt");
            BufferedReader brSensor = new BufferedReader(new FileReader(fileSensor));

            while(true) {
                int flag3 = 0;
                for(int target: targets){
                    int flag2 = 0;
                    for(int sensor: sensors){
                        File result1 = new File("Final Data\\Varying_Sensor_Target_225\\TMxCHSE\\targetCoverageRatio.txt");
                        BufferedWriter bw1 = new BufferedWriter(new FileWriter(result1, true));
                        File result2 = new File("Final Data\\Varying_Sensor_Target_225\\TMxCHSE\\sensorUsageRatio.txt");
                        BufferedWriter bw2 = new BufferedWriter(new FileWriter(result2, true));
                        File result3 = new File("Final Data\\Varying_Sensor_Target_225\\TMxCHSE\\targetPerSensor.txt");
                        BufferedWriter bw3 = new BufferedWriter(new FileWriter(result3, true));
                        //System.out.println("sensor "+sensor);
                        int flag = 0;
                        String[] lines = new String[3];
                        double targetsCovered_sum = 0.0;
                        double sensorUsed = 0.0;
                        for (int i = 0; i < 5; i++) {
                            lines[0] = brSensor.readLine();
                            if (lines[0] == null){
                                flag = 1;
                                flag2 = 1;
                                flag3 = 1;
                                break;
                            }
                            lines[1] = brSensor.readLine();
                            lines[2] = brSensor.readLine();
                            targetsCovered_sum += Double.parseDouble(lines[1].split(" ")[2]);
                            sensorUsed += Double.parseDouble(lines[2].split(",")[0].split(" ")[1]);
                            //System.out.println(i+"------------copying sensor positions---------");
                        }
                        if(flag == 1)
                            break;
                        System.out.println(targetsCovered_sum+" "+sensorUsed);
                        //bw1.append("habijabi");

                        //bw1.append(String.valueOf((targetsCovered_sum/5.0)/target));
                        bw1.append(String.format("%f",(targetsCovered_sum/5.0)/target));
                        bw1.newLine();
                        bw1.close();
                        //bw2.append(String.valueOf((sensorUsed/5.0)/sensor));
                        bw2.append(String.format("%f",(sensorUsed/5.0)/sensor));
                        bw2.newLine();
                        bw2.close();
                        //bw3.append(String.valueOf(Math.round(1.0*targetsCovered_sum/sensorUsed)));
                        bw3.append(String.format("%f",1.0*targetsCovered_sum/sensorUsed));
                        bw3.newLine();
                        bw3.close();
                    }
                    if(flag2 == 1)
                        break;
                }
                if(flag3 == 1)
                    break;
            }
            brSensor.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
