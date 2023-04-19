package com.eastwood.design.pattern.strategy;

public class ConcreteStrategyB implements Strategy {
  @Override
  public void  algorithmInterface() {
    //具体的算法...
    System.out.println("ConcreteStrategyB");
  }
}