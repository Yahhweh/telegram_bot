package placeholder.organisation.telegrambot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import placeholder.organisation.telegrambot.entity.PostLog;

import java.time.LocalDate;
import java.util.List;

public interface PostLogDao extends JpaRepository<PostLog, Long> {

    List<PostLog> findAllByUserId(Long userId);
    boolean existsByUserIdAndPostDate(Long userId, LocalDate postDate);

    @Modifying
    @Query(value = "INSERT INTO post_logs (user_id, post_date) VALUES (:userId, :postDate) ON CONFLICT ON CONSTRAINT unique_day_post DO NOTHING", nativeQuery = true)
    void safeSaveLog(@Param("userId") Long userId, @Param("postDate") LocalDate postDate);
}
