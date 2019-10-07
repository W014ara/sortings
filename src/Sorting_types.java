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
                    mergeSort(array, 1, array.length - 1);
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

    private static void mergeSort(int[] arr, int right, int left) {
        long startTime = System.nanoTime();
        if (right <= left) return;
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
        long endTime = System.nanoTime();
        System.out.println("Выполнена сортировка слиянием на " + arr.length + " элементов\n" + "Время алгоритма: "
                + (double) ((endTime - startTime) / 1000000000) + " секунд.");
    }

    private static void merge(int[] array, int left, int mid, int right) {
        // вычисляем длину
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // создаем временные подмассивы
        int[] leftArray = new int[lengthLeft];
        int[] rightArray = new int[lengthRight];

        // копируем отсортированные массивы во временные
        System.arraycopy(array, left, leftArray, 0, lengthLeft);
        for (int i = 0; i < lengthRight; i++)
            rightArray[i] = array[mid + i + 1];

        // итераторы содержат текущий индекс временного подмассива
        int leftIndex = 0;
        int rightIndex = 0;

        // копируем из leftArray и rightArray обратно в массив
        for (int i = left; i < right + 1; i++) {
            // если остаются нескопированные элементы в R и L, копируем минимальный
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            // если все элементы были скопированы из rightArray, скопировать остальные из leftArray
            else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }
            // если все элементы были скопированы из leftArray, скопировать остальные из rightArray
            else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
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
        long startTime = System.nanoTime();
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
        long endTime = System.nanoTime();
        System.out.println("Выполнена быстрая сортировка " + array.length + " элементов\n" + "Время алгоритма: "
                + (double) ((endTime - startTime) / 1000000000) + " секунд.");
    }
}
