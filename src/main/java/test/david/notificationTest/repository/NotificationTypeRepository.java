package test.david.notificationTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.david.notificationTest.entity.NotificationType;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
}
