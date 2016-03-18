package API_Stream_4_2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 4.2 Streams
 * 新增的Stream API（java.util.stream）将生成环境的函数式编程引入了Java库中
 * Steam API极大得简化了集合操作（后面我们会看到不止是集合）
 */
public class Main {

    public static void main(String[] args) {
        Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8)
        );

        //Q:这个task集合中一共有多少个OPEN状态的点？
        long sumOfOpenTask = tasks.stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                .count();
        System.out.println("sumOfOpenTask: " + sumOfOpenTask);
        //Q:这个task集合中所有OPEN状态的分数总和
        long totalPointsOfOpenTasks = tasks.stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                //mapToInt操作基于每个task实例的Task::getPoints方法将task流转换成Integer集合
                .mapToInt(Task::getPoints)
                .sum();
        System.out.println("Total points: " + totalPointsOfOpenTasks);
        //Q:打印这个task集合中所有元素的信息
        tasks.stream().forEach(task -> System.out.print(task.toString() + " "));
        System.out.println();

        //stream的另一个价值是创造性地支持并行处理（parallel processing）。对于上述的tasks集合，我们可以用下面的代码计算所有任务的点数之和：
        //*****以下方法调用还没看懂*****
        final double totalPoints = tasks
                .stream()
                .parallel()
                .map(task -> task.getPoints()) // or map( Task::getPoints )
                .reduce(0, Integer::sum);

        System.out.println("Total points (all tasks): " + totalPoints);

        //对于一个集合，经常需要根据某些条件对其中的元素分组。
        //利用steam提供的API可以很快完成这类任务，代码如下：
        // Group tasks by their status
        final Map<Status, List<Task>> map = tasks
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);

        //Q:如何计算集合中每个任务的点数在集合中所占的比重
        // Calculate the weight of each tasks (as percent of total points)
        final Collection<String> result = tasks
                .stream()                                        // Stream< String >
                .mapToInt(Task::getPoints)                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble(points -> points / totalPoints)   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong(weight -> (long) (weight * 100)) // LongStream
                .mapToObj(percentage -> percentage + "%")      // Stream< String>
                .collect(Collectors.toList());                 // List< String >

        System.out.println(result);

        //Stream API不仅可以作用于Java集合，传统的IO操作（从文件或者网络一行一行得读取数据）
        final Path path = new File("text.txt").toPath();
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            //Stream的onClose方法会在forEach方法走完执行
            lines.onClose(() -> System.out.println("Done!")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        private Task(Status status, Integer points) {
            this.status = status;
            this.points = points;
        }

        public Status getStatus() {
            return status;
        }

        public Integer getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }

    private enum Status {
        OPEN, CLOSED
    }
}
