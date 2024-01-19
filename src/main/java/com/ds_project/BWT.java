package com.ds_project;

import java.util.ArrayList;

public class BWT {

    private String s;
    private ArrayList<String> rotations;

    public BWT(String s){
        this.s = s;
        this.rotations = new ArrayList<String>();
        String r;
        for (int i = 0; i < this.s.length()+1; i++) {
            r = this.s.substring(i, this.s.length()) + "$" + this.s.substring(0, i);
            this.rotations.add(r);
        }
        this.rotations.sort(null);
    }

    public String encode(){
        String enq = "", r;
        for (int i = 0; i < this.rotations.size(); i++) {
            r = this.rotations.get(i);
            enq += r.charAt(r.length()-1);
        }
        return enq;
    }

    public static String decode(String enq){
        ArrayList<String> table = new ArrayList<String>();
        for (int i = 0; i < enq.length(); i++) {
            table.add("");
        }
        for (int i = 0; i < enq.length(); i++) {
            for (int j = 0; j < enq.length(); j++) {
                table.set(j, enq.charAt(j) + table.get(j));
            }
            table.sort(null);
        }
        String str;
        for (int i = 0; i < table.size(); i++) {
            str = new String(table.get(i));
            if(str.startsWith("$")){
                return str.substring(1);
            }
        }
        return "";
    }  

    public ArrayList<String> search(String sub){
        sub = new StringBuffer(sub).reverse().toString();
        int top = 0;
        int bottom = this.rotations.size();
        Str_Iterator i_s = new Str_Iterator(sub);
        String letter = i_s.next();
        return find_first(i_s,letter,top,bottom);
    }

    private ArrayList<String> find_first(
        Str_Iterator i_s,
        String letter,
        int top, int bottom){

        int p_top = top;
        int p_bottom = bottom; 
        String r;
        ArrayList<Integer> rows = new ArrayList<Integer>();
        for (int i = 0; i < this.rotations.size(); i++) {
            r = this.rotations.get(i);
            if (r.startsWith(letter)) {
                rows.add(i);
            }
        }
        top = rows.get(0);
        bottom = rows.get(rows.size()-1);
        return find_last(i_s, letter, top, bottom, p_top, p_bottom);
    }

    private ArrayList<String> find_last(
        Str_Iterator i_s, 
        String letter,
        int top, int bottom, int p_top, int p_bottom){
        
        String r;
        int tmp;
        ArrayList<String> return_value = new ArrayList<String>(); 
        try {
            letter = i_s.next();
        } catch (Exception e) {
            ArrayList<String> tmp2 = new ArrayList<String>(); 
            for (int i = p_top; i < p_bottom+1; i++) {
                r = this.rotations.get(i);
                if (r.endsWith(letter)) {
                    tmp2.add(r);
                }
            }
            for (int i = 0; i < tmp2.size(); i++) {
                r = tmp2.get(i);
                tmp = r.length()-r.indexOf("$")-2;
                return_value.add(Integer.toString(tmp));
            }
            return return_value;
        }
        
        for (int i = top; i < bottom+1; i++) {
            r = this.rotations.get(i);
            if (r.endsWith(letter)) {
                return find_first(i_s, letter, top, bottom);
            }
        }
        return_value.add(Integer.toString(-1));
        return return_value;

    }

    public String getString(){
        return this.s;
    }
    
}
