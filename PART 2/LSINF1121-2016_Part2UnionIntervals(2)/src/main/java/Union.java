import java.util.ArrayList;
import java.util.Arrays;

public class Union {

    public static Interval [] union(Interval [] intervals) {
        if (intervals.length < 1){ return intervals;}
        Arrays.sort(intervals);
        int min = intervals[0].getMin(), max = intervals[0].getMax();
        ArrayList<Interval> result = new ArrayList<Interval>();
        for(int i = 1; i < intervals.length; i++){
            //System.out.println(intervals[i].toString());
            if(intervals[i].getMin() > max){
                //System.out.println("new interval");
                result.add(new Interval(min,max));
                min = intervals[i].getMin();
                max = intervals[i].getMax();
            }
            else{
                max = Math.max(max, intervals[i].getMax());
            }
        }
        result.add(new Interval(min,max));
        Interval[] end = result.toArray(new Interval[0]);
        return end;

    }
}