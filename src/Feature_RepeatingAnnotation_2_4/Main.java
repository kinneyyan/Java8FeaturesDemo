package Feature_RepeatingAnnotation_2_4;

import java.lang.annotation.*;

/**
 * 2.4 重复注解
 * 注解有一个很大的限制是：在同一个地方不能多次使用同一个注解。
 * Java 8打破了这个限制，引入了重复注解的概念，允许在同一个地方多次使用同一个注解。
 * <p>
 * 在Java 8中使用@Repeatable注解定义重复注解，
 * 实际上，这并不是语言层面的改进，而是编译器做的一个trick，底层的技术仍然相同
 */
public class Main {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    }

    //若要使用重复注解,貌似一定要申明如下存放Filter注解的容器
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    //Filter接口若不使用@Repeatable(Filters.class)注解修饰,是不允许重复注解的
    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable {
    }

    public static void main(String[] args) {
        //反射API提供了一个新的方法：getAnnotationsByType()，可以返回某个类型的重复注解
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
    }
}
