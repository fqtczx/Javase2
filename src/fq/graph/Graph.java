package fq.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储边的关系的二维数组
    private int numofEdges;//表示边的个数
    public boolean[] isVisited;//标记节点是否被访问过

    public static void main(String[] args) {
        Graph g=new Graph(8);
//        String[] strs={"A","B","C","D","E"};
        String[] strs={"1","2","3","4","5","6","7","8"};
        for(int i=0;i<strs.length;i++){
            g.insertVertex(strs[i]);
        }
        g.insertEdge(0,1,1);
        g.insertEdge(0,2,1);
        g.insertEdge(1,3,1);
        g.insertEdge(1,4,1);
        g.insertEdge(3,7,1);
        g.insertEdge(4,7,1);
        g.insertEdge(2,5,1);
        g.insertEdge(2,6,1);
        g.insertEdge(5,6,1);
        g.show();
        g.dfs();
//        g.bfs();
    }

    public Graph(int n){
        edges=new int[n][n];
        vertexList=new ArrayList<>(n);
        numofEdges=0;
        isVisited=new boolean[n];
    }

    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //v1和v2表示点的下标即表示是第几个顶点
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numofEdges++;
    }

    //返回节点的个数
    public int numofVertex(){
        return vertexList.size();
    }

    //返回边的个数
    public int getNumofEdges(){
        return numofEdges;
    }

    //返回节点下标对应的数据
    public String getVal(int index){
        return vertexList.get(index);
    }

    //返回两条边的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void show(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }

    //返回index对应节点的第一个邻接节点的下标,没有就返回-1
    public int getFristNeighbor(int index){
        for(int i=0;i<vertexList.size();i++){
            if(edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    //v1的前一个邻接节点下标为v2，返回其下一个邻接节点下标
    public int getNextNeighbor(int v1,int v2){
        for(int i=v2+1;i<vertexList.size();i++){
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    //深度优先算法
    public void dfs(boolean[] isVisited,int i){
        System.out.print(getVal(i)+"-->");
        isVisited[i]=true;

        int w=getFristNeighbor(i);
        while(w!=-1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }else{
                w=getNextNeighbor(i,w);
            }
        }
    }

    //对dfs进行重载，遍历所有的节点
    public void dfs(){
        for(int i=0;i<numofVertex();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //对一个进行广度优先遍历
    public void bfs(boolean[] isVisited,int i){
        int u;//表示队列头节点的对应下标
        int w;//邻接节点w
        LinkedList queue=new LinkedList();//队列，记录节点访问的顺序

        System.out.print(getVal(i)+"=>");
        isVisited[i]=true;
        queue.addLast(i);

        while(!queue.isEmpty()){
            u=(Integer) queue.removeFirst();
            w=getFristNeighbor(u);

            while(w!=-1){
                if(!isVisited[w]){
                    System.out.print(getVal(w)+"=>");
                    isVisited[w]=true;
                    queue.addLast(w);
                }
                w=getNextNeighbor(u,w);
            }
        }
    }

    public void bfs(){
        for(int i=0;i<numofVertex();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
}
