import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class GlobalWarmingImpl1 extends GlobalWarming {

    ArrayList<TreeMap> islands;
    public boolean [][] visted;
    int [][]altitudeCopy;

    private void IslandFound(TreeMap RBT, Point p){
        int column=p.x;
        int row=p.y;
        int key = column + row*altitudeCopy[0].length; //emplacement
        visted[row][column]=true;

        RBT.put(key, altitudeCopy[row][column]);


        while (true) {
            //bottom
            if (row < altitudeCopy.length - 1 && altitudeCopy[row + 1][column] > waterLevel && visted[row + 1][column] == false){
                IslandFound(RBT, new Point(column, row + 1));
            }
            //right
            else if (column < altitudeCopy[row].length - 1 && altitudeCopy[row][column + 1] > waterLevel && visted[row][column + 1] == false) {
                IslandFound(RBT, new Point(column + 1, row));
            }
            //left
            else if (row > 0 && altitudeCopy[row - 1][column] > waterLevel && visted[row - 1][column] == false) {
                IslandFound(RBT, new Point(column, row - 1));
            }
            //top
            else if (column > 0 && altitudeCopy[row][column - 1] > waterLevel && visted[row][column - 1] == false) {
                IslandFound(RBT, new Point(column - 1, row));
            }
            //no neighbors
            else {
                break;
            }
        }
        return;
    }

    public GlobalWarmingImpl1(int[][] altitude, int waterLevel) {
        super(altitude, waterLevel);
        // expected pre-processing time in the constructror : O(n^2 log(n^2))
        // TODO

        altitudeCopy = altitude.clone();
        visted = new boolean [altitude.length][altitude[0].length];
        islands = new ArrayList();

        for (int row = 0; row < altitude.length; row++) {
            for (int column = 0; column < altitude[row].length; column++) {
                if(altitude[row][column]>waterLevel && visted[row][column]==false){
                    //new island
                    TreeMap RBT = new TreeMap();
                    IslandFound(RBT, new Point(column, row));
                    islands.add(RBT); //add RBT to the ArrayList
                }
            }
        }
        //System.out.println("finished");

    }


    public int nbIslands() { //count number of trees
        // TODO
        // expected time complexity O(1)

        return islands.size();
    }


    public boolean onSameIsland(Point p1, Point p2) {
        int P1 = p1.x + p1.y*altitudeCopy[0].length;
        int P2 = p2.x + p2.y*altitudeCopy[0].length;
        System.out.println("place in the matrix, P1 " + P1+ " and P2 "+P2);
        Iterator<TreeMap> iter = islands.iterator();
        int counter=0;
        while (iter.hasNext() && counter==0){
            TreeMap RBT =  iter.next();
            if(RBT.get(P1)!= null)
                counter++;
            if (RBT.get(P2)!=null)
                counter++;
            if (counter==2)
                return true;
            if(counter==1){
                System.out.println("are not on the same island, island size " + RBT.size());
                return false;
            }

        }

        return false;
    }

}