package placeholder.organisation.telegrambot.service.parser;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.photo.PhotoSize;

import java.util.List;

@Service
public class PhotoParser implements Parser<SendPhoto> {
    @Override
    public SendPhoto parse(Update update, long group_id) {
            try {
                List<PhotoSize> photos = update.getMessage().getPhoto();
                String fileId = photos.get(photos.size() - 1).getFileId();
                System.out.println("smth printed");

                SendPhoto photo = SendPhoto.builder()
                        .chatId(group_id)
                        .photo(new InputFile(fileId))
                        .caption("@" + getUser(update).getUserName())
                        .build();

                return photo;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    private static User getUser(Update update){
        return update.getMessage().getFrom();
    }
    }