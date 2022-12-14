package com.mfvanek.experiments.spring;

import java.util.*;

/**
 * Дано целое число n. Требуется вывести все правильные скобочные последовательности длины 2 ⋅ n, упорядоченные лексикографически (см. https://ru.wikipedia.org/wiki/Лексикографический_порядок).
 *
 * В задаче используются только круглые скобки.
 *
 * Желательно получить решение, которое работает за время, пропорциональное общему количеству правильных скобочных последовательностей в ответе, и при этом использует объём памяти, пропорциональный n.
 *
 * Формат ввода
 * Единственная строка входного файла содержит целое число n, 0 ≤ n ≤ 11
 *
 * Формат вывода
 * Выходной файл содержит сгенерированные правильные скобочные последовательности, упорядоченные лексикографически.
 */
public class JaTest4 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = Integer.parseInt(scanner.nextLine());
            if (n > 0) {
                char[] str = new char[2*n];
                gen(0, 0, n, 0, str);
            }
        }
    }

    private static void gen(int leftBraces, int rightBraces, int n, int iteration, char[] str) {
        if (leftBraces == n && rightBraces == n) {
            System.out.println(str);
        } else {
            if (leftBraces < n) {
                str[iteration] = '(';
                gen(leftBraces + 1, rightBraces, n, iteration + 1, str);
            }
            if (rightBraces < leftBraces) {
                str[iteration] = ')';
                gen(leftBraces, rightBraces + 1, n, iteration + 1, str);
            }
        }
    }
}
