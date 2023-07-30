package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@ConfigurationProperties(prefix = "taco.order")
public class OrderController {
    private OrderProps props;
    private OrderRepository orderRepository;
    private TacoRepository tacoRepository;
    private IngredientRefRepository ingredientRefRepository;

    public OrderController (OrderRepository orderRepository, TacoRepository tacoRepository,
                            IngredientRefRepository ingredientRefRepository, OrderProps props) {
        this.orderRepository = orderRepository;
        this.tacoRepository = tacoRepository;
        this.ingredientRefRepository = ingredientRefRepository;
        this.props = props;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()){
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);

        saveTacosAndIngredients(order);

        sessionStatus.setComplete();

        return "redirect:/";
    }

    private void saveTacosAndIngredients(TacoOrder order) {
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            taco.setTacoOrder(order);
            tacoRepository.save(taco);
            saveIngredientsFromTaco(taco);
        }
    }

    private void saveIngredientsFromTaco(Taco taco) {
        for (Ingredient ingredient : taco.getIngredients()) {
            IngredientRefId tacoIngredientId = new IngredientRefId();
            tacoIngredientId.setIngredientId(ingredient.getId());
            tacoIngredientId.setTacoId(taco.getId());

            IngredientRef ingredientRef = new IngredientRef();
            ingredientRef.setId(tacoIngredientId);

            ingredientRef.setIngredient(ingredient);
            ingredientRef.setTaco(taco);

            ingredientRefRepository.save(ingredientRef);
        }
    }
}
