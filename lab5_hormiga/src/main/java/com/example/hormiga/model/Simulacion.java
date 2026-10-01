package com.example.hormiga.model;

public class Simulacion {
    private int[][] matriz;
    private int antX;
    private int antY;
    private char antDir; // 'U', 'D', 'L', 'R'
    private int vision;

    public Simulacion(int rows, int cols, int startX, int startY, char startDir, int vision) {
        this.matriz = new int[rows][cols]; // 0 = blanco (miga), 1 = negro (feromona)
        this.antX = startX;
        this.antY = startY;
        this.antDir = startDir;
        this.vision = vision;
    }

    public void avanzar(int pasos) {
        for (int i = 0; i < pasos; i++) {
            verificarLimitesYExpandir();
            
            // 1. Leer color actual
            int color = matriz[antY][antX];
            
            // 2. Girar y cambiar color
            if (color == 0) {
                // Blanco: Girar 90 a la derecha
                girarDerecha();
                matriz[antY][antX] = 1; // Dejar feromona
            } else {
                // Negro: Girar 90 a la izquierda
                girarIzquierda();
                matriz[antY][antX] = 0; // Dejar miga
            }
            
            // 3. Avanzar 1 paso
            switch (antDir) {
                case 'U': antY--; break;
                case 'D': antY++; break;
                case 'L': antX--; break;
                case 'R': antX++; break;
            }
        }
    }

    private void verificarLimitesYExpandir() {
        int rows = matriz.length;
        int cols = matriz[0].length;
        
        // Si la hormiga sale de los límites, expandimos la matriz
        if (antX < 0 || antX >= cols || antY < 0 || antY >= rows) {
            // El aumento de visión se distribuye equitativamente a los lados
            int expansionPorLado = vision / 2;
            int newRows = rows + vision;
            int newCols = cols + vision;
            
            int[][] newMatriz = new int[newRows][newCols];
            
            // Copiamos la matriz vieja al centro de la nueva
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    newMatriz[r + expansionPorLado][c + expansionPorLado] = matriz[r][c];
                }
            }
            
            matriz = newMatriz;
            // Actualizamos la posición de la hormiga relativa al nuevo centro
            antX += expansionPorLado;
            antY += expansionPorLado;
        }
    }

    private void girarDerecha() {
        switch (antDir) {
            case 'U': antDir = 'R'; break;
            case 'R': antDir = 'D'; break;
            case 'D': antDir = 'L'; break;
            case 'L': antDir = 'U'; break;
        }
    }

    private void girarIzquierda() {
        switch (antDir) {
            case 'U': antDir = 'L'; break;
            case 'L': antDir = 'D'; break;
            case 'D': antDir = 'R'; break;
            case 'R': antDir = 'U'; break;
        }
    }

    public int[][] getMatriz() { return matriz; }
    public int getAntX() { return antX; }
    public int getAntY() { return antY; }
    public char getAntDir() { return antDir; }
}
