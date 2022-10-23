package com.group7.model.Grid;

import java.lang.reflect.Array;

public class Grid {
    private Object[][] path;

    public Grid(){
        this.path = new Object [12][15];
    }

    public Object[][] getPath() {
        return path;
    }

    public void setPath(Object[][] path) {
        this.path = path;
    }

    public int getLength(){
        return 20;
    }

    public int getWidth(){
        return 20;
    }
}

