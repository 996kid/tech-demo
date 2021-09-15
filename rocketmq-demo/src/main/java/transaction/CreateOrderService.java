package transaction;

import com.google.inject.Inject;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ExecutorService;

/**
 * @author 996kid@gmail.com
 * @Description CreateOrderService
 * @Date 2021/8/5 15:44
 */
public class CreateOrderService {

    @Inject
    private OrderDao orderDao; // 注入订单表的DAO
    @Inject
    private ExecutorService executorService; //注入一个ExecutorService

    private TransactionMQProducer producer;

    // 初始化transactionListener 和 producer
    public void init() throws MQClientException {
        TransactionListener transactionListener = createTransactionListener();
        producer = new TransactionMQProducer("myGroup");
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();
    }

    // 创建订单服务的请求入口
    public boolean createOrder(CreateOrderRequest request) throws MQClientException {
        // 根据创建订单请求创建一条消息
        Message msg = createMessage(request);
        // 发送事务消息 request 执行本地事务所需的参数
        SendResult sendResult = producer.sendMessageInTransaction(msg, request);
        // 返回：事务是否成功
        return sendResult.getSendStatus() == SendStatus.SEND_OK;
    }

    private Message createMessage(CreateOrderRequest request) {
        return null;
    }

    private TransactionListener createTransactionListener() {
        return new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                CreateOrderRequest request = (CreateOrderRequest) arg;
                try {
                    // 执行本地事务创建订单
                    orderDao.createOrderInDB(request);
                    // 如果没抛异常说明执行成功，提交事务消息
                    return LocalTransactionState.COMMIT_MESSAGE;
                } catch (Throwable t) {
                    // 失败则直接回滚事务消息
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
            }

            // 反查本地事务
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                // 从消息中获得订单ID
                String orderId = msg.getUserProperty("orderId");

                // 去数据库中查询订单号是否存在，如果存在则提交事务；
                // 如果不存在，可能是本地事务失败了，也可能是本地事务还在执行，所以返回UNKNOW
                //（PS：这里RocketMQ有个拼写错误：UNKNOW）
                return orderDao.isOrderIdExistsInDB(orderId) ?
                        LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.UNKNOW;
            }
        };
    }

    class CreateOrderRequest {

    }
}
