package placeholder.organisation.telegrambot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_progress")
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "user_id")
    Long userId;
    @Column(name = "total_active_days")
    Long activeDays;
    @Column(name = "total_penalties")
    Long penalties;
    @Column(name = "last_post_date")
    LocalDate lasPostDate;
    @Column(name= "first_post_date")
    LocalDate firstPostDate;

    public UserProgress(Long userId, Long activeDays, Long penalties, LocalDate lasPostDate, LocalDate firstPostDate) {
        this.userId = userId;
        this.activeDays = activeDays;
        this.penalties = penalties;
        this.lasPostDate = lasPostDate;
        this.firstPostDate = firstPostDate;
    }
}
