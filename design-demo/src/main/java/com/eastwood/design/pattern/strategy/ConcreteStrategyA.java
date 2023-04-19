package com.eastwood.design.pattern.strategy;

public class ConcreteStrategyA implements Strategy {
  @Override
  public void  algorithmInterface() {
    //具体的算法...
    System.out.println("ConcreteStrategyA");
  }
}