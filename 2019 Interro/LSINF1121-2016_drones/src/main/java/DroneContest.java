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

        Drone[] droneStartSort = participants.clone();
        Arrays.sort(droneStartSort, new Comparator<Drone>() {
            @Override
            public int compare(Drone d1, Drone d2) {
                return d1.start-d2.start;
            }
        });

        Drone[] droneEndSort = participants.clone();
        Arrays.sort(droneEndSort, new Comparator<Drone>() {
            @Override
            public int compare(Drone d1, Drone d2) {
                return d1.end-d2.end;
            }
        });

        TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer> ();

        LinkedList<HeightChange> result = new  LinkedList<HeightChange>();
        result.add(new HeightChange(0,0)); //in the question drone started at t=1

        int previousHeight = result.get(0).height;
        int currentHeight;
        int i = 0; //Runner for droneStartSort
        int j = 0; //Runner for droneEndSort

        while(i < droneStartSort.length || j < droneEndSort.length){
            int startTime = Integer.MAX_VALUE;
            int endTime = Integer.MAX_VALUE;

            if (i < droneStartSort.length) startTime = droneStartSort[i].start;
            if (j < droneEndSort.length) endTime = droneEndSort[j].end;

            //add new drones in the tree
            if (startTime <= endTime) {
                while (i < participants.length && droneStartSort[i].start == startTime) {
                    //check if height already exist in the tree
                    Integer count = tree.get(droneStartSort[i].height);
                    if (count == null) tree.put(droneStartSort[i].height, 1); //doesn't exist
                    else tree.put(droneStartSort[i].height, count + 1); //exist, so inc
                    i++;
                }
            }

            //remove drone form the tree
            if (endTime <= startTime) {
                while (j < participants.length && endTime == droneEndSort[j].end) {
                    Integer count = tree.get(droneEndSort[j].height);
                    if (count.equals(1)) tree.remove(droneEndSort[j].height);
                    else tree.put(droneEndSort[j].height, count - 1);
                    j++;
                }
            }

            if (!tree.isEmpty()) currentHeight = tree.lastKey();
            else currentHeight = 0; //case empty so 0 for sure

            if (currentHeight != previousHeight) {
                previousHeight = currentHeight;
                result.add(new HeightChange(Math.min(startTime, endTime), currentHeight));
            }
        }
        return result;
    }
}
