package tacos.data;

import org.springframework.core.annotation.Order;
import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Integer> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

//    TacoOrder save(TacoOrder order);
}
