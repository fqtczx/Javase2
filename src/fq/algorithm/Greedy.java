package fq.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Greedy {
    public static void main(String[] args) {
        HashMap<String, HashSet> hm=new HashMap<>();
        HashSet<String> h1=new HashSet<>();
        h1.add("北京");
        h1.add("上海");
        h1.add("天津");
        hm.put("K1",h1);

        HashSet<String> h2=new HashSet<>();
        h2.add("北京");
        h2.add("广州");
        h2.add("深圳");
        hm.put("K2",h2);

        HashSet<String> h3=new HashSet<>();
        h3.add("成都");
        h3.add("上海");
        h3.add("杭州");
        hm.put("K3",h3);

        HashSet<String> h4=new HashSet<>();
        h4.add("上海");
        h4.add("天津");
        hm.put("K4",h4);

        HashSet<String> h5=new HashSet<>();
        h5.add("杭州");
        h5.add("大连");
        hm.put("K5",h5);

        HashSet<String> allAreas=new HashSet<>();
        Set<String> s=hm.keySet();
        for(String str:s){
            HashSet<String> hs=hm.get(str);
            allAreas.addAll(hs);
        }

        System.out.println(allAreas);

        //select存储选择的电台
        ArrayList<String> select=new ArrayList<>();

        //定义一个集合，存储遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        ArrayList<String> join=new ArrayList<>();

        //定义maxKey,保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //最后其不为空的话，将其加入select中
        String maxKey="";
        int size=0;
        while(allAreas.size()!=0){
            for(String key:hm.keySet()){
                HashSet<String> hs=hm.get(key);

                join.addAll(hs);//要定义一个中间变量，在这个上边进行取交集的操作
                join.retainAll(allAreas);//这个方法返回的就是两者的交集

                if(join.size()>size){
                    maxKey=key;
                    size=join.size();
                }

                //或者另一种方式
                /*
                if(join.size()>0 && (maxKey==null) || join.size() > hm.get(maxKey).size()){
                    maxKey=key
                }
                 */
                join.clear();
            }
            if(maxKey!=null){
                select.add(maxKey);
                allAreas.removeAll(hm.get(maxKey));
            }
            //这里三个重置必须要进行
            join.clear();
            maxKey=null;
            size=0;
        }
        System.out.println(select);
    }
}
