package com.eastwood.design.pattern.singlton;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorDoubleCheck {
  private AtomicLong id = new AtomicLong(0);
  private static IdGeneratorDoubleCheck instance;
  private IdGeneratorDoubleCheck() {}
  public static IdGeneratorDoubleCheck getInstance() {
    if (instance == null) {
      synchronized(IdGeneratorDoubleCheck.class) { // 此处为类级别的锁
        if (instance == null) {
          instance = new IdGeneratorDoubleCheck();
        }
      }
    }
    return instance;
  }
  public long getId() { 
    return id.incrementAndGet();
  }
}