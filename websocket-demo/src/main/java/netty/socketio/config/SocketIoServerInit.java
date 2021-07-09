package netty.socketio.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 996kid@gmail.com
 * @Description SocketIoServerInit
 * @Date 2021/1/29 16:44
 */
@Component
public class SocketIoServerInit implements CommandLineRunner {

    @Autowired
    private SocketIOServer socketIOServer;

    @Override
    public void run(String... args) throws Exception {
        socketIOServer.start();
    }
}
