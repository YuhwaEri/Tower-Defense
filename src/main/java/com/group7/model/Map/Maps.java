package com.group7.model.Map;

public enum Maps {
    MAP_1("src/main/resources/map1.png", "src/main/resources/map1_path.csv");
    

    private final String picturePath;
    private final String pathCSVPath;
    private Path path;

    Maps(String picturePath, String pathCSVPath) {
        this.picturePath = picturePath;
        this.pathCSVPath = pathCSVPath;
        this.path = new Path(pathCSVPath);
    }


    public String getPathCSVPath() {
        return pathCSVPath;
    }

    public String getPicturePath() {
        return picturePath;
    }  

    public Path getPath() {
        return path;
    }

    public int getWidth() {
        return path.getWidth();
    }

    public int getLength() {
        return path.getLength();
    }

    public boolean isOnPath(int xCoord, int yCoord) {
        return path.isOnPath(xCoord, yCoord);
    }
}
