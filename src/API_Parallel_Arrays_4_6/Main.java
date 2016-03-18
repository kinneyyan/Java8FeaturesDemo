package API_Parallel_Arrays_4_6;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 4.6 并行数组
 * 用于支持并行数组处理。最重要的方法是parallelSort()，可以显著加快多核机器上的数组排序
 */
public class Main {

    //使用parallelSetAll()方法生成20000个随机数，然后使用parallelSort()方法进行排序。
    //这个程序会输出乱序数组和排序数组的前10个元素
    public static void main(String[] args) {
        long[] arrayOfLong = new long[20000];

        Arrays.parallelSetAll(arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();

        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();
    }
}
