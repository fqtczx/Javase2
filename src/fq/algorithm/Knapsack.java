package fq.algorithm;

public class Knapsack {
    public static void main(String[] args) {
        int[] w={1,4,3};//物品的重量
        int[] val={1500,3000,2000};//物品的价值，就是前面讲的v[i]
        int m=4;//背包的容量
        int n=val.length;//物品的个数

        //v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v=new int[n+1][m+1];
        //为了记录放入商品的情况，我们定义一个二维数组
        int[][] path=new int[n+1][m+1];

        //初始化第一行和第一列，默认为0
        for(int i=0,j=0;i<v.length && j<v[0].length;i++,j++){
            v[i][0]=0;
            v[0][j]=0;
        }

        //利用动态规划算法开始处理那个价值矩阵
        for(int i=1;i<v.length;i++){//不处理第一行，i是从1开始的
            for(int j=1;j<v[0].length;j++){//不处理第一列，j是从1开始的
                //按照公式开始处理
                if(w[i-1]>j){//因为i是从1开始的，因此原来公式中的w[i]要修改成w[i-1]
                    v[i][j]=v[i-1][j];
                }else{
                    //因为i是从1开始的，因此公式需要进行调整
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况，我们不能简单的使用上面的公式，需要用if-else进行替代
                    if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j]=1;
                    }else{
                        v[i][j]=v[i-1][j];
                    }
                }
            }
        }

        //输出v
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v[i].length;j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

        //输出最后放入的是哪些商品
        //尝试简单的遍历path数组
//        for(int i=0;i<path.length;i++){
//            for(int j=0;j<path[i].length;j++){
////                System.out.print(path[i][j]+" ");
//                if(path[i][j]==1){
//                    System.out.printf("第%d个商品放入背包\n",i);
//                }
//            }
//            System.out.println();
//        }

        //简单的利用上面的遍历代码无法达到效果,会有冗余的数据
        int i=path.length-1;//行的最大下标
        int j=path[0].length-1;//列的最大下标
        while(i>0 && j>0){
            //从最后开始遍历
            if(path[i][j]==1){
                System.out.printf("第%d个商品放入背包\n",i);
                j=j-w[i-1];//j要减去放入物品的重量
            }
            i--;
        }
    }
}
