import java.util.*;

public class Huffman {
    private Huffman() {
    }


    // Huffman trie node
    public static class Node implements Comparable<Node> {
        public final int ch;
        public final int freq;
        public final Node left, right;

        Node(int ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }

        // is the node a leaf node?
        public boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        /*
        public String toString() {
            StringBuilder buffer = new StringBuilder(50);
            print(buffer, "", "");
            return buffer.toString();
        }


        private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
            buffer.append(prefix);
            if (ch == -1)
                buffer.append("freq " + freq); //print character
            else
                buffer.append("ch: " + ch + ", freq " + freq); //print character
            buffer.append('\n');
            if (left != null) {
                left.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                right.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
        */

    }

    /**
     * build the Huffman trie given frequencies
     * corresponding to each character codes from 0 to R-1.
     * freq[i] is the frequency for the character with code i
     * freq.length = R.
     * R is either 256 or 65536, depending on whether the user choose to use unicode or ASCII.
     */
    public static Node buildTrie(int R, int[] freq) {

        // Initialize priority queue with singleton trees.
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        //ajouter les elements
        for (int c = 0; c < R; c++) {
            if (freq[c] > 0) {
                pq.add(new Node(c, freq[c], null, null));
            }
        }

        //les merger
        while (pq.size() > 1)
        { // Merge two smallest trees.
            Node x = pq.poll();
            Node y = pq.poll();
            Node parent = new Node('\0', x.freq + y.freq, x, y);
            pq.add(parent);
        }
        Node root =  pq.poll();

        //System.out.println(root.toString());
        return root;
    }

}
