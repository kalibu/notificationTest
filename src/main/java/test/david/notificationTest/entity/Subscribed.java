package test.david.notificationTest.entity;

import jakarta.persistence.*;
import lombok.Data;
import test.david.notificationTest.enums.CategoryEnum;

import java.io.Serializable;

@Entity(name = "SUBSCRIBED")
@Data
public class Subscribed implements Serializable {

    @Id
    @SequenceGenerator(name = "subscribedSeq", sequenceName = "SUBSCRIBED_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "subscribedSeq")
    @Column(name = "subscribed_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

}
