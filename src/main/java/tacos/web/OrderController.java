package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.*;
import tacos.data.IngredientRefRepository;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private TacoRepository tacoRepository;
    private IngredientRefRepository ingredientRefRepository;

    public OrderController (OrderRepository orderRepository, TacoRepository tacoRepository, IngredientRefRepository ingredientRefRepository) {
        this.orderRepository = orderRepository;
        this.tacoRepository = tacoRepository;
        this.ingredientRefRepository = ingredientRefRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()){
            return "orderForm";
        }

        orderRepository.save(order);
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            taco.setTacoOrder(order);
            tacoRepository.save(taco);
            for (Ingredient ingredient : taco.getIngredients()) {
                IngredientRefId tacoIngredientId = new IngredientRefId();
                tacoIngredientId.setIngredientId(ingredient.getId());
                tacoIngredientId.setTacoId(taco.getId());

                IngredientRef ingredientRef = new IngredientRef();
                ingredientRef.setId(tacoIngredientId);

                // WTF??? зачем это
                ingredientRef.setIngredient(ingredient);
                ingredientRef.setTaco(taco);

                ingredientRefRepository.save(ingredientRef);
            }
        }
        sessionStatus.setComplete();

        return "redirect:/";
    }
}