package streamapi;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yyh
 * @Description StreamApi
 * @Date 2020/9/9 22:55
 */
public class StreamApi {

    public static void main(String[] args) {
        test4();
    }

    static void test1() {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);

        //filter a stream of string[], and return a string[]?

        temp.flatMap(x -> Arrays.stream(x));
        Stream<String[]> stream = temp.filter(x -> "a".equals(x.toString()));

        stream.forEach(System.out::println);
    }

    static void test2() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        List list = inputStream.
                flatMap((childList) -> childList.stream()).collect(Collectors.toList());
        System.out.println(list);
    }

    static void test3() {
        List<List<String>> listOut = new ArrayList();
        List<String> listOne = new ArrayList();
        List<String> listTwo = new ArrayList();
        listOne.add("1");
        listOne.add("2");
        listOne.add("3");
        listTwo.add("5");
        listTwo.add("6");
        listOut.add(listOne);
        listOut.add(listTwo);
        System.out.println(listOut);
        List<String> list = listOut.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void test4() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("欧阳雪",18,"中国",'F'));
        personList.add(new Person("Tom",24,"美国",'M'));
        personList.add(new Person("Harley",22,"英国",'F'));
        personList.add(new Person("向天笑",20,"中国",'M'));
        personList.add(new Person("李康",22,"中国",'M'));
        personList.add(new Person("小梅",20,"中国",'F'));
        personList.add(new Person("何雪",21,"中国",'F'));
        personList.add(new Person("李康",22,"中国",'M'));

        personList.stream().collect(Collectors.toMap((Function<Person, Object>) person -> person.getName(),
                person -> person.getCountry(), new BinaryOperator<Object>() {

            @Override
            public Object apply(Object o, Object o2) {
                return o;
            }
        }));


        personList.stream().map(new Function<Person, Object>() {

            @Override
            public Object apply(Person person) {
                return null;
            }
        }).map(new Function<Object, Object>() {

            @Override
            public Object apply(Object o) {
                return null;
            }
        });

        List<Integer> listAge = personList.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getAge).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        personList.stream()
                .filter(person -> person.getAge() > 18)
                .map(person -> person.getAge()).forEach(stringBuilder::append);

        personList.stream()
                .filter(person -> person.getAge() > 18)
                .map(person -> person.getAge()).forEach(integer -> stringBuilder.append(integer));
        System.out.println(listAge);

        List<Person> listPerson = personList.stream()
                .filter(person -> person.getAge() > 18)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(listPerson);

        List<Person> listSorted = personList.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(listSorted);

        Map<Person, Integer> map = new HashMap<>();

        // to map
        personList.stream().collect(Collectors.toMap(StreamApi::test, Person::getName, (s, s2) -> s));
        personList.stream().collect(Collectors.toMap(Person::getAge, i -> i));
        personList.stream().collect(Collectors.toMap(Person::getAge, map::get, (s, s2) -> s));

        //
        personList.stream().collect(Collectors.toMap(i -> i, map::get));
    }

    static char test(Person person) {
        return person.getSex();
    }
}
