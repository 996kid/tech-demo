package designpatterns.factory;

/**
 * @author 996kid@gmail.com
 * @Description Knife
 * @Date 2022/6/6 21:58
 */
public class Knife implements Weapon {
    @Override
    public void attack() {
        System.out.println("a knife attacks...");
    }
}
