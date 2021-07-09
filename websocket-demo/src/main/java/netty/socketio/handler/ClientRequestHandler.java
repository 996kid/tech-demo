package netty.socketio.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.OnMessage;

/**
 * @author 996kid@gmail.com
 * @Description ClientRequestHandler
 * @Date 2021/2/1 10:44
 */
@Slf4j
public class ClientRequestHandler {

    @OnConnect
    public void onConnect(SocketIOClient client) {
        HandshakeData handshakeData = client.getHandshakeData();
        String userId = handshakeData.getSingleUrlParam("userId");
        log.info("{} 建立连接.", userId);
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        HandshakeData handshakeData = client.getHandshakeData();
        String userId = handshakeData.getSingleUrlParam("userId");
        log.info("{} 断开连接", userId);
    }

    @OnMessage
    public void onMessage(SocketIOClient client, AckRequest ackRequest, Object o) {

    }
}
