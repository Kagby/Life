package dev.denisov.life;

public class Buffer {

    // Заполняем буфферный массив глядя на основной
    static void fillBuffer(char[][] field, char[][] bufferField, int rows, int cols, Obj generation) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; ++j) {
                int neighbours = 0;

                // считаем соседей
                for (int r = -1; r < 2; ++r) {
                    for (int c = -1; c < 2; ++c) {
                        if (i + r >= 0 && i + r < rows && j + c >= 0 && j + c < cols) {
                            if (field[i + r][j + c] == '*') {
                                neighbours++;
                            }
                        }
                    }
                }

                // себя не считаем за соседа
                if (field[i][j] == '*') {
                    --neighbours;
                }

                // условие жизни
                if (field[i][j] == '.' && neighbours == 3) {
                    bufferField[i][j] = '*';
                }

                else if (field[i][j] == '*' && (neighbours == 2 || neighbours == 3)) {
                    bufferField[i][j] = '*';
                }

                else {
                    bufferField[i][j] = '.';
                }
            }
        }
         generation.setGeneration(generation.getGeneration() + 1);
    }

    // сравниеваем 2 массива
    static boolean compare(char[][] field, char[][] bufferField, int rows, int cols) {
        boolean result = true;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (field[i][j] != bufferField[i][j]) {
                    result = false;
                }

            }
        }
        return result;
    }
}