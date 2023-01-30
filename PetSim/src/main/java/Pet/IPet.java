package Pet;

/**
 * интерфейс для реализации функционала питомца
 */
public interface IPet {
    /**
     * покормить
     */
    void eat();

    /**
     * поспать
     */
    void sleep();

    /**
     * погладить
     */
    void stroke();

    /**
     * прогулка
     */
    void walk();

    /**
     * поиграть
     */
    void play();
}
