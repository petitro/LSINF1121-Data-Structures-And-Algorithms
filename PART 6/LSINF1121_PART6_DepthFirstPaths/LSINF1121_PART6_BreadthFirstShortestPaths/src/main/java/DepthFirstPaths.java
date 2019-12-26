//TODO import

import java.util.ArrayList;
import java.util.List;

public class DepthFirstPaths {
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] edgeTo;     // edgeTo[v] = last edge on s-v path
    private final int s;

    /**
     * Computes a path between s and every other vertex in graph G
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // Depth first search from v
    private void dfs(Graph G, int v) {
        //TODO question 2
        List<Integer> list = new ArrayList<Integer>();
        list.add(v);

        int oldV = v;
        while (!list.isEmpty()){
            int w = list.get(list.size()-1);
            list.remove(list.size()-1);
            if(!marked[w]) {
                marked[w] = true;
                edgeTo[w] = oldV;
                for (int x : G.adj(w))
                    list.add(x);
                oldV = w;
            }
        }
    }

    /**
     * Is there a path between the source s and vertex v?
     * @param v the vertex
     * @return true if there is a path, false otherwise
     */
    public boolean hasPathTo(int v) {
        //TODO question 3
        return marked[v];
    }

    /**
     * Returns a path between the source vertex s and vertex v, or
     * null if no such path
     * @param v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         s and vertex v, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        //TODO question 4
        List<Integer> iter = new ArrayList<Integer>();
        while (v!=edgeTo[v]){
            iter.add(v);
            v=edgeTo[v];
        }
        return iter;
    }
}
