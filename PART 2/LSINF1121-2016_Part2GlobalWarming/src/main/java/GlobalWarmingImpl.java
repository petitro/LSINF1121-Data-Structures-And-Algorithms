import java.util.*;
import java.util.Arrays;
import java.util.Comparator;

public class GlobalWarmingImpl extends GlobalWarming {


    //technique, mettre en 1D puis de nouveau en 2D
    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        // expected pre-processing time in the constructror : O(n^2 log(n^2))

        int count=0;

        for (int m = 0; m < altitude.length; m++){
            count += altitude[m].length;
        }
        int [] altitude1D =  new int[count];

        int count2 = 0;

        for (int m = 0; m < altitude.length; m++){
            System.arraycopy (altitude[m], 0, altitude1D, count2, altitude[m].length);
            // Copy 2D array's contents to 1D array elements
            count2 += altitude[m].length;
        }

        Arrays.sort(altitude1D);

        for (int i = 0; i < altitude.length; i++){
            for (int j = 0; j < altitude[i].length; j++){
                altitude[i][j]=altitude1D[i*altitude.length+j];
            }
        }

    }


    public int nbSafePoints(int waterLevel) {
        // expected time complexity O(log(n^2))
        int result=0;
        //toujours à la fin
        for (int i=altitude.length-1; i>=0; i--){
            if(altitude[i][0]>waterLevel){
                result += altitude[i].length;
            }
            else{
                for (int j=altitude[i].length-1; j>=0; j--){
                    if(altitude[i][j]<=waterLevel){break;}
                    result++;
                }
                break;
            }
        }


        //System.out.println("my result " +result + " , for a waterLevel of "+waterLevel);
        return result;
    }



}