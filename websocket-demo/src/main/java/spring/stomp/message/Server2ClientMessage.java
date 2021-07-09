package spring.stomp.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Server2ClientMessage {

    private String responseMessage;

}