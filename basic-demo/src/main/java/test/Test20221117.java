package test;

import java.io.*;

/**
 * @ClassName: Test20221117
 * @Description: TODO
 * @Author: yyh
 * @Date: 2022/11/17 10:26
 */
public class Test20221117 {

    public static void main(String[] args) throws IOException {
        fun1();
    }

    private static void fun1() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\yuyanhong\\Desktop/极数充stopReasonMapping.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = bufferedReader.readLine();
        int start = 1900;
        StringBuilder sb = new StringBuilder("INSERT INTO yunkc_base.base_t_dict (dict_id, dict_type, dict_type_name, code, name, sort, create_by, create_time, update_by, update_time, del_flag) VALUES ");
        while (line != null) {
            String[] strings = line.split(" ");
            sb.append("(" + start++ + ", 'charge_endReason', '停止原因', 'jsc-" + strings[0] + "', '" + strings[1] + "', 1, 'admin', '2022-11-17 10:51:51', 'admin', '2022-11-1 10:51:51', '0'), \n");
            line = bufferedReader.readLine();
        }
        System.out.println(sb);
        bufferedReader.close();
        fileInputStream.close();
    }

    private static void fun2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\yuyanhong\\Desktop/stopReasonMapping.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = bufferedReader.readLine();
        int start = 1600;
        StringBuilder sb = new StringBuilder("INSERT INTO yunkc_base.base_t_dict (dict_id, dict_type, dict_type_name, code, name, sort, create_by, create_time, update_by, update_time, del_flag) VALUES ");
        while (line != null) {
            String[] strings = line.split(":");
            sb.append("(" + start++ + ", 'charge_endReason', '停止原因', 'wm-" + strings[0] + "', '" + strings[1] + "', 1, 'admin', '2022-11-17 10:51:51', 'admin', '2022-11-1 10:51:51', '0'), \n");
            line = bufferedReader.readLine();
        }
        System.out.println(sb);
        bufferedReader.close();
        fileInputStream.close();
    }
}
