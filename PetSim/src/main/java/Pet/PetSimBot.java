package Pet;


import Constants.CreationConstants;
import Constants.GameConstants;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.*;


/**
 * класс телеграм бота
 */
public class PetSimBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    /**
     * HashMap пользователей, String - userId
     */
    private HashMap<String, User> users = new HashMap<>();

    private final PetSim petSim = new PetSim();

    public PetSimBot(String name, String token) {
        BOT_NAME = name;
        BOT_TOKEN = token;
        runTimer();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String userId = message.getFrom().getId().toString();
        deserialize();
        if (!users.containsKey(userId))
            users.put(userId, new User());
        String sticker = PetSim.getSticker(users.get(userId));
        String newMessage = EmojiParser.removeAllEmojis(message.getText());
        if (newMessage.charAt(0) != "/".charAt(0) && petSim.commands.containsKey("/" + newMessage))
            newMessage = "/" + newMessage;
        SendMessage sendMessage = new SendMessage().builder()
                .chatId(String.valueOf(chatId))
                .text(petSim.runCommand(newMessage, users.get(userId)))
                .build();
        getKeyboard(sendMessage, newMessage, userId);
        try {
            if (newMessage.equals("/state") && (!sticker.equals(""))) {
                SendSticker sendSticker = new SendSticker().builder().chatId(String.valueOf(chatId))
                        .sticker(new InputFile(sticker)).build();
                execute(sendSticker);
            }
            execute(sendMessage);
            Serializator.serialize(users);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void runTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                notifyUsers();
            }
        }, 0, 600 * 1000);
    }

    /**
     * метод для отправления пользователям напоминаний о животных
     */
    private void notifyUsers() {
        for (String id : users.keySet()) {
            User user = users.get(id);
            if (user.getPets().size() != 0) {
                for (String name : user.getPets().keySet()) {
                    user.getPets().get(name).updateState();
                    if (user.getPets().get(name).getPetDying() && !user.getPets().get(name).getIsBusy()) {
                        SendMessage sendMessage = new SendMessage().builder()
                                .chatId(String.valueOf(id))
                                .text(String.format("%s %s", name, GameConstants.YOUR_PET_IS_DYING))
                                .build();
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!user.getPets().get(name).getPetFinished().equals("") && !user.getPets().get(name).getIsBusy()) {
                        SendMessage sendMessage = new SendMessage().builder()
                                .chatId(String.valueOf(id))
                                .text(user.getPets().get(name).getPetFinished())
                                .build();
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * метод для десериализации данных пользователей
     */
    private void deserialize() {
        try {
            users = Serializator.deserialize();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод для создания кнопок с командами
     *
     * @param sendMessage отправляемое сообщение
     * @param newMessage  команда пользователя
     */
    private void getKeyboard(SendMessage sendMessage, String newMessage, String userId) {
        User user = users.get(userId);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow startRow = new KeyboardRow();
        KeyboardRow systemRow = new KeyboardRow();
        KeyboardRow boostRow = new KeyboardRow();
        KeyboardRow careRow = new KeyboardRow();
        KeyboardRow petRow = new KeyboardRow();
        KeyboardRow buyRow = new KeyboardRow();
        KeyboardRow shopRow1 = new KeyboardRow();
        KeyboardRow shopRow2 = new KeyboardRow();

        fillKeyboard(startRow, systemRow, boostRow, careRow, petRow, buyRow, shopRow1, shopRow2);

        if (newMessage.equals("/start") | newMessage.equals("/stop") | sendMessageType(sendMessage, GameConstants.ERROR_MESSAGE) |
                sendMessageType(sendMessage, CreationConstants.NON_EXISTENT_PET.toStringValue()))
            addRow(keyboard, startRow);
        else if (sendMessageType(sendMessage, CreationConstants.CHOOSE_PET.toStringValue())) addRow(keyboard, petRow);
        else if (user.currentPet != null && user.currentPet.getIsBusy()) addRow(keyboard, boostRow);
        else if (((newMessage.equals("/care") | newMessage.equals("/state")) && user.currentPet != null && !user.currentPet.getIsBusy()) |
                sendMessage.getText().endsWith(GameConstants.HERE)) addRow(keyboard, careRow);
        else if (sendMessage.getText().startsWith(GameConstants.CHOOSE_TO_BUY)) addRow(keyboard, buyRow);
        else if (newMessage.equals("/shop")) {
            addRow(keyboard, shopRow1);
            keyboard.add(shopRow2);
        } else keyboard.add(systemRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    /**
     * метод, заполняющий ряды клавиатуры командами
     *
     * @param startRow  ряд с командами /help, /create, /stop, /state
     * @param systemRow ряд с командами /pets, /shift, /balance, /care
     * @param boostRow  ряд с командой /boost
     * @param careRow   ряд с командами /eat, /stroke, /walk, /play, /sleep
     * @param petRow    ряд с командами /dog, /cat
     * @param buyRow    ряд с командой /shop
     * @param shopRow1  ряд с командами /maxEnergy, /noHunger
     * @param shopRow2  ряд с командами /noNeed, /moreMoney, /all, /skins
     */
    private void fillKeyboard(KeyboardRow startRow, KeyboardRow systemRow, KeyboardRow boostRow,
                              KeyboardRow careRow, KeyboardRow petRow, KeyboardRow buyRow,
                              KeyboardRow shopRow1, KeyboardRow shopRow2) {
        startRow.add("help\ud83d\udcc3");
        startRow.add("create\ud83c\udd95");
        startRow.add("stop\u274c");
        startRow.add("state\ud83d\udcca");

        systemRow.add("pets\ud83c\udf38");
        systemRow.add("shift\ud83d\udc46");
        systemRow.add("balance\ud83d\udcb0");
        systemRow.add("care\ud83d\udc95");

        boostRow.add("boost\ud83c\udd99");
        boostRow.add("buy\ud83d\udc5b");

        careRow.add("eat\ud83c\udf57");
        careRow.add("stroke\ud83e\udd1a");
        careRow.add("walk\ud83d\udc3e");
        careRow.add("play\ud83c\udfbe");
        careRow.add("sleep\ud83d\udca4");

        petRow.add("dog\ud83d\udc36");
        petRow.add("cat\ud83d\udc31");

        buyRow.add("shop\ud83d\udecd");

        shopRow1.add("maxEnergy\ud83d\udcab");
        shopRow1.add("noHunger\ud83e\uddb4");

        shopRow2.add("noNeed\ud83c\udfe0");
        shopRow2.add("moreMoney\ud83d\udcb8");
        shopRow2.add("all\ud83d\udca5");
        shopRow2.add("skins");
    }

    /**
     * метод, проверящий, совпадают ли сообщения
     *
     * @param sendMessage отправляемое сообщение
     * @param answer      отправляемое сообщение для сравнения
     * @return результат сравнения сообщений
     */
    private boolean sendMessageType(SendMessage sendMessage, String answer) {
        return sendMessage.getText().equals(answer);
    }

    /**
     * метод для заполнения клавиатуры определенным рядом команд
     *
     * @param keyboard клавиатура
     * @param row      ряд клавиатуры
     */
    private void addRow(List<KeyboardRow> keyboard, KeyboardRow row) {
        keyboard.clear();
        keyboard.add(row);
    }
}
