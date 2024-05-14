package test.david.notificationTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.david.notificationTest.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
