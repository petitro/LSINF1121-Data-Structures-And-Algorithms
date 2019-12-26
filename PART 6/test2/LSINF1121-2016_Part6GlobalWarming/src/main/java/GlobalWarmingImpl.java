import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

    boolean [] marked;
    int nbSafePointLeft;
    int rowNb;
    int rowLg;
    int size;

     /** In the following, we assume that the points are connected to
     * horizontal or vertical neighbors but not to the diagonal ones
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     * @param waterLevel is the water level, every entry <= waterLevel is flooded
    */
    public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
        super(altitude,waterLevel);
        // TODO
        rowNb=altitude.length;
        rowLg=altitude[0].length;
        size = rowLg*rowNb;

        marked = new boolean[size];
        nbSafePointLeft=nbSafePointsCorrect(waterLevel);

        for(int s =0; s<size; s++){
            if(altitude[row(s)][col(s)]>waterLevel)
                marked[s]=true;
        }
    }

    /**
     *
     * @param p1 a safe point with valid coordinates on altitude matrix
     * @param p2 a safe point (different from p1) with valid coordinates on altitude matrix
     * @return the shortest simple path (vertical/horizontal moves) if any between from p1 to p2 using only vertical/horizontal moves on safe points.
     *         an empty list if not path exists (i.e. p1 and p2 are not on the same island).
    */
    public List<Point> shortestPath(Point p1, Point p2) {
        // TODO
        // expected time complexity O(n^2)


        int P1=ind(p1.x, p1.y);
        int P2=ind(p2.x, p2.y);
        int [] distTo=new int[size]; //distTo point P1
        int [] edgeTo=new int[size];
        for (int i=0; i<distTo.length; i++){
            distTo[i]= Integer.MAX_VALUE;;
            edgeTo[i]= -1;;
        }
        distTo[P1]=0;
        edgeTo[P1]=P1;

        PriorityQueue<Entry> queue = new PriorityQueue<Entry>();
        queue.add(new Entry(P1, 0));

        while (!queue.isEmpty()){
            Entry e = queue.poll();
            int point = e.position;
            if(point==P2)
                break;

            //check on the left, marked, already seen
            //left
            int PL=point-1;
            if(point%rowLg!=0 && marked[PL] && distTo[point]+1<distTo[PL]){
                edgeTo[PL]=point;
                distTo[PL]=distTo[point]+1;
                queue.add(new Entry(PL, distTo[PL]));
            }

            //right
            int PR=point+1;
            if(point%rowLg!=rowLg-1 && marked[PR] && distTo[point]+1<distTo[PR]) {
                edgeTo[PR] = point;
                distTo[PR] = distTo[point] + 1;
                queue.add(new Entry(PR, distTo[PR]));
            }
            //top
            int PT= point-rowLg;
            if(point>rowLg && marked[PT] && distTo[point]+1<distTo[PT]) {
                edgeTo[PT] = point;
                distTo[PT] = distTo[point] + 1;
                queue.add(new Entry(PT, distTo[PT]));
            }
            //bottom
            int PB= point+rowLg;
            if(point<size-rowLg && marked[PB] && distTo[point]+1<distTo[PB]){
                edgeTo[PB]=point;
                distTo[PB]=distTo[point]+1;
                queue.add(new Entry(PB, distTo[PB]));
            }

        }

        if(p1.equals(p2))
            return null;

        if(distTo[P2]==Integer.MAX_VALUE)
            return null;

        LinkedList<Point> list = new LinkedList<>();
        int point=P2;
        while(point!=P1){
            list.addFirst(new Point(row(point), col(point)));
            point=edgeTo[point];
        }
        list.addFirst(p1);

        return list;
    }

    public int ind(int row,int col) {return row*rowLg + col;}

    public int row(int pos) { return pos / rowLg; }

    public int col(int pos) { return pos % rowLg; }


    private class Entry implements Comparable<Entry>{
        int position;
        int dist;
        //creation of vertex
        Entry(int position, int value) {
            this.position = position;
            this.dist = value;
        }
        //find best distance
        public int compareTo(Entry o) {
            return this.dist - o.dist;
        }
    }


}

