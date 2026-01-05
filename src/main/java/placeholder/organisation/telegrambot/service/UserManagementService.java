package placeholder.organisation.telegrambot.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import placeholder.organisation.telegrambot.dao.UserDao;
import placeholder.organisation.telegrambot.entity.UserTelegram;

import java.time.LocalDateTime;

@Service

public class UserManagementService {

    UserDao userDao;

    public UserManagementService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public UserTelegram getOrCreate(Long telegramId, String username) {
        return userDao.findByTelegramId(telegramId)
                .orElseGet(() -> createUser(telegramId, username));
    }

    private UserTelegram createUser(Long telegramId, String username) {
        UserTelegram newUser = new UserTelegram();
        newUser.setTelegramId(telegramId);
        newUser.setUsername(username);
        newUser.setCreatedAt(LocalDateTime.now());
        return userDao.save(newUser);
    }
}