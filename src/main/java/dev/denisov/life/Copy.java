package dev.denisov.life;

public class Copy {
    static void copyArray(char[][] field, char[][] bufferField, int rows, int cols){
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                field[i][j] = bufferField[i][j];
            }
        }
    }
}
