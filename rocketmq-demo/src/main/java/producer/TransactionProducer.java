package producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

public class TransactionProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionListener transactionListener = new TransactionListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("please_rename_unique_group_name");
        ExecutorService executorService = new ThreadPoolExecutor(
                2, 5,
                100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("client-transaction-msg-check-thread");
                    return thread;
                });

        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();

        //官方例子
//        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
//        for (int i = 0; i < 10; i++) {
//            try {
//                Message msg =
//                    new Message("TopicTest1234", tags[i % tags.length], "KEY" + i,
//                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
//                System.out.printf("%s%n", sendResult);
//
//                Thread.sleep(10);
//            } catch (MQClientException | UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
        // 极客时间 消息队列高手课 生成订单 清空购物车事务实例
        // 发送清空购物车消息 异步清空购物车
        Message message = new Message("shoppingTopic", "clear shopping cart".getBytes());
        SendResult sendResult = producer.sendMessageInTransaction(message, null);
        System.out.println(sendResult);
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();
    }
}