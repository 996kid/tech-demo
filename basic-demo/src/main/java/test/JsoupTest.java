package test;

import org.jsoup.Jsoup;

/**
 * @author 996kid@gmail.com
 * @Description JsoupTest
 * @Date 2022/6/8 10:25
 */
public class JsoupTest {

    public static void main(String[] args) {
        String str = "<p><div data-v-44c574b8=\\\"\\\"><div data-v-44c574b8=\\\"\\\"><div data-v-44c574b8=\\\"\\\"><div data-v-44c574b8=\\\"\\\"><div data-v-44c574b8=\\\"\\\"><div data-v-44c574b8=\\\"\\\"><p>这边也会帮您和当地反馈的哦</div></div></div></div></div></div><div data-v-44c574b8=\\\"\\\"><div data-v-44c574b8=\\\"\\\"><div data-v-44c574b8=\\\"\\\"><br></div></div></div></p>";
        System.out.println(Jsoup.parse(str).text());
    }
}
