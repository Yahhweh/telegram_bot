package placeholder.organisation.telegrambot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserTelegram {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @Column(name = "telegram_id")
    Long telegramId;

    @Column(name = "username")
    String username;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    public UserTelegram(Long telegramId, String username, LocalDateTime createdAt) {
        this.telegramId = telegramId;
        this.username = username;
        this.createdAt = createdAt;
    }



}