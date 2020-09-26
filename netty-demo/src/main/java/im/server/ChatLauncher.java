package im.server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

public class ChatLauncher {

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("chatevent", ChatObject.class, (client, data, ackRequest) -> {
            // broadcast messages to all clients
            server.getBroadcastOperations().sendEvent("chatevent", data);
//            server.getRoomOperations("myroom").sendEvent("chatevent", data);
        });

        server.addConnectListener(client -> {
            System.out.println("namespace: " + client.getNamespace().getName());
            System.out.println("sessionId: " + client.getSessionId());
            System.out.println("remoteAddress: " + client.getRemoteAddress());
            System.out.println("handShakeData: " +
                    client.getHandshakeData().getUrl() + ", " + client.getHandshakeData().getUrlParams());
        });

        server.addDisconnectListener(client -> {
            System.out.println("disconnected: " + client.getSessionId());
        });

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }

}
