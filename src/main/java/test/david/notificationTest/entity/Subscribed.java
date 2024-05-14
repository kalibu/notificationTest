package test.david.notificationTest.entity;

import jakarta.persistence.*;
import lombok.Data;
import test.david.notificationTest.enums.CategoryEnum;

import java.io.Serializable;

@Entity(name = "SUBSCRIBED")
@Data
public class Subscribed implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "subscribed_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

}
