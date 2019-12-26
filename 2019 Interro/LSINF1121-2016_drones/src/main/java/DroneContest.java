import java.util.*;

public class DroneContest {
    /**
     * Given an array of participants (that starts their drones at a time given by drone.start (inclusive),
     * stops it at drone.end (exclusive) and goes at height drone.height),
     * output the heights changes for the use of Skeyes.
     *
     * The first drone takes off strictly somewhere after time 0.
     *
     *  The height changes must be as described on INGInious.
     *  Equivalently, as follows:
     *  - They must be ordered by time
     *  - The first height change must be at time 0, and at height 0.
     *  - The last height change must be at the time the last drone stops (and thus must be at height 0!)
     *  - Given two successive height changes A and B, the maximum height of any active drone between A.time (inclusive)
     *    and B.time (exclusive) is EXACTLY A.height (i.e. there exists a drone with this height active between these
     *    times). Moreover, A.height != B.height.
     */
    public static LinkedList<HeightChange> findHighest(Drone[] participants) {

        //sorting with complexity of N log(N)
        Drone [] startInc = participants.clone();
        Collections.shuffle(Arrays.asList(startInc));
         Arrays.sort(startInc, new Comparator<Drone>() {
            @Override
            public int compare(Drone d1, Drone d2) {
                return d1.start-d2.start;
            }
        });

        //sorting with hopefully a complexity of N
        Drone[] endInc =startInc.clone();
        InsertionSort(endInc);

        //all the current heights at time t
        LinkedList<Integer> currentDrone  = new LinkedList<Integer>();
        currentDrone.add(0);
        LinkedList<HeightChange> result = new LinkedList<>();

        int previousHeight = 0;
        int currentHeight = 0;

        int runnerStart = 0;
        int runnerEnd = 0;
        while(runnerStart < startInc.length || runnerEnd < endInc.length){

            int startTime;
            int endTime;
            if (runnerStart < startInc.length) startTime = startInc[runnerStart].start;
            else startTime = Integer.MAX_VALUE; //needed
            if (runnerEnd < endInc.length) endTime = endInc[runnerEnd].end;
            else endTime = Integer.MAX_VALUE;

            //add heights to the list
            while (startTime <= endTime && (runnerStart < startInc.length && startInc[runnerStart].start == startTime)){
                currentDrone.add(startInc[runnerStart].height);
                runnerStart++;
            }

            //delete height from the list
            while (endTime <= startTime && (runnerEnd < endInc.length && endTime == endInc[runnerEnd].end)) {
                //System.out.println("runnerEnd "+runnerEnd + ", height to remove " + endInc[runnerEnd].height );
                currentDrone.remove((Integer) endInc[runnerEnd].height);
                runnerEnd++;
            }

            //get the maximum height of the list
            currentHeight= Collections.max(currentDrone);

            //System.out.println("currentHeight " + currentHeight + ", previousHeight " + previousHeight);
            if (currentHeight != previousHeight) {
                //System.out.println("time "+ Math.min(startTime, endTime)+", height "+ currentHeight);
                result.add(new HeightChange(Math.min(startTime, endTime), currentHeight));
                previousHeight = currentHeight;
            }
        }
        //first point
        result.addFirst(new HeightChange(0,0));
        //result.addLast(new HeightChange(endInc[endInc.length-1].end, endInc[endInc.length-1].height));
        return result;
    }

    private static void InsertionSort(Drone[] a){
        int N = a.length;
        for(int i=1; i<N; i++){
            for (int j=i; j>0 && a[j].end < a[j-1].end; j--){
                Drone temp = new Drone(a[j].start , a[j].end, a[j].height);
                a[j]=a[j-1];
                a[j-1]=temp;
            }
        }
    }
}
