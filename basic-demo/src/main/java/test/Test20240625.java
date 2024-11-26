package test;

import lombok.Data;

public class Test20240625 {

    @Data
    static
    class A {
        private String s;
    }

    public static void main(String[] args) {
//        Set set = new HashSet();
//        set.add(new ExpenseItemDto(1, "111"));
//        set.add(new ExpenseItemDto(2, "222"));
//        set.add(new ExpenseItemDto(1, "111"));
//        System.out.println(set);
//
//        System.out.println(new Integer(980) == 980);
//        System.out.println("山东省青岛市".contains("青岛市"));



//        File file1 = new File("C:\\Users\\Administrator\\Desktop\\996kid\\新建文件夹\\lib");
//        File[] files = file1.listFiles();
//        Set<String> names = new HashSet<>();
//        Arrays.stream(files).forEach(i -> names.add(i.getName()));
//
//        File file2 = new File("C:\\Users\\Administrator\\Desktop\\996kid\\ReimbursementService\\ReimbursementService\\target\\lib");
//        File[] files2= file2.listFiles();
//        Arrays.stream(files2).map(File::getName).filter(i -> !names.contains(i)).forEach(System.out::println);

          String s = "abc";
          A a = new A();
          a.setS(s);
          s = "def";
        System.out.println(a.s);
    }
}
