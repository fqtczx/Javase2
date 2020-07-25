package fq.algorithm;

import java.util.Arrays;

public class Prim {
    public static void main(String[] args) {
        char[] data={'A','B','C','D','E','F','G'};
        int val=10000;//这个权值表示两个不连通
        int verxs=data.length;//节点数据
        int[][] weight={
                {val,5,7,val,val,val,2},
                {5,val,val,9,val,val,3},
                {7,val,val,val,8,val,val},
                {val,9,val,val,val,4,val},
                {val,val,8,val,val,5,4},
                {val,val,val,4,5,val,6},
                {2,3,val,val,4,6,val}
        };
        Graph g=new Graph(verxs);
        MinTree mt=new MinTree();
        mt.creGraph(g,verxs,data,weight);//将图创建出来
        mt.show(g);//打印图，就是打印邻接矩阵
        mt.prim(g,3);//普里姆算法生成边，达到路径最小但是全部连接的目的
    }
}

//创建最小生成树
class MinTree{
    //生成图，将图的各种属性添加进去
    public void creGraph(Graph g,int verxs,char[] data,int[][] weight){
        for(int i=0;i<verxs;i++){
            g.data[i]=data[i];//节点
            for(int j=0;j<verxs;j++){
                g.weight[i][j]=weight[i][j];//邻接矩阵
            }
        }
    }

    //遍历打印图，也就是邻接矩阵
    public void show(Graph g){
        for(int[] link:g.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    //得到最小生成树

    /**
     *
     * @param g 图
     * @param v 表示从图的第几个顶点进行开始生成，就是下标
     */
    public void prim(Graph g,int v){
        int[] visited =new int[g.verx];//表示节点是否被访问过
        visited[v]=1;//将当前节点标记为访问过

        //记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int minWeight=10000;//将minWeight初始成一个大数，后面在遍历过程中，会被替换

        for(int k=1;k<g.verx;k++){//要生成verx-1条边

            //每一次生成的子图，和哪个节点的距离最近
            //其实就是寻找已经访问过的节点和未访问过的节点间的权值最小的边
            for(int i=0;i<g.verx;i++){//i表示被访问过的节点
                for(int j=0;j<g.verx;j++){//j表示没有被访问过的节点
                    if(visited[i]==1 && visited[j]==0 && g.weight[i][j]<minWeight){
                        //替换并记录
                        minWeight=g.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            //找到一条最小的边
            System.out.println("边<"+g.data[h1]+","+g.data[h2]+">权值："+minWeight);
            visited[h2]=1;//标记已访问过
            minWeight=10000;//重置minWeight
        }
    }
}

//创建图
class Graph{
    int verx;//表示图的节点个数
    char[] data;//存放节点的数据
    int[][] weight;//存放边，也就是邻接矩阵

    public Graph(int verxs){
        verx=verxs;
        data=new char[verx];
        weight=new int[verx][verx];
    }
}