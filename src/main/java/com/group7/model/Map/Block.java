package com.group7.model.Map;

public class Block {

    private int xCoord;
    private int yCoord;
    private int blockNum;

    public Block(int xCoord, int yCoord, int blockNum) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.blockNum = blockNum;
    }

    public Block(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public boolean equals(int xCoord, int yCoord) {

        if (this.xCoord == xCoord && this.yCoord == yCoord) {
            return true;
        } else return false;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Block) {
            Block block = (Block) o;

            if (block.xCoord == this.xCoord && block.yCoord == this.yCoord) {
                return true;
            } else {
                return false;
            }
        } else return false;

        
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getBlockNum() {
        return blockNum;
    }
    
}
