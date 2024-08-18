package dev.denisov.life;

public class Print {

    void print(char[][] field, int rows, int cols, int generation, int alive_cells) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Генерация: " + generation + ". Живых клеток: " + alive_cells);
    }
}