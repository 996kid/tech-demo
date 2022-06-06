package designpatterns.factory;

/**
 * @author 996kid@gmail.com
 * @Description App
 * @Date 2022/6/6 22:01
 */
public class App {

    public static void main(String[] args) {
        WeaponFactory.getWeapon(WeaponType.GUN).attack();
        WeaponFactory.getWeapon(WeaponType.STICK).attack();
        WeaponFactory.getWeapon(WeaponType.KNIFE).attack();
    }
}
