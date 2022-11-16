package com.group7.model.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Path {

    private int width;
    private int length;
    private ArrayList<Block> path;


    public Path(String pathCSVpath) {

        path = new ArrayList<Block>();

        try {
            Scanner sc = new Scanner(new File(pathCSVpath));

            sc.useDelimiter(",");

            String[] dimensions = sc.nextLine().split(",");
            width = Integer.parseInt(dimensions[0]);
            length = Integer.parseInt(dimensions[1]);

            int blockNum = 0;
            while (sc.hasNext()) {
                String[] tmpBlock = sc.nextLine().split(",");
                int tmpBlockXCoord = Integer.parseInt(tmpBlock[0]);
                int tmpBlockYCoord = Integer.parseInt(tmpBlock[1]);

                path.add(new Block(tmpBlockXCoord, tmpBlockYCoord, blockNum));
                blockNum++;
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

    public boolean isOnPath(int xCoord, int yCoord) {

        boolean result;

        Block tmpBlock = new Block(xCoord, yCoord);

        if (path.contains(tmpBlock)) {
            result = true;
        } else result = false;

        return result;

    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Block> getPath() {
        return path;
    }

    public Block nextBlock(Block block) {
        int blockNum = block.getBlockNum();
        if (blockNum < size()-1) {
            return path.get(blockNum+1);
        } else return null;
        
    }

    public Block previousBlock(Block block) {
        int blockNum = block.getBlockNum();

        if (blockNum > 0) {
            return path.get(blockNum-1);
        } else return null;
    }

    public int size() {
        return path.size();
    }

    // zero-indexed
    public Block getBlock(int blockNum) {
        return path.get(blockNum);
    }

}
