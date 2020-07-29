package fq.algorithm;

import java.util.Arrays;

public class Floyd {
    public static void main(String[] args) {
        final int INF=65535;
        char[] vertex={'A','B','C','D','E','F','G'};
        int[][] matrix={
                {0,5,7,INF,INF,INF,2},
                {5,0,INF,9,INF,INF,3},
                {7,INF,0,INF,8,INF,INF},
                {INF,9,INF,0,INF,4,INF},
                {INF,INF,8,INF,0,5,4},
                {INF,INF,INF,4,5,0,6},
                {2,3,INF,INF,4,6,0}
        };
        FGraph fg=new FGraph(matrix,vertex);
        fg.show();
        fg.floyd();
        fg.show();
    }
}

class FGraph {
    private char[] vertex;//存放顶点数组
    private int[][] dis;//保存各个顶点间的距离，最后的结果也是保留在这个二维数组中
    private int[][] infix;//保存两个顶点之间的中间节点

    public FGraph(int[][] matrix,char[] vertex){
        this.dis=matrix;//各个顶点之间的距离就是初始的邻接矩阵
        this.vertex=vertex;
        this.infix=new int[vertex.length][vertex.length];

        //存放的是前驱节点的下标
        for(int i=0;i<vertex.length;i++){
            Arrays.fill(infix[i],i);
        }
    }

    public void show() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(dis[i][j]+" ");
            }
            System.out.println();
        }


        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(vertex[infix[i][j]]+" ");
            }
            System.out.println();
        }
    }

    public void floyd(){
        int len=0;//变量保存距离
        for(int k=0;k<vertex.length;k++){//中间顶点
            for(int i=0;i<vertex.length;i++){//出发顶点
                for(int j=0;j<vertex.length;j++){//目标顶点
                    len=dis[i][k]+dis[k][j];
                    if(len<dis[i][j]){
                        dis[i][j]=len;
                        infix[i][j]=infix[k][j];
                        //这里前驱节点的更新非常重要，是kj路径的前驱节点
                    }
                }
            }
        }
    }
}