import java.util.*;

public  class WordTransformationSP {

    /**
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     */
    /**
     *
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     * @param s
     * @param start
     * @param end
     * @return rotated string
     */
    public static String rotation(String s, int start, int end) {
        return s.substring(0,start)+new StringBuilder(s.substring(start,end)).reverse().toString()+s.substring(end);
    }

    /**
     * Compute the minimal cost from string "from" to string "to" representing the shortest path
     * @param from
     * @param to
     * @return
     */
    public static int minimalCost(String from, String to) {
        //TODO
        //Dijkstra
        HashMap<String, Integer> distTo = new HashMap<>(); //each node of the graph + distance from start point
        //no way to modify an element in the PQ
        PriorityQueue<Entry> PQ = new PriorityQueue<>(); //next candidate
        PQ.add(new Entry(from,0));
        distTo.put(from,0);

        while (!PQ.isEmpty()){
            Entry n = PQ.poll();
            String v = n.value;
            //O(n^2)
            //loop for each letter
            for (int i=0; i<v.length(); i++){
                //loop for each new word starting by letter i
                for (int j =i+2; j<= v.length(); j++){
                    String w = rotation(v, i,j);
                    if(!distTo.containsKey(w) ||
                        distTo.get(w) > distTo.get(v)+(j-i) ){ //if the found path is better than the older one
                        distTo.put(w, distTo.get(v)+j-i);
                        //would be better to modify the PQ with the newer one, impossible with java implementation
                        PQ.add(new Entry(w, distTo.get(v)+j-i));
                    }
                }
            }
        }
        return distTo.get(to);
    }


    private static class Entry implements Comparable<Entry>{
        String value;
        int dist;
        //creation of vertex
        Entry(String content, int value) {
            this.value = content;
            this.dist = value;
        }
        //find best distance
        public int compareTo(Entry o) {
            return this.dist - o.dist;
        }
    }
}