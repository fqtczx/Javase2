package fq.io;

import java.io.*;

public class FileOutputShortcut {
    static  String file="BasicFileOutput";

    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new StringReader(
                BufferedInputFile.read("D://IDEAPro//src//fq//io//BasicFileOutput.java")
        ));
        PrintWriter out=new PrintWriter(
                new BufferedWriter(new FileWriter(file))
        );
        int linecount=0;
        String s;
        while((s=in.readLine())!=null){
            out.println(linecount++ +": "+s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
