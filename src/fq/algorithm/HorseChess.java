package fq.algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HorseChess {
    public static int X;
    public static int Y;
    public static boolean[] visited;
    public static boolean isFinished;

    public static void main(String[] args) {
        X=6;
        Y=6;
        int[][] chess=getChess(X,Y);
        visited=new boolean[X*Y];
        travel(chess,0,5,1);
        for(int[] link:chess){
            System.out.println(Arrays.toString(link));
        }
    }

    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> al=new ArrayList<>();
        Point p1=new Point();

        if((p1.x=curPoint.x-2)>=0 && (p1.y=curPoint.y-1)>=0){
            al.add(new Point(p1));
        }

        if((p1.x=curPoint.x-1)>=0 && (p1.y=curPoint.y-2)>=0){
            al.add(new Point(p1));
        }

        if((p1.x=curPoint.x+1)<X && (p1.y=curPoint.y-2)>=0){
            al.add(new Point(p1));
        }

        if((p1.x=curPoint.x+2)<X && (p1.y=curPoint.y-1)>=0){
            al.add(new Point(p1));
        }

        if((p1.x=curPoint.x+2)<X && (p1.y=curPoint.y+1)<Y){
            al.add(new Point(p1));
        }

        if((p1.x=curPoint.x+1)<X && (p1.y=curPoint.y+2)<Y){
            al.add(new Point(p1));
        }

        if((p1.x=curPoint.x-1)>=0 && (p1.y=curPoint.y+2)<Y){
            al.add(new Point(p1));
        }

        if((p1.x=curPoint.x-2)>=0 && (p1.y=curPoint.y+1)<Y){
            al.add(new Point(p1));
        }
        return al;
    }

    public static void travel(int[][] chess,int row,int colum,int step){
        chess[row][colum]=step;
        visited[row*X+colum]=true;

        ArrayList<Point> al=next(new Point(colum,row));
        sort(al);
        while(!al.isEmpty()){
            Point p=al.remove(0);
            if(!visited[p.y*X+p.x]){
                travel(chess,p.y,p.x,step+1);
            }
        }

        if(step<X*Y && !isFinished){
            chess[row][colum]=0;
            visited[row*X+colum]=false;
        }else{
            isFinished=true;
        }
    }

    public static int[][] getChess(int x,int y){
        return new int[x][y];
    }

    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int num1=next(o1).size();
                int num2=next(o2).size();

                if(num1<num2){
                    return -1;
                }else if(num1==num2){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
    }
}
