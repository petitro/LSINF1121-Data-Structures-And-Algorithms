import java.security.Key;
import java.util.*;

public class GlobalWarmingImpl2 extends GlobalWarming {

    private WeightedQuickUnionUF wuf; // weighted quick find object reference
    private boolean[][] visited;
    private int islandNb=0;


    public GlobalWarmingImpl2(int[][] altitude, int waterLevel) {
        super(altitude, waterLevel);
        // expected pre-processing time in the constructror : O(n^2 log(n^2))
        // TODO

        int rowNb=altitude.length;
        int rowLength=altitude[0].length;
        int maxSize=rowNb*rowLength;
        wuf = new WeightedQuickUnionUF(maxSize);
        visited = new boolean[rowNb][rowLength];
        for(int row=0; row<rowNb;row++){
            for(int column=0; column<rowLength;column++) {
                if(altitude[row][column]>waterLevel){
                    //connecter a un existant
                    int point = row*rowLength+column;
                    //bottom
                    if (row < rowNb - 1 && altitude[row + 1][column] > waterLevel && visited[row + 1][column] == true){
                        wuf.union(point, point+rowLength);
                        visited[row][column] = true;
                    }
                    //right
                    else if (column < rowLength - 1 && altitude[row][column + 1] > waterLevel && visited[row][column + 1] == true) {
                        wuf.union(point, point+1);
                        visited[row][column] = true;
                    }
                    //left
                    else if (column > 0 && altitude[row][column - 1] > waterLevel && visited[row][column - 1] == true) {
                        wuf.union(point, point-1);
                        visited[row][column] = true;

                    }
                    //top
                    else if (row > 0 && altitude[row - 1][column] > waterLevel && visited[row - 1][column] == true) {
                        wuf.union(point, point-rowLength);
                        visited[row][column] = true;
                    }

                    //nouvelle ile
                    else{
                        islandNb++;
                        visited[row][column] = true;
                    }
                }
            }
        }
    }


    public int nbIslands() { //count number of trees
        // TODO
        // expected time complexity O(1)

        return islandNb;
    }


    public boolean onSameIsland(Point p1, Point p2) {
        return wuf.find(Point2int(p1)) == wuf.find(Point2int(p2));
    }

    private Point Int2point(int p){
        int rowLength=altitude[0].length;
        int row = p%rowLength;
        int column = p - row*rowLength;
        return new Point(column, row);
    }

    private int Point2int(Point p){
        int column = p.x;
        int row = p.y;
        int rowLength=altitude[0].length;
        return column + row*rowLength;
    }


    public class WeightedQuickUnionUF {
        private int[] parent;   // parent[i] = parent of i
        private int[] size;     // size[i] = number of elements in subtree rooted at i
        private int count;      // number of components

        public WeightedQuickUnionUF(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int count() {
            return count;
        }


        public int find(int p) {
            validate(p);
            while (p != parent[p])
                p = parent[p];
            return p;
        }

        @Deprecated
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        // validate that p is a valid index
        private void validate(int p) {
            int n = parent.length;
            if (p < 0 || p >= n) {
                throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            // make smaller root point to larger one
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
        }
    }
}