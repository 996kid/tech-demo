package jdk;

import java.io.FileNotFoundException;
import java.io.IOException;

class Parent {
    void method() throws IOException {
        // 父类方法可能会抛出IOException
    }
}

class Child extends Parent {
    @Override
    void method() throws FileNotFoundException {
        // 子类方法抛出了父类方法声明的IOException的子类FileNotFoundException
    }
}
