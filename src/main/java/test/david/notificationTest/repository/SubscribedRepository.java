package test.david.notificationTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.david.notificationTest.entity.Subscribed;

@Repository
public interface SubscribedRepository extends JpaRepository<Subscribed, Long> {
}
