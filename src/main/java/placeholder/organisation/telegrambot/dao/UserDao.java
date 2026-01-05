package placeholder.organisation.telegrambot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import placeholder.organisation.telegrambot.entity.UserTelegram;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserTelegram, Long> {

    boolean existsByTelegramId(Long telegramId);
    Optional<UserTelegram> findByTelegramId(Long telegramId);
}
