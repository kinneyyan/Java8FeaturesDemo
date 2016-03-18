package Feature_LargerAnnotationRange_2_6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2.6 拓宽注解的应用场景
 * <p>
 * Java 8拓宽了注解的应用场景。
 * 现在，注解几乎可以使用在任何元素上：局部变量、接口类型、超类和接口实现类，甚至可以用在函数的异常定义上。
 */
public class Main {

    //ElementType.TYPE_USER和ElementType.TYPE_PARAMETER是Java 8新增的两个注解
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
    public @interface NonEmpty {
    }

    public static class Holder<@NonEmpty T> extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
        }
    }

    public static void main(String[] args) {
        Holder<String> holder = new Holder<>();
    }
}
