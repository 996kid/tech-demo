package com.eastwood.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.*;

public class GenerateSql {

	private static ExecutorService executorService = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

	public static void main(String[] args) throws Exception {
		test();
	}

	private static void test() throws IOException, InterruptedException {
		Config config = Config.fromYAML(new File("D:\\eastwood\\iyunwen\\redisson-demo\\src\\main\\resources\\redisson.yml"));
		RedissonClient client = Redisson.create(config);
		RBlockingQueue<String> blockingQueue = client.getBlockingQueue("test");
		for (int i = 0; i < 10; i++) {
			blockingQueue.add("item-" + i);
		}
		executorService.submit(() -> {
			Iterator<String> iterator = blockingQueue.iterator();
			while (iterator.hasNext()) {
				iterator.next();
				iterator.remove();
			}
		});
//		CountDownLatch countDownLatch = new CountDownLatch(1);
//		new Thread(() -> {
//			Iterator<String> iterator = blockingQueue.iterator();
//			while (iterator.hasNext()) {
//				iterator.next();
//				iterator.remove();
//			}
//			countDownLatch.countDown();
//		}).start();
//		countDownLatch.await();
		Thread.sleep(3000);
		Iterator<String> iterator = blockingQueue.iterator();
		System.out.println("remove done.");
		while (iterator.hasNext()) {
			System.out.println("item: " + iterator.next());
		}
		client.shutdown();
	}

}
