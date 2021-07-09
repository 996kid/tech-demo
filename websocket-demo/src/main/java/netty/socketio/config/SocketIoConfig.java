package netty.socketio.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.protocol.JacksonJsonSupport;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author 996kid@gmail.com
 * @Description SocketIoConfig
 * @Date 2021/1/29 17:11
 */
@Configuration
public class SocketIoConfig {

    @Autowired
    private SocketIoProperties socketIoProperties;

    private SocketIOServer socketIOServer;

    @Bean
    public SocketIOServer socketIOServer(RedissonClient redissonClient) {
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setHostname(socketIoProperties.getHost());
        configuration.setTransports(Transport.WEBSOCKET);
        configuration.setContext(socketIoProperties.getContextPath());
        configuration.setWorkerThreads(socketIoProperties.getWorkerThreads());
        configuration.setPingTimeout(socketIoProperties.getPingTimeout());
        configuration.setPingInterval(socketIoProperties.getPingInterval());
        configuration.setUseLinuxNativeEpoll(false);
        configuration.setJsonSupport(new JsonSupport());
        configuration.getSocketConfig().setReuseAddress(true);
        configuration.getSocketConfig().setSoLinger(0);
        configuration.getSocketConfig().setTcpKeepAlive(true);
        configuration.getSocketConfig().setTcpNoDelay(true);
        RedissonStoreFactory factory = new RedissonStoreFactory();
        configuration.setStoreFactory(factory);
        socketIOServer = new SocketIOServer(configuration);
        return socketIOServer;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        socketIOServer.stop();
    }

    class JsonSupport extends JacksonJsonSupport {
        @Override
        protected void init(ObjectMapper objectMapper) {
            super.init(objectMapper);
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        }
    }

}
