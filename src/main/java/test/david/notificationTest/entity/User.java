package test.david.notificationTest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "USERS")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 14)
    @Column(name = "phone_number")
    private String phoneNumber;

}
