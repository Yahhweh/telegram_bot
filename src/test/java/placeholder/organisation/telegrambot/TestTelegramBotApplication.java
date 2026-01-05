package placeholder.organisation.telegrambot;

import org.springframework.boot.SpringApplication;

public class TestTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.from(TelegramBotApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
