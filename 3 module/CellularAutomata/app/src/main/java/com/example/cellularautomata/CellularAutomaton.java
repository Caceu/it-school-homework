package com.example.cellularautomata;

public class CellularAutomaton {
    private int width;
    private int height;
    private boolean[][] grid;
    private boolean[] rule;
    
    public CellularAutomaton(int height, int width, int ruleSet) {
        this.width = width;
        this.height = height;
        this.rule = new boolean[8];
        for(int i = 0; i < 8; i++)
            this.rule[i] = false;
        this.grid = new boolean[height][width];
        this.applyRules(ruleSet);
    }

    private void generate() {
        grid = new boolean[height][width];
        grid[0] = new boolean[width];
        for (int i = 0; i < width; i++)
            grid[0][i] = false;
        grid[0][width / 2] = true;

        for (int y = 1; y < height; y++) {
            boolean[] slice = new boolean[3];
            slice[0] = false;
            slice[1] = grid[y - 1][0];
            slice[2] = grid[y - 1][1];
            grid[y] = new boolean[width];
            grid[y][0] = apply(slice);
            for (int x = 1; x < width - 1; x++) {
                slice[0] = grid[y - 1][x - 1];
                slice[1] = grid[y - 1][x];
                slice[2] = grid[y - 1][x + 1];
                grid[y][x] = apply(slice);
            }
            slice[0] = grid[y - 1][width - 2];
            slice[1] = grid[y - 1][width - 1];
            slice[2] = false;
            grid[y][width - 1] = apply(slice);
        }
    }

    private boolean apply(boolean[] input) {
        byte rule_number = 7;
        if(input[0])
            rule_number -= 4;
        if(input[1])
            rule_number -= 2;
        if(input[2])
            rule_number -= 1;
        return rule[rule_number];
    }

    public void applyRules(int ruleSet) {
        rule = new boolean[8];
        for(int i = 0; i < 8; i++)
            rule[i] = false;
        for(int i = 7; i >= 0; i--){
            rule[i] = ruleSet % 2 != 0;
            ruleSet = ruleSet >> 1;
        }
        this.generate();
    }
    public void setWidth(int width) {
        this.width = width;
        this.generate();
    }

    public void setHeight(int height) {
        this.height = height;
        this.generate();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public boolean[] getRule() {
        return rule;
    }
}
