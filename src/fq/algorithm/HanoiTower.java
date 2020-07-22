package fq.algorithm;

public class HanoiTower {
    public static void main(String[] args) {
        move(64,'A','B','C');
    }

    /**
     * @param num 代表盘的个数
     * @param a 开始柱
     * @param b 中间柱
     * @param c 目标柱
     */
    //使用分治算法解决汉诺塔问题,具体使用递归算法实现
    public static void move(int num,char a,char b,char c){
        if(num==1){
            //如果只有一个盘，直接从a移动到c即可
            System.out.println("第1个盘从"+a+"移动到"+c);
        }else{
            //如果盘数大于等于2个时，将其分为两个部分，即最上面所有盘，最下面的一个盘
            //首先将上面的所有盘从A移动到B，中间会用到C
            move(num-1,a,c,b);
            //其次再将最下面的盘从A盘移动到C盘
            System.out.println("第"+num+"个盘从"+a+"移动到"+c);
            //最后将在B上的num-1个盘从B盘移动到C盘，中间会用到A盘
            move(num-1,b,a,c);
        }
    }
}
