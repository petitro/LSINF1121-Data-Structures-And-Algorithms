//feel free to import anything here

import java.util.PriorityQueue;

public class MineClimbing {
    /**
    * Returns the minimum distance between (startX, startY) and (endX, endY), knowing that
    * you can climb from one mine block (a,b) to the other (c,d) with a cost Math.abs(map[a][b] - map[c][d])
    *
    * Do not forget that the world is round: the position (map.length,i) is the same as (0, i), etc.
    *
    * map is a matrix of size n times m, with n,m > 0.
    *
    * 0 <= startX, endX < n
    * 0 <= startY, endY < m
    */
    public static int best_distance(int[][] map, int startRow, int startCol, int endRow, int endCol) {
        //TODO Student
        //Dijkstra's algorithm started from startX, startY

        PriorityQueue<Entry> PQ = new PriorityQueue<Entry>();
        int rowLg= map[0].length;
        int rowNb= map.length;
        int size = rowLg*rowNb;
        int[] disTo = new int[size];
        //int [] edgeTo = new int[size];
        for(int i =0; i<size; i++){
            disTo[i]=Integer.MAX_VALUE;
            //edgeTo[i]=-1;
        }


        int P1 = ind(startRow,startCol,rowLg);
        int P2 = ind(endRow, endCol, rowLg);
        disTo[P1]=0;
        //edgeTo[P1]=P1;
        PQ.add(new Entry(P1,0));

        int counter =0;

        while (!PQ.isEmpty() && counter <size){
            Entry e = PQ.poll();
            int P=e.position;
            int dist=e.dist;
            if(P==P2)
                break;
            //int x= row(P, rowLg);
            //int y= col(P, rowLg);
            //int height=map[x][y];
            int height=map[row(P, rowLg)][col(P, rowLg)];

            //top
            int PT=newPosT(P, rowLg, rowNb);
            int heightT=Math.abs(height- map[row(PT,rowLg)][col(PT,rowLg)]);
            if(dist+ heightT< disTo[PT]){
                PQ.add(new Entry(PT, dist+heightT));
                if(disTo[PT]==Integer.MAX_VALUE)
                    counter++;
                disTo[PT]=dist+ heightT;
                //edgeTo[PT]=P;
            }
            //bottom
            int PB=newPosB(P,rowLg,rowNb);
            int heightB=Math.abs(height- map[row(PB,rowLg)][col(PB,rowLg)]);
            if(dist+ heightB< disTo[PB]){
                PQ.add(new Entry(PB,dist+heightB));
                if(disTo[PB]==Integer.MAX_VALUE)
                    counter++;
                disTo[PB]=dist+ heightB;
                //edgeTo[PB]=P;
            }
            //left
            int PL=newPosL(P,rowLg);
            int heightL=Math.abs(height- map[row(PL,rowLg)][col(PL,rowLg)]);
            if(dist+ heightL < disTo[PL]){
                PQ.add(new Entry(PL, dist+ heightL));
                if(disTo[PL]==Integer.MAX_VALUE)
                    counter++;
                disTo[PL]=dist+heightL;
                //edgeTo[PL]=P;
            }
            //right
            int PR=newPosR(P,rowLg);
            int heightR=Math.abs(height-map[row(PR,rowLg)][col(PR,rowLg)]);
            if( dist+ heightR < disTo[PR]){
                PQ.add(new Entry(PR, dist+ heightR));
                if(disTo[PR]==Integer.MAX_VALUE)
                    counter++;
                disTo[PR]=dist+heightR;
                //edgeTo[PR]=P;
            }
        }



        return disTo[P2];
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

    //new position top
    public static int newPosT(int pos, int rowLg, int rowNb){
        int answer = pos-rowLg;
        if(answer>=0)
            return answer;
         return answer+rowLg*rowNb;
    }
    public static int newPosB(int pos, int rowLg, int rowNb){
        int answer = pos+rowLg;
        if(answer<rowLg*rowNb)
            return answer;
        return answer-rowLg*rowNb;
    }
    public static int newPosL(int pos, int rowLg){
        int answer = pos-1;
        if(pos%rowLg!=0)
            return answer;
        return answer + rowLg;
    }
    public static int newPosR(int pos, int rowLg){
        int answer = pos+1;
        if(answer%rowLg!=0)
            return answer;
        return answer - rowLg;
    }

    // you may need to add additional things below.
}