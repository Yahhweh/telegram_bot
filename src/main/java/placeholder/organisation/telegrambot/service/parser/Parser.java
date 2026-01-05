package placeholder.organisation.telegrambot.service.parser;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Parser<T> {

    T parse(Update update, long group_id);
}
