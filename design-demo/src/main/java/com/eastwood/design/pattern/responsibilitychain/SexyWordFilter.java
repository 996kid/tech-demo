package com.eastwood.design.pattern.responsibilitychain;

public class SexyWordFilter implements SensitiveWordFilter {

  @Override
  public boolean doFilter(Content content) {
    boolean legal = true;
    //...
    return legal;
  }

}