package placeholder.organisation.telegrambot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import placeholder.organisation.telegrambot.dao.PostLogDao;
import placeholder.organisation.telegrambot.entity.PostLog;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

    private final UserManagementService userService;
    private final ProgressService progressService;
    private final PostLogDao postLogDao;


    @Transactional
    public void registerActivity(User tUser) {
        Long userId = tUser.getId();
        LocalDate targetDay = LocalDate.now();

        try {

            userService.getOrCreate(userId, tUser.getUserName());

            if (postLogDao.existsByUserIdAndPostDate(userId, targetDay)) {
                log.info("Activity already logged for user {} on {}", userId, targetDay);
                return;
            }

            savePostLog(userId, targetDay);

            progressService.updateProgress(userId, targetDay);

            log.info("SUCCESS: User {} activity saved.", userId);

        } catch (DataIntegrityViolationException e) {
            log.warn("Concurrent modification for user {}", userId);
        }
    }

    private void savePostLog(Long userId, LocalDate date) {
        PostLog log = new PostLog(userId);
        log.setPostDate(date);
        postLogDao.save(log);
    }
}