package placeholder.organisation.telegrambot.dao;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import placeholder.organisation.telegrambot.entity.UserProgress;

public interface UserProgressDao extends JpaRepository<UserProgress, Long> {

    UserProgress findByUserId(@NonNull Long id);
}
