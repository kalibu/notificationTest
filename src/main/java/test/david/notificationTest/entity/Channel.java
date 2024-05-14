package test.david.notificationTest.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "notification_type_id")
    private NotificationType notificationType;

}
