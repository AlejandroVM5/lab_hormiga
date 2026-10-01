package com.example.hormiga.dto;

public class TableroDto {
    private int[][] matriz;
    private int antX;
    private int antY;
    private String antDir;

    public TableroDto(int[][] matriz, int antX, int antY, String antDir) {
        this.matriz = matriz;
        this.antX = antX;
        this.antY = antY;
        this.antDir = antDir;
    }

    public int[][] getMatriz() { return matriz; }
    public int getAntX() { return antX; }
    public int getAntY() { return antY; }
    public String getAntDir() { return antDir; }
}
