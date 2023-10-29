package ru.bev0802;

/*Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, 
если они одинаковые, и false в противном случае. Массивы могут быть любого типа данных, но должны 
иметь одинаковую длину и содержать элементы одного типа.*/

public class Main {
    public static void main(String[] args) {
        // Проверка сравнеия стринковых массивов
        String[] arrayStr1 = { "Саша", "Маша", "Вася" };
        String[] arrayStr2 = { "Саша", "Маша", "Вася" };
        String[] arrayStr3 = { "Виктор", "Маша", "Саша" };

        boolean resulttrue = compareArrays(arrayStr1, arrayStr2);
        System.out.println("Сравнение одинаковых массивовов строк: " + resulttrue);

        boolean resultfalse = compareArrays(arrayStr1, arrayStr3);
        System.out.println("Сравнение не одинаковых массивовов строк: " + resultfalse);

        // Проверка сравнеия целочисленных массивов
        Integer[] arrayInt1 = { 1, 2, 3 };
        Integer[] arrayInt2 = { 1, 2, 3 };
        Integer[] arrayInt3 = { 4, 5, 6, 7 };

        boolean resulttrue1 = compareArrays(arrayInt1, arrayInt2);
        System.out.println("\nСравление одинаковых массивов целых чисел: " + resulttrue1);

        boolean resultfalse1 = compareArrays(arrayInt1, arrayInt3);
        System.out.println("Сравнение не одинаковых массивов целых чисел: " + resultfalse1);

        // Проверка сравнеия дробных массивов
        Double[] arrayDouble1 = { 1.1, 2.2, 3.3 };
        Double[] arrayDouble2 = { 1.1, 2.2, 3.3 };
        Double[] arrayDouble3 = { 4.4, 5.5, 6.6 };

        boolean resulttrue2 = compareArrays(arrayDouble1, arrayDouble2);
        System.out.println("\nСравнение одинаковых массивов дробных чисел: " + resulttrue2);

        boolean resultfalse2 = compareArrays(arrayDouble1, arrayDouble3);
        System.out.println("Сравнение не одинаковых массивов дробных чисел: " + resultfalse2);

        // Проверка сравнеия float массивов
        Float[] arrayFloat1 = { 1.1f, 2.2f, 3.3f };
        Float[] arrayFloat2 = { 1.1f, 2.2f, 3.3f };
        Float[] arrayFloat3 = { 4.4f, 5.5f, 6.6f };

        boolean resultTrue3 = compareArrays(arrayFloat1, arrayFloat2);
        System.out.println("\nСравнение одинаковых float массивов: " + resultTrue3);

        boolean resultFalse3 = compareArrays(arrayFloat1, arrayFloat3);
        System.out.println("Сравнение не одинаковых float массивов: " + resultFalse3);

    }
    // Проверка сравнеия символьных массивов
    // char[] arrayChar1 = {'a', 'b', 'c'};
    // char[] arrayChar2 = {'a', 'b', 'c'};
    // char[] arrayChar3 = {'x', 'y', 'z'};

    // boolean resulttrue3 = compareArrays(arrayChar1, arrayChar2);
    // System.out.println("Сравнение одинаковых символьных массивов: " +
    // resulttrue3);

    // boolean resultfalse3 = compareArrays(arrayChar1, arrayChar3);
    // System.out.println("Сравнение не одинаковых символьных массивов: " +
    // resultfalse3);

    // Проверка сравнеия логических массивов
    // boolean[] arrayBool1 = {true, false, true};
    // boolean[] arrayBool2 = {true, false, true};
    // boolean[] arrayBool3 = {false, true, false};

    // boolean resultTrue4 = Arrays.equals(arrayBool1, arrayBool2);
    // System.out.println("Сравнение одинаковых логических массивов: " +
    // resultTrue4);

    // boolean resultFalse4 = Arrays.equals(arrayBool1, arrayBool3);
    // System.out.println("Сравнение не одинаковых логических массивов: " +
    // resultFalse4);

    public static <T> boolean compareArrays(T[] array1, T[] array2) {
        if (array1.length != array2.length) {
            System.out.println("Массивы не одинакового размера!");
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].equals(array2[i])) {
                return false;
            }
        }

        return true;
    }
}
