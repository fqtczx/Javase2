package fq.io;

import java.io.File;

public class DirectoryDemo {
    public static void main(String[] args) {
        PPrint.pprint(Directory.walk("D://IDEAPro//src//fq").dirs);
        for(File file:Directory.local("D://IDEAPro//src//fq","T.*")){
            System.out.println(file);
        }
        System.out.println("---------------");
        for(File file:Directory.walk("D://IDEAPro//src//fq","T.*\\.java")){
            System.out.println(file);
        }
        System.out.println("=================");
        for(File file:Directory.walk("D://IDEAPro//src//fq","[Bb].*")){
            System.out.println(file);
        }
    }
}
