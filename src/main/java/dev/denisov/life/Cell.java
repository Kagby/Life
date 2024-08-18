package dev.denisov.life;

// считаем живые клетки
public class Cell {

    private int aliveCells = 0;

    public int getLifeCells(char[][] field, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; ++j) {
                if (field[i][j] == '*') {
                    ++ aliveCells;
                }
            }
        }
        return aliveCells;
    }
}