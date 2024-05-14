package test.david.notificationTest.entity;

import jakarta.persistence.*;
import lombok.Data;
import test.david.notificationTest.enums.NotificationTypeEnum;

import java.io.Serializable;

@Entity(name = "CHANNELS")
@Data
public class Channel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "channel_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "notification_type")
    @Enumerated(EnumType.STRING)
    private NotificationTypeEnum notificationType;

}
