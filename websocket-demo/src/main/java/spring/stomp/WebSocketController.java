package spring.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import spring.stomp.message.Client2ServerMessage;
import spring.stomp.message.Server2ClientMessage;

import java.security.Principal;

/**
 * @author 996kid@gmail.com
 * @Description Controller
 * @Date 2021/1/26 17:09
 */
@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/des/getResponse")
    public Server2ClientMessage say(Client2ServerMessage message) throws Exception {
        Thread.sleep(3000);
        return new Server2ClientMessage("Hello," + message.getName() + "!");
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg){
        // 在 SpringMVC 中，可以直接在参数中获得 principal，principal 中包含当前用户信息
        if (principal.getName().equals("nasus")){
            // 硬编码，如果发送人是 nasus 则接收人是 chenzy 反之也成立。
            // 通过 messageingTemplate.convertAndSendToUser 方法向用户发送信息，参数一是接收消息用户，参数二是浏览器订阅地址，参数三是消息本身
            messagingTemplate.convertAndSendToUser("chenzy",
                    "/queue/notifications",principal.getName()+"-send:" + msg);
        } else {
            messagingTemplate.convertAndSendToUser("nasus",
                    "/queue/notifications",principal.getName()+"-send:" + msg);
        }
    }
}
