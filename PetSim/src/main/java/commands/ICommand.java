package commands;

import Pet.User;

/**
 * интерфейс для выполнения определенных команд: старт, стоп, вывод справки, создание питомца
 */
public interface ICommand {
    /**
     * метод выполнения команд
     */
    String execute(String message, User user);
}
