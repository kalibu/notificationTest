package test.david.notificationTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.david.notificationTest.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
