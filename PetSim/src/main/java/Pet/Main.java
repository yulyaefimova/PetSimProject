package Pet;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static final Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {
        PetSimBot bot = new PetSimBot(getenv.get("BOT_NAME"), getenv.get("BOT_TOKEN"));

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
