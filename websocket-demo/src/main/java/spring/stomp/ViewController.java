package spring.stomp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 996kid@gmail.com
 * @Description ResourceController
 * @Date 2021/1/26 17:38
 */
@Controller
public class ViewController {

    @GetMapping("/demo")
    public String getView(){
        return "demo";
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/chat")
    public String getChatView() {
        return "chat";
    }
}
