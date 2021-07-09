package netty.socketio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 996kid@gmail.com
 * @Description SocketIoConfig
 * @Date 2021/1/29 16:45
 */
@Configuration
@ConfigurationProperties(prefix = "socketio.server")
@Data
public class SocketIoProperties {

    private String host;

    private int port;

    private String contextPath;

    private int workerThreads;

    private int bossThreads;

    private String transport;

    private int pingTimeout;

    private int pingInterval;
}
