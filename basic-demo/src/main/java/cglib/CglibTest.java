package cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class CglibTest {

    public static void main(String[] args) {
        //拦截某个类所有的方法
        DaoProxy daoProxy = new DaoProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(daoProxy);

        Dao dao = (Dao)enhancer.create();
        dao.update();
        dao.select();


//        DaoProxy daoProxy = new DaoProxy();
//        DaoProxy daoAnotherProxy = new DaoProxy();
//
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(Dao.class);
//        enhancer.setCallbacks(new Callback[]{daoProxy, daoAnotherProxy, NoOp.INSTANCE});
//        enhancer.setCallbackFilter(new DaoFilter());
//
//        Dao dao = (Dao)enhancer.create();
//        dao.update();
//        dao.select();
    }
    
}