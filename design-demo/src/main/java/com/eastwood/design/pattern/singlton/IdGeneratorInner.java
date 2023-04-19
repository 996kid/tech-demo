package com.eastwood.design.pattern.singlton;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorInner {
  static {
    System.out.println("world");
  }

  private AtomicLong id = new AtomicLong(0);
  private IdGeneratorInner() {}

  /**
   * 静态内部类不会在外部类加载时加载
   */
  private static class SingletonHolder {
    static {
      System.out.println("SingletonHolder loaded...");
    }
    private static final IdGeneratorInner instance = new IdGeneratorInner();
  }
  
  public static IdGeneratorInner getInstance() {
    return SingletonHolder.instance;
  }
 
  public long getId() { 
    return id.incrementAndGet();
  }

  public static void main(String[] args) {
    System.out.println("hello");
    IdGeneratorInner.getInstance();
  }
}