public class GlobalWarmingImpl extends GlobalWarming {

    public int[] unions;
    public int[] size;
    public int count;
    public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
        super(altitude,waterLevel);
        //System.out.println(altitude.length);
        unions = new int[altitude.length*altitude.length]; //1D
        size = new int[altitude.length*altitude.length];//1D
        count=nbSafePointsCorrect(waterLevel); //O(n)
        //copier tout les element dans union et size O(n)
        for(int i=0;i<unions.length;i++){
            unions[i]=i;
            size[i]=1;
        }
        //unie tout le point a gauche et en haut (deja observe) minO(n^2)
        for(int i=0;i<altitude.length;i++){
            for(int j=0;j<altitude.length;j++){
                if(altitude[i][j]>waterLevel){
                    if(i>0 && altitude[i-1][j]>waterLevel){
                        union(new Point(i,j), new Point(i-1,j));
                    }
                    if(j>0 && altitude[i][j-1]>waterLevel){
                        union(new Point(i,j), new Point(i,j-1));
                    }
                    //string(unions);
                }
            }
        }

    }

    //Quasi lineair, compress algoritme
    public int find(int p){
        int old_p=p;
        //trouve le root
        while(p!=unions[p]){
            p=unions[p];
        }
        //egale tout les pointeurs vers le root
        while(old_p!=p){
            int old = unions[old_p];
            unions[old_p]=p;
            old_p=old;
        }
        return old_p;
    }

    //Complexite
    public void union(Point p1, Point p2){
        //System.out.println(p1.x+" "+p1.y);
        int i = find(p1.x*altitude.length+p1.y);
        int j = find(p2.x*altitude.length+p2.y);
        if(i==j){
            return; //pointe deja vers le meme root
        }
        //selection de la plus grosse ile
        if(size[i]<size[j]){
            unions[i]=j;
            size[j]+=size[i];
        }
        else{
            unions[j]=i;
            size[i]+=size[j];
        }
        count--; //l'union de deux cases diminu le nombre d'ile
    }

    public int nbIslands() {
        return count;
    }


    public boolean onSameIsland(Point p1, Point p2) {

        if(altitude[p1.x][p1.y]<=waterLevel||altitude[p2.x][p2.y]<=waterLevel){
            return false;
        }
        if(p1.equals(p2))
            return true;
        //System.out.println(find(p1.x*altitude.length+p1.y));
        //System.out.println(find(p2.x*altitude.length+p2.y));
        return find(p1.x*altitude.length+p1.y)==(find(p2.x*altitude.length+p2.y));
    }

    public void string(int[] tab){
        for(int i=0;i<tab.length;i++){
            System.out.print(tab[i]+" ");
            if(i%(altitude.length-1)==0){
                System.out.println(" ");
            }
        }
        System.out.println("-----------------");
    }

}

