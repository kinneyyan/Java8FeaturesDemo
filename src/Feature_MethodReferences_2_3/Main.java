package Feature_MethodReferences_2_3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 2.3 方法引用
 */
public class Main {

    public static void main(String[] args) {
        //第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class<T>::new。
        //注意：这个构造器没有参数。
        Car car = Car.create(Car::new);
        //Car car = Car.create(() -> new Car());//同上

        //第二种方法引用的类型是静态方法引用，语法是Class::static_method。
        //注意：这个方法接受一个Car类型的参数。
        List<Car> cars = Arrays.asList(car);
        cars.forEach(Car::collide);
        //cars.forEach(t -> Car.collide(t));//同上

        //第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method
        //注意，这个方法没有定义入参：
        cars.forEach(Car::repair);
        //cars.forEach(t -> t.repair());//同上

        //第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。
        //注意：这个方法接受一个Car类型的参数：
        Car otherCar = Car.create(Car::new);
        cars.forEach(otherCar::follow);
        //cars.forEach(t -> otherCar.follow(t));//同上
    }

    public static class Car {
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }

}
