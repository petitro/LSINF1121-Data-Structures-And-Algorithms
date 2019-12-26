import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Maze {
    public static Iterable<Integer> shortestPath(int [][] maze,  int x1, int y1, int x2, int y2) {
        //TODO
        //Dijkstra's algorithm
        LinkedList<Integer> ll = new LinkedList<Integer>(); //list to be returned
        if(maze[x1][y1] == 1 || maze[x2][y2] == 1) //check for a wall
            return ll;
        PriorityQueue<Entry> PQ = new PriorityQueue<Entry>();

        int rowLg=maze[0].length;
        int rowNb= maze.length;
        int size = rowLg*rowNb;

        int[] disTo = new int[size];
        int [] edgeTo = new int[size];
        for(int i=0; i<size;i++){
            disTo[i]=Integer.MAX_VALUE;
            edgeTo[i]=-1;
        }

        int P1=ind(x1,y1,rowLg);
        int P2=ind(x2,y2,rowLg);
        edgeTo[P1]=P1;
        disTo[P1]=0;
        PQ.add(new Entry(P1,0));

        while (!PQ.isEmpty()){
            Entry e = PQ.poll();
            int P=e.position;
            int dist=e.dist;
            int x= row(P, rowLg);
            int y= col(P, rowLg);

            //top
            if(x>0 && maze[x-1][y]==0 && dist+1 < disTo[P-rowLg]){
                PQ.add(new Entry(P-rowLg, dist+1));
                disTo[P-rowLg]=dist+1;
                edgeTo[P-rowLg]=P;
            }
            //bottom
            if(x<rowNb-1 && maze[x+1][y]==0 && dist+1 < disTo[P+rowLg]){
                PQ.add(new Entry(P+rowLg, dist+1));
                disTo[P+rowLg]=dist+1;
                edgeTo[P+rowLg]=P;
            }
            //left
            if(y>0 && maze[x][y-1]==0 && dist+1 < disTo[P-1]){
                PQ.add(new Entry(P-1, dist+1));
                disTo[P-1]=dist+1;
                edgeTo[P-1]=P;
            }
            //right
            if(y<rowLg-1 && maze[x][y+1]==0 && dist+1 < disTo[P+1]){
                PQ.add(new Entry(P+1, dist+1));
                disTo[P+1]=dist+1;
                edgeTo[P+1]=P;
            }
        }

        if(edgeTo[P2]==-1) //has no path
            return ll;

        for(int i=P2; i!= P1;i=edgeTo[i]){
            if(i==-1)
                return null;
            ll.addFirst(i);
        }
        ll.addFirst(P1);

        return ll;
    }

    private static class Entry implements Comparable<Entry>{
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

    public static int ind(int x,int y, int lg) {return x*lg + y;}

    public static int row(int pos, int mCols) { return pos / mCols; }

    public static int col(int pos, int mCols) { return pos % mCols; }
}
