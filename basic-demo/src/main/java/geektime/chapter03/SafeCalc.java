package geektime.chapter03;

class SafeCalc {
  long value = 0L;
  long get() {
    return value;
  }

  synchronized long get1() {
    return value;
  }

  synchronized void addOne() {
    value += 1;
  }
}