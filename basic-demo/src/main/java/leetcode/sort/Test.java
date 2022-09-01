package leetcode.sort;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author yyh
 * @Description Test
 * @Date 2020/9/8 13:49
 */
public class Test {

    public static Map<String, String> getAttributes(String attributes) {
        Map<String, String> attr = new HashMap<>();
        Matcher m = Pattern.compile("(\\w+):(.*?)(?:;\\w+:|$)").matcher(attributes);
        while (m.find()) {
            attr.put(m.group(1), m.group(2));
        }
        return attr;
    }

    //｛a:1;b:    xxyyzz;c:ab  cde;｝
    public static void main(String[] args) {
//        String s = "Overtime=true,TransportCosts=1= 1,two, three,Billable=7200";
//        String s1 = "｛a:1;b:    xxyyzz;c:ab  cde;｝";
//        Map<String,String> map = getAttributes(s1);
//        for (Map.Entry entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }

        test();
    }


    static void test() {
        List<String> a1 = new ArrayList<String>();
        a1.add("test1");
        a1.add("test2");
        a1.add("test3");
        List<String> a2 = new ArrayList<String>();
        a2.add("test4");
        a2.add("test5");
        a2.add("test6");
        List<List> all = new ArrayList<List>();
        all.add(a1);
        all.add(a2);
        System.out.println(all);
        List list = all.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(list);
    }

    static void test2() {
//        List<String> udtos = new ArrayList<>();
//        final List<CashPreRepayInfoDTO> pdtos = new ArrayList<>();
//
//        pdtos.addAll(Arrays.asList(c1,c2,c3));
//        udtos.addAll(Arrays.asList(r1,r2,r3));
//        List<RepaymentPlanTrialDTO> list =
//                udtos.stream().flatMap(x -> pdtos.stream().filter(y -> x.getPeriod().intValue() == y.getPeriodNum()).map(z -> new RepaymentPlanTrialDTO(x.getPeriod(), x.getShouldRepayTime().toLocalDate(), x.getShouldRepayTotal(), z.getClearanceAmount()))).collect(Collectors.toList());
    }
}
