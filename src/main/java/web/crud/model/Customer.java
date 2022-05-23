package web.crud.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30, message = "* Enter from 2 to 30 characters")
    @NotEmpty(message = "* Fill in the field")
    @NotNull
    private String name;

    @Email(message = "* Check that the input is correct")
    @NotEmpty(message = "* Fill in the field")
    @NotNull
    private String email;

}
