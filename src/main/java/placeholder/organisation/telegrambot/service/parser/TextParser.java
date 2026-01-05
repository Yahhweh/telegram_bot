package placeholder.organisation.telegrambot.service.parser;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class TextParser implements Parser<SendMessage> {
    @Override
    public SendMessage parse(Update update, long chatId) {

        return SendMessage.builder()
                .chatId(chatId)
                .text("poshel nahui")
                .replyToMessageId(update.getMessage().getMessageId())
                .build();
//            telegramClient.execute(message);
    }

}
