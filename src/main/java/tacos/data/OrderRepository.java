package tacos.data;

import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;
import tacos.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Integer> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

//    TacoOrder save(TacoOrder order);
}
