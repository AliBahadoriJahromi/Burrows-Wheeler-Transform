package com.ds_project;

import java.util.ArrayList;

public class BWM {
    
    public static ArrayList<String> search(String sub, String org){
        sub = new StringBuffer(sub).reverse().toString();
        ArrayList<String> table = new ArrayList<String>();
        String r;
        for (int i = 0; i < org.length()+1; i++) {
            r = org.substring(i) + "$" + org.substring(0, i);
            table.add(r);
        }
        table.sort(null);
        int top = 0;
        int bottom = table.size();
        Str_Iterator i_s = new Str_Iterator(sub);
        String letter = i_s.next();
        return find_first(table,i_s,letter,top,bottom);
    }

    private static ArrayList<String> find_first(
        ArrayList<String> rotations,
        Str_Iterator i_s,
        String letter,
        int top, int bottom){

        int p_top = top;
        int p_bottom = bottom; 
        String r;
        ArrayList<Integer> rows = new ArrayList<Integer>();
        for (int i = 0; i < rotations.size(); i++) {
            r = rotations.get(i);
            if (r.startsWith(letter)) {
                rows.add(i);
            }
        }
        top = rows.get(0);
        bottom = rows.get(rows.size()-1);
        return find_last(rotations,i_s, letter, top, bottom, p_top, p_bottom);
    }

    private static ArrayList<String> find_last(
        ArrayList<String> rotations,
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
                r = rotations.get(i);
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
            r = rotations.get(i);
            if (r.endsWith(letter)) {
                return find_first(rotations,i_s, letter, top, bottom);
            }
        }
        return_value.add(Integer.toString(-1));
        return return_value;
    }


}
