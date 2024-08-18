package dev.denisov.life;

import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Указываем путь к файлу
        Path path = Paths.get("src/main/in0.txt");

        // Счётчик генераций
        Obj generation = new Obj();

        // Читаем все строки файла в список lines
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int rows = Integer.parseInt(lines.get(0).split("\\s+")[0]); // Строки: первая строка файла до пробела
        int cols = Integer.parseInt(lines.get(0).split("\\s+")[1]); // Столбцы: первая строка файла после пробела

        // Создаём основной массив и заполняем точками
        char[][] field = new char[rows][cols]; // Рисуем поле
        for (char[] chars : field) {
            Arrays.fill(chars, '.');
        }

        // Помещаем в основной массив "*" на координаты из файла
        for(int i = 1, x = lines.size(); i < x; i++) {
            int r = Integer.parseInt(lines.get(i).split("\\s+")[0]);
            int c = Integer.parseInt(lines.get(i).split("\\s+")[1]);
            field[r][c] = '*';
        }

        // Создаём буферный двумерный массив
        char[][] bufferField = new char[rows][cols];
        for (char[] chars : bufferField) {
            Arrays.fill(chars, '.');
        }

        while (true) {
            // считаем живые клетки
            Cell cell = new Cell();
            int lifeCells = cell.getLifeCells(field, rows, cols);

            // Проверка на живые клетки
            if (lifeCells < 1) {
                System.out.println("Все клетки мертвы. Потрачено");
            }

            // Выводим массив 1
            Print printField = new Print();
            printField.print(field, rows, cols, generation.getGeneration(), lifeCells);

            // Заполняем буфферный массив глядя на основной
            Buffer.fillBuffer(field, bufferField, rows, cols, generation);

            //проверка на стагнацию
            if (Buffer.compare(field, bufferField, rows, cols)) {
                //sleep(5);
                Thread.sleep(200);
                System.out.println("clear");
                printField.print(field, rows, cols, generation.getGeneration(), lifeCells);
                System.out.println("Мир в стагнации. Потрачено");
                break;
            }

            // копируем массив 2 в 1
            Copy.copyArray(field, bufferField, rows, cols);

            Thread.sleep(200);
        }
    }
}
