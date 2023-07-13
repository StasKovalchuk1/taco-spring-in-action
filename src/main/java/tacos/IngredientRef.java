package tacos;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class IngredientRef {

    @EmbeddedId
    private IngredientRefId id;

    @ManyToOne
    @MapsId("tacoId")
    @JoinColumn(name = "taco", referencedColumnName = "id")
    private Taco taco;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient", referencedColumnName = "id")
    private Ingredient ingredient;
//    private final String ingredient;
}
