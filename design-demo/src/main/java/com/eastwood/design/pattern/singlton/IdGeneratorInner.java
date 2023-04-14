package com.eastwood.design.pattern.singlton;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorInner {
  private AtomicLong id = new AtomicLong(0);
  private IdGeneratorInner() {}

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
}