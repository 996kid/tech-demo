package transaction;

/**
 * @author 996kid@gmail.com
 * @Description OrderDao
 * @Date 2021/8/5 15:58
 */
public interface OrderDao {
    void createOrderInDB(CreateOrderService.CreateOrderRequest request);

    boolean isOrderIdExistsInDB(String orderId);
}
