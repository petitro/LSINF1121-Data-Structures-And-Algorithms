import java.util.*;

public class Huffman2 {
    private Huffman2() { }



    // Huffman trie node
    public static class Node implements Comparable<Node>{
        public final int ch;
        public final int freq;
        public final Node left, right;

        Node(int ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
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

        public String toString() {
            StringBuilder buffer = new StringBuilder(50);
            print(buffer, "", "");
            return buffer.toString();
        }

        private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
            buffer.append(prefix);
            if(ch==-1)
                buffer.append("freq "+freq); //print character
            else
                buffer.append("ch: " + ch +", freq "+freq); //print character
            buffer.append('\n');
            if(left!=null) {
                left.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                right.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }


    }

    /**
     * build the Huffman trie given frequencies
     * corresponding to each character codes from 0 to R-1.
     * freq[i] is the frequency for the character with code i
     * freq.length = R.
     * R is either 256 or 65536, depending on whether the user choose to use unicode or ASCII.
     */
    public static Node buildTrie(int R, int[] freq) {
        //faire une liste avec les elements non nulle

        Node[] nodeArray = new Node[freq.length];
        for(int i=0; i<nodeArray.length; i++){
            if(freq[i]!=0)
                nodeArray[i]= new Node(i, freq[i], null, null);
        }
        Arrays.sort(nodeArray);

        Node root =  MakeTree(nodeArray, nodeArray.length);
        System.out.println(root.toString());
        return root;
    }

    private static Node MakeTree(Node[] array, int length){

        //return condition
        int counter=0;
        int finish=0;
        for (int i = array.length-length; i < array.length; i ++){
            if (array[i] != null) {
                if (counter == 1) {
                    finish=0;
                    break;
                }
                finish=1;
                counter++;
            }
        }
        if(finish==1)
            return array[array.length-1];

        int left =array.length-length;
        int right = array.length-length+1;
        Node superNode = new Node(-1, array[left].freq + array[right].freq, array[left], array[right]);

        array[left]=null;
        array[right]=superNode;

        InsertionSort(array, length);
        return MakeTree(array, length-1);
    }

    private static void InsertionSort(Node[] array, int length){
        for(int i=2+array.length-length; i<array.length; i++){
            if(array[i].freq>=array[i-1].freq)
                break;
            Node tmp = array[i];
            array[i]=array[i-1];
            array[i-1]=tmp;
        }
    }
}
