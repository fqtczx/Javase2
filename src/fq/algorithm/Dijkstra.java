package fq.algorithm;

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args) {
        final int INF=65535;
        char[] vertex={'A','B','C','D','E','F','G'};
        int[][] matrix={
                {INF,5,7,INF,INF,INF,2},
                {5,INF,INF,9,INF,INF,3},
                {7,INF,INF,INF,8,INF,INF},
                {INF,9,INF,INF,INF,4,INF},
                {INF,INF,8,INF,INF,5,4},
                {INF,INF,INF,4,5,INF,6},
                {2,3,INF,INF,4,6,INF}
        };
        DGraph dg=new DGraph(vertex,matrix);
        dg.show();
        dg.dijkstra(2);
    }


}

class DGraph{
    private char[] vertex;//存放顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;

    public DGraph(char[] ver,int[][] matrix){
        this.vertex=ver;
        this.matrix=matrix;
    }

    public void show(){
        for(int[] link:matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //出发顶点的下标
    public void dijkstra(int index){
        vv=new VisitedVertex(vertex.length,index);
        update(index);
        System.out.println("第一轮循环之后的结果：");
        System.out.println(Arrays.toString(vv.already_arr));
        System.out.println(Arrays.toString(vv.dis));
        System.out.println(Arrays.toString(vv.pre_visited));
        for(int j=1;j<vertex.length;j++){
            int ind=vv.updateArr();
            update(ind);
        }

        System.out.println("处理完成之后的结果：");

        System.out.println(Arrays.toString(vv.already_arr));
        System.out.println(Arrays.toString(vv.dis));
        System.out.println(Arrays.toString(vv.pre_visited));
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱节点
    public void update(int index){
        int len=0;
        for(int i=0;i<matrix[index].length;i++){
            len=vv.getDis(index)+matrix[index][i];
            if(!vv.in(i) && len<vv.getDis(i)){
                vv.updateDis(i,len);
                vv.updatePre(i,index);
            }
        }
    }
}

//顶点属性集合
class VisitedVertex{
    public int[] already_arr;//记录各个顶点是否被访问过，1表示访问过，0表示未访问，会动态更新
    public int[] pre_visited;//记录每个顶点的前驱节点，会动态更新
    public int[] dis;//记录从某个顶点出发到其他所有顶点的距离，自身记为0，会动态更新，最终的结果会储存在这里

    /**
     *
     * @param len 表示顶点的个数
     * @param index 表示出发顶点对应的下标，G的话就是6
     */
    public VisitedVertex(int len,int index){
        this.already_arr=new int[len];
        this.pre_visited=new int[len];
        this.dis=new int[len];

        //初始化dis
        Arrays.fill(dis,65535);
        this.dis[index]=0;
        this.already_arr[index]=1;//设置出发顶点已经被访问过
    }

    //判断下标为index的顶点是否被访问过，是就返回true
    public boolean in(int index){
        return already_arr[index]==1;
    }

    //更新出发顶点到index顶点的距离
    public void updateDis(int index,int len){
        dis[index]=len;

    }

    //更新index顶点的前驱节点下标为pre
    public void updatePre(int index,int pre){
        pre_visited[index]=pre;
    }

    //返回出发顶点到index顶点的距离
    public int getDis(int index){
        return dis[index];
    }

    //下面这个是核心代码，用来更新最短路径数组以及前驱节点数组
    public int updateArr(){
        int min=65535;
        int index=0;
        for(int i=0;i<already_arr.length;i++){
            if(already_arr[i]==0 && dis[i]<min){
                min=dis[i];
                index=i;
            }
        }
        already_arr[index]=1;
        return index;
    }
}