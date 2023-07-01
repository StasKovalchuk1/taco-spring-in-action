package tacos;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }


//    public String getId() {
//        return id;
//    }
}

/*
This class uses Lombok library which allows to not add getter/setters/constructors
lombok will automatically generate those methods at compile time
 */

