package placeholder.organisation.telegrambot.service;

import org.springframework.stereotype.Service;
import placeholder.organisation.telegrambot.dao.UserProgressDao;
import placeholder.organisation.telegrambot.entity.UserProgress;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ProgressService {

    UserProgressDao userProgressDao;

    public ProgressService(UserProgressDao userProgressDao) {
        this.userProgressDao = userProgressDao;
    }

    void updateProgress(Long userId, LocalDate activityDate) {

        UserProgress userProgress = userProgressDao.findByUserId(userId);
        if (userProgress == null) {
            userProgress = new UserProgress(userId, 1L, 0L, activityDate, activityDate);
        } else {
            userProgress.setActiveDays(userProgress.getActiveDays() + 1);
            userProgress.setLasPostDate(activityDate);

            long daysBetween = ChronoUnit.DAYS.between(
                    userProgress.getFirstPostDate(),
                    userProgress.getLasPostDate());

            userProgress.setPenalties(daysBetween - userProgress.getActiveDays());
        }

        userProgressDao.saveAndFlush(userProgress);
        System.out.println("SUCCESS: DB Updated. Active Days: " + userProgress.getActiveDays());
    }


}
