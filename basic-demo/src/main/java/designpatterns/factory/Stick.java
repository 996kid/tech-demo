package designpatterns.factory;

/**
 * @author 996kid@gmail.com
 * @Description Stick
 * @Date 2022/6/6 21:58
 */
public class Stick implements Weapon {
    @Override
    public void attack() {
        System.out.println("a stick attacks...");
    }
}
