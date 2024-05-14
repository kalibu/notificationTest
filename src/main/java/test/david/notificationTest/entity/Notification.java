package test.david.notificationTest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;

@Entity(name = "NOTIFICATIONS")
@Data
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "notification_type_id")
    private NotificationType notificationType;

    @Column(name = "notification_date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar notificationDate;

}
