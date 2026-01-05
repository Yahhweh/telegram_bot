package placeholder.organisation.telegrambot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_logs")
@Getter
@Setter
@NoArgsConstructor
public class PostLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_logs_id")
    private Long postLogsId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "post_date", nullable = false, updatable = false)
//    @CreationTimestamp
    private LocalDate postDate;


    public PostLog(Long userId) {
        this.userId = userId;
    }
}