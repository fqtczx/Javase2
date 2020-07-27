package fq.algorithm;

import java.util.Arrays;

public class Kruskal {
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接数组
    private static final int INF=Integer.MAX_VALUE;//表示两条边不能联通

    public static void main(String[] args) {
        char[] vertexs={'A','B','C','D','E','F','G'};
        int[][] martix={
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };

        Kruskal k=new Kruskal(vertexs,martix);
        k.print();
        System.out.println(k.edgeNum);
//        EData[] eds=k.getEdges();
//        k.sortEdges(eds);
//        System.out.println(Arrays.toString(eds));
        k.kruskal();
    }

    public Kruskal(char[] vertexs,int[][] matrix){
        this.vertexs=vertexs;
        this.matrix=matrix;

        for(int i=0;i<vertexs.length;i++){
            for(int j=i+1;j<vertexs.length;j++){
                if(matrix[i][j]!= INF  ){
                    edgeNum++;//统计有效边的条数
                }
            }
        }
    }

    public void print(){
        for(int i=0;i<vertexs.length;i++){
            for(int j=0;j<vertexs.length;j++){
                System.out.printf("%12d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序：冒泡
    public void sortEdges(EData[] ed){
        for(int i=0;i<ed.length-1;i++){
            for(int j=0;j<ed.length-1-i;j++){
                if(ed[j].weight > ed[j+1].weight){
                    EData tmp=ed[j];
                    ed[j]=ed[j+1];
                    ed[j+1]=tmp;
                }
            }
        }
    }

    //返回顶点对应的下标
    private int getIndex(char a){
        for(int i=0;i<vertexs.length;i++){
            if(vertexs[i]==a){
                return i;
            }
        }
        return -1;
    }

    //返回由边构成的数组,通过遍历matrix矩阵得到
    private EData[] getEdges(){
        EData[] eds=new EData[edgeNum];
        int index=0;
        for(int i=0;i<vertexs.length;i++){
            for(int j=i+1;j<vertexs.length;j++){
                if(matrix[i][j] != INF){
                    eds[index]=new EData(vertexs[i],vertexs[j],matrix[i][j]);
                    index++;
                }
            }
        }
        return eds;
    }

    //获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同

    /**
     *
     * @param ends 数组是为了记录各个顶点对应的终点是哪个，ends数组是在遍历过程中逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while(ends[i] !=0){
            i=ends[i];
        }
        return i;
    }

    public void kruskal(){
        int index=0;//表示最后结果数组的索引
        int[] ends=new int[vertexs.length];//用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点

        //创建结果数组，保存最后的最小生成树
        EData[] res=new EData[vertexs.length-1];//比顶点数量少一的边

        EData[] eds=getEdges();
//        System.out.println(Arrays.toString(eds));
        sortEdges(eds);
        System.out.println(Arrays.toString(eds));

        //遍历eds数组，将边添加到最小生成树中时，判断是否加入的边与最小生成树已有的边构成回路，如果没有，就将结果加入res中
        for(int i=0;i<eds.length;i++){
            //获取第i条边的第一个顶点(起点)和第二个顶点(终点)
            //这里返回的是顶点在顶点数组中对应的下标
            int p1=getIndex(eds[i].start);
            int p2=getIndex(eds[i].end);

            //获取俩个顶点在已有最小生成树中的终点
            int m=getEnd(ends,p1);
            int n=getEnd(ends,p2);

            /*
            核心代码是下面这一段
             */
            //判断是否构成回路
            if(m != n){
                ends[m]=n;//设置m在已有最小生成树中的终点
                res[index]=eds[i];//有一条边加入结果数组
                index++;
            }
        }

        //输出结果最小生成树
        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }
}

//创建一个类EData,它的对象实例就表示一条边
class EData{
    char start;//起点
    char end;//终点
    int weight;//边的权值

    public EData(char a,char b,int weight){
        start=a;
        end=b;
        this.weight=weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}