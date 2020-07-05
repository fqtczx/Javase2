package fq.recursion;

public class MiGong {
    public static void main(String[] args) {
        int[][] map=new int[8][7];
        for(int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        map[3][2]=1;
        map[3][1]=1;

        //输出地图
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        //使用递归回溯来给小球找路径
        System.out.println("--------------------");
        setWay(map,1,1);

        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    //i,j表示出发的起点，能到右下角表示到达终点
    //约定：map[i][j]=0时表示该点没有走过，为1时为墙，为2时表示通路可以走，为3时表示该路已经走过，但是走不通
    //走迷宫时的策略：下右上左，如果走不通，则回溯
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else{
            if(map[i][j]==0){
                map[i][j]=2;
                if(setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                }else if(setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else{
                    map[i][j]=3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    public static boolean setWay2(int[][] map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else{
            if(map[i][j]==0){
                map[i][j]=2;
                if(setWay2(map,i-1,j)){
                    return true;
                }else if(setWay2(map,i,j+1)){
                    return true;
                }else if(setWay2(map,i+1,j)){
                    return true;
                }else if(setWay2(map,i,j-1)){
                    return true;
                }else{
                    map[i][j]=3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }
}
