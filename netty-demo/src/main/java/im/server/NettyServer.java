package im.server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;

/**
 * @author yyh
 * @Description NettyServer
 * @Date 2020/9/24 18:45
 */
public class NettyServer {

    public static void main(String[] args) {
        SocketConfig socketConfig = new SocketConfig();
        Configuration configuration = new Configuration();
        configuration.setSocketConfig(socketConfig);
        SocketIOServer socketIOServer = new SocketIOServer(configuration);
    }
}
