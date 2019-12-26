import java.util.ArrayList;

public class DigraphImplem implements Digraph {

    private int V;
    private ArrayList<ArrayList>al;//Adjacency list
    private int E;

    public DigraphImplem(int V) {
         // TODO
        this.V=V;
        al = new ArrayList<ArrayList>(V);
        for(int i=0; i<V; i++)
            al.add(new ArrayList());
        E=0;
    }

    /**
     * The number of vertices
     */
    public int V() {
        // TODO
        return V;
    }

    /**
     * The number of edges
     */
    public int E() {
        // TODO
        return E;
    }

    /**
     * Add the edge v->w
     */
    public void addEdge(int v, int w) {
        // TODO
        ArrayList my = al.get(v);
        my.add(w);
        E++;
    }

    /**
     * The nodes adjacent to node v
     * that is the nodes w such that there is an edge v->w
     */
    public Iterable<Integer> adj(int v) {
        // TODO
        Iterable<Integer> iter = al.get(v);
        return iter;
    }

    /**
     * A copy of the digraph with all edges reversed
     */
    public Digraph reverse() {
        // TODO
        Digraph dig = new DigraphImplem(V);
        for(int i=0; i<V; i++){
            Iterable<Integer> iter = adj(i);
            for(int Adj : iter)
                dig.addEdge(Adj, i);
        }
        return dig;
    }


}
