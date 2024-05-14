package test.david.notificationTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.david.notificationTest.entity.Channel;
import test.david.notificationTest.entity.Subscribed;
import test.david.notificationTest.enums.CategoryEnum;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    List<Channel> findAllByUserId(final Long id);

}
