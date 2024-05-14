package test.david.notificationTest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import test.david.notificationTest.enums.CategoryEnum;
import test.david.notificationTest.enums.NotificationTypeEnum;

import java.io.Serializable;
import java.util.Calendar;

@Entity(name = "NOTIFICATIONS")
@Data
public class Notification implements Serializable {

    @Id
    @SequenceGenerator(name = "notificationsSeq", sequenceName = "NOTIFICATIONS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "notificationsSeq")
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(name = "notification_type")
    @Enumerated(EnumType.STRING)
    private NotificationTypeEnum notificationType;

    @Column(name = "notification_date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar notificationDate;

    @NotNull
    @Size(max = 4000)
    private String message;

}
