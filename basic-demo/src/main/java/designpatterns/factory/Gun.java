package designpatterns.factory;

/**
 * @author 996kid@gmail.com
 * @Description Gun
 * @Date 2022/6/6 21:56
 */
public class Gun implements Weapon {
    @Override
    public void attack() {
        System.out.println("a gun attacks...");
    }
}
