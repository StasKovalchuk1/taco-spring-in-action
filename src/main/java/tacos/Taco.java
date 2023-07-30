package tacos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @Transient
    private List<Ingredient> ingredients;


    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }


    /**
     * use @JsonIgnore to avoid json recursion
     */
    @ManyToOne
    @JoinColumn(name = "taco_order", referencedColumnName = "id")
    @JsonIgnore
    private TacoOrder tacoOrder;
}
