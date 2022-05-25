package geektime.chapter04;

/** 在第一个示例程序里，我们用了两把不同的锁来分别保护账户余额、账户密码，创建锁的时候，
 * 我们用的是：private final Object xxxLock = new Object();，
 * 如果账户余额用 this.balance 作为互斥锁，账户密码用 this.password 作为互斥锁，你觉得是否可以呢？
 *   balance  password 都是可变的对象 Integer -128 ~ 127 都是使用缓存的
 * @author 996kid@gmail.com
 * @Description Think04
 * @Date 2022/4/25 22:38
 */
public class Think04 {

    class Account {
        // 锁：保护账户余额
        private final Object balLock
                = new Object();
        // 账户余额
        private Integer balance;
        // 锁：保护账户密码
        private final Object pwLock
                = new Object();
        // 账户密码
        private String password;

        // 取款
        void withdraw(Integer amt) {
            synchronized(balLock) {
                if (this.balance > amt){
                    this.balance -= amt;
                }
            }
        }
        // 查看余额
        Integer getBalance() {
            synchronized(balLock) {
                return balance;
            }
        }

        // 更改密码
        void updatePassword(String pw){
            synchronized(pwLock) {
                this.password = pw;
            }
        }
        // 查看密码
        String getPassword() {
            synchronized(pwLock) {
                return password;
            }
        }
    }
}
