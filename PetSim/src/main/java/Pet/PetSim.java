package Pet;

import commands.*;
import commands.activity.*;
import commands.shop.*;

import java.util.HashMap;
import java.util.Map;

/**
 * класс, реализующий Pet.PetSim бота.
 */
public class PetSim {
    public final Map<String, ICommand> commands = new HashMap<>();

    /**
     * метод для ответа на команду в консоли
     *
     * @param message сообщение в консоли
     * @param user    текущий пользователь
     * @return ответ на команду
     */
    public String runCommand(String message, User user) {
        if (user.currentPet != null)
            user.currentPet.updateState();
        fillCommands();
        ICommand command = commands.get(message);
        if (commands.containsKey(message))
            return command.execute(message, user);
        else return new NoCommand().execute(message, user);
    }

    /**
     * метод для передачи телеграм боту id стикеров
     *
     * @param user данные о пользователе
     * @return вызов метода checkStickerState() для определения стикера
     */
    public static String getSticker(User user) {
        if (user.currentPet != null)
            user.currentPet.updateState();
        if (user == null) return "";
        if (user.currentPet == null) return "";
        if (user.currentPet.getIsBusy())
            return "";
        return user.currentPet.checkStickerState();
    }

    /**
     * метод для заполнения словаря командами
     */
    private void fillCommands() {
        commands.put("/start", new StartCommand());
        commands.put("/stop", new StopCommand());
        commands.put("/help", new HelpCommand());
        commands.put("/create", new CreateCommand());
        commands.put("/balance", new BalanceCommand());
        commands.put("/eat", new EatCommand());
        commands.put("/dog", new PetCommand());
        commands.put("/cat", new PetCommand());
        commands.put("/play", new PlayCommand());
        commands.put("/shift", new ShiftCommand());
        commands.put("/sleep", new SleepCommand());
        commands.put("/state", new StateCommand());
        commands.put("/stroke", new StrokeCommand());
        commands.put("/walk", new WalkCommand());
        commands.put("/boost", new BoostCommand());
        commands.put("/pets", new GetPetsCommand());
        commands.put("/care", new CareCommand());
        commands.put("/buy", new BuyCommand());
        commands.put("/shop", new ShopCommand());
        commands.put("/maxEnergy", new MaxEnergyCommand());
        commands.put("/noHunger", new NoHungerCommand());
        commands.put("/noNeed", new NoNeedCommand());
        commands.put("/moreMoney", new MoreMoneyCommand());
        commands.put("/all", new AllCommand());
        commands.put("/skins", new SkinsCommand());
        commands.put("/ginger_skin", new GingerSkinCommand());
        commands.put("/hussie_skin", new HussieSkinCommand());
        commands.put("/change_skin", new ChangeSkinCommand());
    }
}
