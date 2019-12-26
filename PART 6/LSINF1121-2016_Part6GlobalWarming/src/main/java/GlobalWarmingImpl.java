import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

    boolean[][] marked;
    Point[][] edgeTo;
    Node[][] list;
    int n;

    /**
     * In the following, we assume that the points are connected to
     * horizontal or vertical neighbors but not to the diagonal ones
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     * @param waterLevel is the water level, every entry <= waterLevel is flooded
     */
    public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
        super(altitude, waterLevel);
        n = altitude.length;
        list = new Node[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(altitude[i][j] > waterLevel){
                    Point p = new Point(i,j);
                    Node noeud = new Node(p);
                    check_adj(i, j, noeud);
                    list[i][j] = noeud;
                }
            }
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

        marked = new boolean[n][n];
        edgeTo = new Point[n][n];
        calc(p2);
        if(!hasPathTo(p1)) {
            return new ArrayList<Point>();
        }

        List<Point> al = new ArrayList<Point>();
        Point p = new Point(p1.x, p1.y);
        for (; ! p.equals(p2); p = edgeTo[p.x][p.y]) {
            al.add(p);
        }
        al.add(p2);

        return al;
    }



    public void calc(Point p1){
        marked[p1.x][p1.y] = true;
        Node noeud = list[p1.x][p1.y];
        if(noeud == null){
            return ;
        }
        for(Point p : noeud.adjacents){
            if(!marked[p.x][p.y]){
                edgeTo[p.x][p.y] = p1;
                calc(p);
            }
        }
    }



    public boolean hasPathTo(Point p2){
        return marked[p2.x][p2.y];
    }


    // on regarde les 4 points adjacents à (i,j) et on les ajoute si
    // ils sont au dessus du niveau de la mer
    public void check_adj(int i, int j, Node noeud){
        if( i > 0 ){
            check_point(i-1, j, noeud);
        }
        if( j > 0 ){
            check_point(i, j-1, noeud);
        }
        if( i < n-1 ){
            check_point(i+1, j, noeud);
        }
        if( j < n-1 ){
            check_point(i, j+1, noeud);
        }
    }



    public void check_point(int i, int j, Node noeud){
        if(altitude[i][j] > waterLevel){
            noeud.add_adj(new Point(i,j));
        }
    }



    public static class Node{
        Point p;
        LinkedList<Point> adjacents;

        public Node(Point p){
            this.p = p;
            this.adjacents = new LinkedList<Point>();
        }

        public void add_adj(Point adj){
            this.adjacents.add(adj);
        }
    }
}