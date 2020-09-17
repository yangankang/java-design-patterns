package com.myself.iodemo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReaderStream {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(new File("1.txt"));
        char[] ca = new char[1024];
        int count = 0;
        while ((count = fr.read(ca)) != -1) {
            System.out.println(new String(ca, 0, count));
        }
    }
}
