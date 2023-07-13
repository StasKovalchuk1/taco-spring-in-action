package tacos;

import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class IngredientRefId implements Serializable {
    private int tacoId;
    private String ingredientId;

    public int getTacoId() {
        return tacoId;
    }

    public void setTacoId(int tacoId) {
        this.tacoId = tacoId;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }
}
