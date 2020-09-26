package im.server;

import com.corundumstudio.socketio.AckCallback;
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
            System.out.println(data.getMessage());
            client.sendEvent("got it", new AckCallback<String>(String.class) {
                @Override
                public void onSuccess(String o) {
                    System.out.println("ack from client: " + client.getSessionId());
                }
            });
//            server.getRoomOperations("myroom").sendEvent("chatevent", data);
        });

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }

}
