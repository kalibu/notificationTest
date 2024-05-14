package test.david.notificationTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.david.notificationTest.entity.Subscribed;
import test.david.notificationTest.enums.CategoryEnum;

import java.util.List;

@Repository
public interface SubscribedRepository extends JpaRepository<Subscribed, Long> {

    List<Subscribed> findAllByCategory(final CategoryEnum category);

}
