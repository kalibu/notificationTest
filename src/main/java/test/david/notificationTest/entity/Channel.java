package test.david.notificationTest.entity;

import jakarta.persistence.*;
import lombok.Data;
import test.david.notificationTest.enums.NotificationTypeEnum;

import java.io.Serializable;

@Entity(name = "CHANNELS")
@Data
public class Channel implements Serializable {

    @Id
    @SequenceGenerator(name = "channelsSeq", sequenceName = "CHANNELS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "channelsSeq")
    @Column(name = "channel_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "notification_type")
    @Enumerated(EnumType.STRING)
    private NotificationTypeEnum notificationType;

}
