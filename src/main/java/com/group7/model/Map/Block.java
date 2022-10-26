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

    public boolean equals(Block block) {
        if (block.xCoord == this.xCoord && block.yCoord == this.yCoord) {
            return true;
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
