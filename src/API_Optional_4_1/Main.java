package API_Optional_4_1;

import java.util.Optional;

/**
 * 4.1 Optional
 * <p>
 * Optional仅仅是一个容器：存放T类型的值或者null。它提供了一些有用的接口来避免显式的null检查
 */
public class Main {
    public static void main(String[] args) {
        Optional<String> fullName = Optional.ofNullable(null);
        //如果Optional实例持有一个非空值，则isPresent()方法返回true，否则返回false
        System.out.println("Full Name is set? " + fullName.isPresent());
        //orElseGet和orElse方法类似,都是Optional事例为null时取默认值
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[orElseGet]"));
        System.out.println("Full Name: " + fullName.orElse("[orElse]"));
        //map() 方法可以将现有的Optional实例的值转换成新的值
        System.out.println(fullName.map(s -> "Hey " + s + "!"));
        fullName = Optional.of("Arsenal");
        System.out.println(fullName.map(s -> "Hey " + s + "!"));

        System.out.println("-----------");

        Optional<String> firstName = Optional.of("Tom");
        System.out.println("First Name is set? " + firstName.isPresent());
        System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
        System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }
}
