package test.david.notificationTest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "CATEGORIES")
@Data
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

}
