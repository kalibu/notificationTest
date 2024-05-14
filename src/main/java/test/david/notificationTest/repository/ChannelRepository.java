package test.david.notificationTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.david.notificationTest.entity.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
