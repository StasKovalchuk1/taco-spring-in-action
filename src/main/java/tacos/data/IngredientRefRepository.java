package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.IngredientRef;
import tacos.IngredientRefId;

public interface IngredientRefRepository extends CrudRepository<IngredientRef, IngredientRefId> {
}
