import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sorting_types {
    public static void main(String[] args) {
        int[] array = creating_array();
        //array_view(array);
        Scanner input_key = new Scanner(System.in);
        int key;
        menu();
        do {
            switch (key = input_key.nextInt()) {
                case 1:
                    bubbleSort(array);
                    break;
                case 2:
                    selectionSort(array);
                    break;
                case 3:
                    inputSort(array);
                    break;
                case 4:
                    mergesort(array);
                    break;
                case 5:
                    quickSort(array, 1, array.length - 1);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Неверная клавиша!");
                    break;
            }
        }
        while (key != 5);
    }

    //Метод выбора меню
    private static void menu() {
        System.out.println("1) Выполнить сортировку пузырьком;");
        System.out.println("2) Выполнить сортировку выбором;");
        System.out.println("3) Выполнить сортировку вставками");
        System.out.println("4) Выполнить сортировку слиянием");
        System.out.println("5) Выполнить быструю сортировку");
        System.out.println("6) Выход из программы");
        System.out.print("Введите номер команды: ");
    }

    //Метод создания массива
    private static int[] creating_array() {
        System.out.print("Введите размерность вашего массива: ");
        Scanner input = new Scanner(System.in);
        int size = -1;
        try {
            size = input.nextInt();
        } catch (Exception e) {
            System.out.print("Ошибка при вводе размерности массива!");
        }
        int[] arr = new int[size];
        int digit;
        Random rand_digit = new Random();
        for (int index = 0; index < arr.length; index++) {
            try {
                digit = rand_digit.nextInt();
                arr[index] = digit;
            } catch (Exception e) {
                System.out.println("Ошибка при вводе элемента массива.");
            }
        }
        System.out.println("Массив успешно создан!");
        return arr;
    }

    //Метод просмотра массива
    private static void array_view(int[] arr) {
        if (arr.length != 0) {
            System.out.print("Ваш массив: ");
            for (int value : arr) {
                System.out.print(value + " ");
            }
            System.out.print("\n");
        } else {
            System.out.println("Warning: Массив не создан!");
        }
    }

    //Пузырьковая сортировка
    private static void bubbleSort(int[] arr) {
        long startTime = System.nanoTime();
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Выполнена сортировка пузырьком на " + arr.length + " элементов\n" + "Время алгоритма: "
                + (double) ((endTime - startTime) / 1000000000) + " секунд.");
    }

    //Соритровка выбором
    private static void selectionSort(int[] arr) {
    /*По очереди будем просматривать все подмножества
      элементов массива (0 - последний, 1-последний,
      2-последний,...)*/
        long startTime = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
        /*Предполагаем, что первый элемент (в каждом
           подмножестве элементов) является минимальным */
            int min = arr[i];
            int min_i = i;
        /*В оставшейся части подмножества ищем элемент,
           который меньше предположенного минимума*/
            for (int j = i + 1; j < arr.length; j++) {
                //Если находим, запоминаем его индекс
                if (arr[j] < min) {
                    min = arr[j];
                    min_i = j;
                }
            }
        /*Если нашелся элемент, меньший, чем на текущей позиции,
          меняем их местами*/
            if (i != min_i) {
                int tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Выполнена сортировка выбором на " + arr.length + " элементов\n" + "Время алгоритма: "
                + (double) ((endTime - startTime) / 1000000000) + " секунд.");
    }

    private static void inputSort(int[] arr) {
        long startTime = System.nanoTime();
        for (int left = 0; left < arr.length; left++) {
            // Вытаскиваем значение элемента
            int value = arr[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value < arr[i]) {
                    arr[i + 1] = arr[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            arr[i + 1] = value;
        }
        long endTime = System.nanoTime();
        System.out.println("Выполнена сортировка вставками на " + arr.length + " элементов\n" + "Время алгоритма: "
                + (double) ((endTime - startTime) / 1000000000) + " секунд.");
    }

    // Сортировка слиянием
    private static void mergesort(int[] arr) {
        float startTime = System.nanoTime();
        int n = arr.length;
        boolean c = true;
        int i = 0;
        int i1 = 0;
        int i2 = 0;
        int n1 = 0;
        int n2 = 0;
        double[] barr = new double[n];
        int mergelen = 0;

        barr = new double[n];
        mergelen = 1;
        while (mergelen < n) {
            if (c) {
                i = 0;
                while (i + mergelen <= n) {
                    i1 = i + 1;
                    i2 = i + mergelen + 1;
                    n1 = i + mergelen;
                    n2 = i + 2 * mergelen;
                    if (n2 > n) {
                        n2 = n;
                    }
                    while (i1 <= n1 | i2 <= n2) {
                        if (i1 > n1) {
                            while (i2 <= n2) {
                                i = i + 1;
                                barr[i - 1] = arr[i2 - 1];
                                i2 = i2 + 1;
                            }
                        } else {
                            if (i2 > n2) {
                                while (i1 <= n1) {
                                    i = i + 1;
                                    barr[i - 1] = arr[i1 - 1];
                                    i1 = i1 + 1;
                                }
                            } else {
                                if (arr[i1 - 1] > arr[i2 - 1]) {
                                    i = i + 1;
                                    barr[i - 1] = arr[i2 - 1];
                                    i2 = i2 + 1;
                                } else {
                                    i = i + 1;
                                    barr[i - 1] = arr[i1 - 1];
                                    i1 = i1 + 1;
                                }
                            }
                        }
                    }
                }
                i = i + 1;
                while (i <= n) {
                    barr[i - 1] = arr[i - 1];
                    i = i + 1;
                }
            } else {
                i = 0;
                while (i + mergelen <= n) {
                    i1 = i + 1;
                    i2 = i + mergelen + 1;
                    n1 = i + mergelen;
                    n2 = i + 2 * mergelen;
                    if (n2 > n) {
                        n2 = n;
                    }
                    while (i1 <= n1 | i2 <= n2) {
                        if (i1 > n1) {
                            while (i2 <= n2) {
                                i = i + 1;
                                arr[i - 1] = (int) barr[i2 - 1];
                                i2 = i2 + 1;
                            }
                        } else {
                            if (i2 > n2) {
                                while (i1 <= n1) {
                                    i = i + 1;
                                    arr[i - 1] = (int) barr[i1 - 1];
                                    i1 = i1 + 1;
                                }
                            } else {
                                if (barr[i1 - 1] > barr[i2 - 1]) {
                                    i = i + 1;
                                    arr[i - 1] = (int) barr[i2 - 1];
                                    i2 = i2 + 1;
                                } else {
                                    i = i + 1;
                                    arr[i - 1] = (int) barr[i1 - 1];
                                    i1 = i1 + 1;
                                }
                            }
                        }
                    }
                }
                i = i + 1;
                while (i <= n) {
                    arr[i - 1] = (int) barr[i - 1];
                    i = i + 1;
                }
            }
            mergelen = 2 * mergelen;
            c = !c;
        }
        if (!c) {
            i = 1;
            do {
                arr[i - 1] = (int) barr[i - 1];
                i = i + 1;
            } while (i <= n);
        }
        float endTime = System.nanoTime();
        System.out.println("Выполнена сортировка слиянием " + arr.length + " элементов\n" + "Время алгоритма: "
                + (double) ((endTime - startTime) / 1000000000) + " секунд.");
    }

    private static int partition(int[] array, int begin, int end) {
        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[end]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        int temp = array[end];
        array[end] = array[counter];
        array[counter] = temp;
        return counter;
    }

    // Реализация быстрой сортировки
    private static void quickSort(int[] array, int begin, int end) {
        float startTime = System.nanoTime();
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
        float endTime = System.nanoTime();
        System.out.println("Выполнена быстрая сортировка " + array.length + " элементов\n" + "Время алгоритма: "
                + (double) ((endTime - startTime) / 1000000000) + " секунд.");
    }
}
