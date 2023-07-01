package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import tacos.Ingredient;

import java.util.Optional;

/*
The first parameter is the type of the object to be persisted by this repository
in this case, Ingredient. The second parameter is the type of the persisted object’s ID field.
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
//    Iterable<Ingredient> findAll();
//
//    Optional<Ingredient> findById(String id);
//
//    Ingredient save(Ingredient ingredient);
}
