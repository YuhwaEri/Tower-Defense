package Grid;

import java.lang.reflect.Array;

class Grid {
    private Object[][] path;

    public Grid(){
        this.path = new Object [20][20];
    }

    public Object[][] getPath() {
        return path;
    }

    public void setPath(Object[][] path) {
        this.path = path;
    }
}
