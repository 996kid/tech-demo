package geektime.chapter04;

/**
 *
 */
public class Account1 {

  private int balance;
  // 转账
  void transfer(Account1 target, int amt){
    synchronized(Account1.class) {
      if (this.balance > amt) {
        this.balance -= amt;
        target.balance += amt;
      }
    }
  } 
}