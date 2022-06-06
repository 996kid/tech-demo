package designpatterns.factory;

/**
 * @author 996kid@gmail.com
 * @Description WeaponFactory
 * @Date 2022/6/6 21:59
 */
public class WeaponFactory {

    public static Weapon getWeapon(WeaponType weaponType) {
        switch (weaponType) {
            case GUN:
                return new Gun();
            case STICK:
                return new Stick();
            case KNIFE:
                return new Knife();
        }
        return null;
    }
}
