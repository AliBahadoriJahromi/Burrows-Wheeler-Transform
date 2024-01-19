package com.ds_project;

import java.util.Iterator;

public class Str_Iterator implements Iterator<String>  {
    private final String str;
    private int pos = 0;

    public Str_Iterator(String str) {
        this.str = str;
    }

    public boolean hasNext() {
        return pos < str.length();
    }

    public String next() {
        Character c = str.charAt(pos++);
        String s = Character.toString(c);
        return s;
        
    }
}
