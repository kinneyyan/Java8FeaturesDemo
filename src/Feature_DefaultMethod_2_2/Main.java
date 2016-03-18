package Feature_DefaultMethod_2_2;

/**
 * 2.2 接口的默认方法和静态方法
 * 默认方法和抽象方法之间的区别在于抽象方法需要实现，而默认方法不需要。
 * 接口提供的默认方法会被接口的实现类继承或者覆写
 */
public class Main {

    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    public static void main(String[] args) {
        //下面的代码片段整合了默认方法和静态方法的使用场景：
        Defaulable defaulable = DefaulableFactory.create(() -> new DefaultableImpl());
        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(() -> new OverridableImpl());
        System.out.println(defaulable.notRequired());
    }

}
