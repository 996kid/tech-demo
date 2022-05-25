package redissonbug;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.RedissonNode;
import org.redisson.api.RFuture;
import org.redisson.api.RMap;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.CompositeCodec;
import org.redisson.config.Config;
import org.redisson.config.RedissonNodeConfig;
import org.redisson.executor.RemoteExecutorService;
import org.redisson.misc.RPromise;
import org.redisson.misc.RedissonPromise;
import org.redisson.remote.RemoteServiceCancelRequest;
import org.redisson.remote.RemoteServiceCancelResponse;
import org.redisson.remote.RequestId;

import java.io.Serializable;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 *  redisson BUG： cancelTask 阻塞线程
 */
public class SchedulerServiceExamples {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient redisson = Redisson.create(config);

        RedissonNodeConfig nodeConfig = new RedissonNodeConfig(config);
        nodeConfig.setExecutorServiceWorkers(Collections.singletonMap("myExecutor", 5));
        RedissonNode node = RedissonNode.create(nodeConfig);
        System.out.println("server started...");
        node.start();

        RScheduledExecutorService e = redisson.getExecutorService("myExecutor");
        String taskId = redisson.getExecutorService("myExecutor")
                .schedule(new RunnableTask(), 2000, TimeUnit.MILLISECONDS)
                .getTaskId();
        Thread.sleep(5500);

        System.out.println("xxxx start cancel task");
        e.cancelTask(taskId);
        System.out.println("yyyy end cancel task");

        e.shutdown();
        redisson.shutdown();
        node.shutdown();

//        Promise promise = new DefaultPromise();
//        CompletableFuture completableFuture = new CompletableFuture();

    }

    @Slf4j
    public static class RunnableTask implements Runnable, Serializable {

        @Override
        public void run() {
            System.out.println("Task start...");
            try {
                Thread.sleep(1000);
                System.out.println("Task running 1 ...");
                Thread.sleep(1000);
                System.out.println("Task running 2 ...");
                Thread.sleep(1000);
                System.out.println("Task running 3 ...");
                Thread.sleep(1000);
                System.out.println("Task running 4 ...");
            } catch (InterruptedException interruptedException) {
                System.out.println("Task interruptedException");
            }
        }

    }



    /****源码***/
    /*public RFuture<Boolean> cancelExecutionAsync(final RequestId requestId) {
        final RPromise<Boolean> result = new RedissonPromise<Boolean>();

        String requestQueueName = getRequestQueueName(RemoteExecutorService.class);
        RFuture<Boolean> removeFuture = removeAsync(requestQueueName, requestId);
        removeFuture.addListener(new FutureListener<Boolean>() {
            @Override
            public void operationComplete(Future<Boolean> future) throws Exception {
                if (!future.isSuccess()) {
                // isSuccess
                    result.tryFailure(future.cause());
                    return;
                }

                if (future.getNow()) {
                    result.trySuccess(true);
                } else {
                    RMap<String, RemoteServiceCancelRequest> canceledRequests = redisson.getMap(cancelRequestMapName, new CompositeCodec(StringCodec.INSTANCE, codec, codec));
                    canceledRequests.putAsync(requestId.toString(), new RemoteServiceCancelRequest(true, true));
                    canceledRequests.expireAsync(60, TimeUnit.SECONDS);

                    final RPromise<RemoteServiceCancelResponse> response = new RedissonPromise<RemoteServiceCancelResponse>();
                    scheduleCheck(cancelResponseMapName, requestId, response);
                    response.addListener(new FutureListener<RemoteServiceCancelResponse>() {
                        @Override
                        public void operationComplete(Future<RemoteServiceCancelResponse> future) throws Exception {
                            if (!future.isSuccess()) {
                                result.tryFailure(future.cause());
                                return;
                            }

                            if (response.getNow() == null) {
                                result.trySuccess(false);
                                return;
                            }
                            result.trySuccess(response.getNow().isCanceled());
                        }
                    });
                }
            }
        });

        return result;
    }


    protected <T> void scheduleCheck(final String mapName, final RequestId requestId, final RPromise<T> cancelRequest) {
        commandExecutor.getConnectionManager().newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                if (cancelRequest.isDone()) {
                    return;
                }

                RMap<String, T> canceledRequests = redisson.getMap(mapName, new CompositeCodec(StringCodec.INSTANCE, codec, codec));
                RFuture<T> future = canceledRequests.removeAsync(requestId.toString());
                future.addListener(new FutureListener<T>() {
                    @Override
                    public void operationComplete(Future<T> future) throws Exception {
                        if (cancelRequest.isDone()) {
                            return;
                        }
                        if (!future.isSuccess()) {
                            scheduleCheck(mapName, requestId, cancelRequest);
                            return;
                        }

                        T request = future.getNow();
                        if (request == null) {
                            scheduleCheck(mapName, requestId, cancelRequest);
                        } else {
                            cancelRequest.trySuccess(request);
                        }
                    }
                });
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }*/


}