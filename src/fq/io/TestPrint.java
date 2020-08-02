package fq.io;

import java.util.ArrayList;

public class TestPrint {
    public static void main(String[] args) {
        ArrayList<String> al=new ArrayList<>();
        al.add("张安");
        al.add("王五");
        al.add("李四");
        System.out.println(PPrint.pformat(al));
    }
}
