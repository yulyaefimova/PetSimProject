package commands.shop;

import Pet.User;
import commands.ICommand;

/**
 * класс с методом вывода магазина
 */
public class ShopCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        return """
                Вот улучшения для твоего питомца:
                /maxEnergy - питомец будет быстрее высыпаться(25 coins)
                /noHunger - питомец будет быстрее есть(25 coins)
                /noNeed - питомцу требуется меньше времени на прогулку(25 coins)
                /moreMoney - за каждое действие будет начисляться 2 монеты(30 coins)
                /skins - можешь приборести другие скины для питомца
                /all - у питомца будут сразу все характеристики(90 coins)\s""";
    }
}
