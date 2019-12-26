public class ConnectedComponents {

    /**
     * @return the number of connected components in g
     */
    public static int numberOfConnectedComponents(Graph g) {
        // TODO
        boolean [] marked = new boolean[g.V()];
        int nbComp=0;

        for(int s =0; s<g.V(); s++){
            if(!marked[s]){
                nbComp++;
                marked=DFS(g, marked, s);
            }
        }
        return nbComp;
    }

    private static boolean[] DFS(Graph g, boolean[]marked, int v){
        marked[v]=true;
        for (int s : g.adj(v)){
            if(!marked[s])
                DFS(g, marked, s);
        }

        return marked;
    }


}