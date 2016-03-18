package Feature_LambdaAndFunctionalInterface_2_1;

import java.util.Arrays;

/**
 * Java 8 Features Sample
 * 2.1 Lambda表达式和函数式接口
 *
 * @link http://www.jianshu.com/p/5b800057f2d8
 */
public class Main {

    public static void main(String[] args) {
        Arrays.asList("a", "b", "c").forEach(e -> System.out.print(e));
        //如果Lambda表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来，
        //类似于Java中的函数体，例如：
        Arrays.asList("a", "b", "d").forEach(e -> {
            System.out.print(e);
            System.out.print(e);
        });

        //Lambda表达式有返回值，返回值的类型也由编译器推理得出。
        //如果Lambda表达式中的语句块只有一行，则可以不用使用return语句，下列两个代码片段效果相同：
        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));
        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
    }
}
