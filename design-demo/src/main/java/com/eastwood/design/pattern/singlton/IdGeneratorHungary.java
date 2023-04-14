package com.eastwood.design.pattern.singlton;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorHungary {
  private AtomicLong id = new AtomicLong(0);
  private static final IdGeneratorHungary instance = new IdGeneratorHungary();
  private IdGeneratorHungary() {}
  public static IdGeneratorHungary getInstance() {
    return instance;
  }
  public long getId() { 
    return id.incrementAndGet();
  }
}