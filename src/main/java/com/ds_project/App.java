package com.ds_project;

import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    
    {
        //Using Burrows Wheeler Transform to encode/decode string
        System.out.println("------------------------------------------------------");
        BWT s1 = new BWT("banana");
        System.out.println("Original string: " + s1.getString());
        String sub = "na";
        String enq = s1.encode();
        System.out.println("Encode string: " + enq);
        String deq = BWT.decode(enq);
        System.out.println("Decode string: " + deq);
        ArrayList<String> indexes = s1.search(sub);
        show(sub ,indexes);
        System.out.println("------------------------------------------------------");
        //Using Burrows Wheeler Mathing to search a sub string in a string
        String org = "rock paper scissors";
        sub = "paper";
        indexes = BWM.search(sub, org);
        System.out.println("Original string: " + org);
        show(sub, indexes);
        sub = "fire";
        indexes = BWM.search(sub, org);
        show(sub, indexes);
        System.out.println("------------------------------------------------------");
    }
    
    public static void show(String sub, ArrayList<String> indexes){
        if (indexes.get(0).equals("-1")) {
            System.out.println("There is not any " + "{"+sub+"}" + " in original string!");
        }else{
            System.out.print("{"+sub+"}" + " is in original string starts at characters: ");
            for (int i = 0; i < indexes.size(); i++) {
                System.out.print(indexes.get(i) + ", ");
            }
            System.out.println();
        }
    }
}
