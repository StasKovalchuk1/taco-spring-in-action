package tacos.web.api;

import org.hibernate.criterion.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.OrderRepository;

import java.util.EmptyStackException;

@RestController
@RequestMapping(path="/orders", produces="application/json")
@CrossOrigin(origins="*")
public class OrderApiController {

    private OrderRepository repo;

    public OrderApiController(OrderRepository repo) {
        this.repo = repo;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        return repo.save(order);
    }

    @PutMapping(path="/{orderId}", consumes="application/json")
    public TacoOrder putOrder(
            @PathVariable("orderId") int orderId,
            @RequestBody TacoOrder order) {
        order.setId(orderId);
        return repo.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") int orderId,
                                @RequestBody TacoOrder patch) {
        TacoOrder order = repo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName()); }
        if (patch.getDeliveryStreet() != null) { order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity()); }
        if (patch.getDeliveryState() != null) { order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration()); }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return repo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") int id) {
        try{
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }
}
