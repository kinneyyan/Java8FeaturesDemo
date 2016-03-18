package Feature_LambdaAndFunctionalInterface_2_1;

import java.util.function.Supplier;

/**
 * 函数接口指的是只有一个函数的接口，这样的接口可以隐式转换为Lambda表达式
 * 默认方法和静态方法不会破坏函数式接口的定义
 */
@FunctionalInterface
public interface Functional {
    void method();

    // Interfaces now allow default methods, the implementer may or
    // may not implement (override) them.
    default void defaultMethod() {
        System.out.println("I'm defaultMethod");
    }

    // Interfaces now allow static methods
    static Functional create(Supplier<Functional> supplier) {
        return supplier.get();
    }
}
