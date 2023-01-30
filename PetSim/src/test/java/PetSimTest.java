import Pet.PetSim;
import Pet.User;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * класс для тестирования основных комманд
 */
class PetSimTest {
    private final CountDownLatch waiter = new CountDownLatch(1);

    /**
     * тест для команды /stop
     */
    @Test
    public void stopMessageTest() {
        PetSim pet = new PetSim();
        User user = new User();
        assertEquals("Приходи создать питомца в другой раз, я буду ждать!", pet.runCommand("/stop", user));
    }

    /**
     * тест для команды /start
     */
    @Test
    public void startMessageTest() {
        PetSim pet = new PetSim();
        User user = new User();
        assertEquals("Привет, я PetSim бот! Напиши /help, чтобы узнать, что я умею делать.",
                pet.runCommand("/start", user));
    }

    /**
     * тест для команды /help
     */
    @Test
    public void helpMessageTest() {
        PetSim pet = new PetSim();
        User user = new User();
        assertEquals("""
                Привет, я PetSim бот! Ты можешь создать себе виртуальных песиков или котов и ухаживать за ними,\s
                для этого напиши /create. Напиши /stop, если передумал создавать питомца.
                Если у тебя несколько питомцев:
                /shift - переключение на другого питомца
                /pets - получить список своих питомцев.
                Что можно сделать с питомцем? Вот основные команды:
                /sleep - отправить его спать
                /eat - покормить
                /stroke - погладить
                /walk - отправить гулять
                /play - поиграть с ним
                /state - узнать состояние вашего питомца
                /boost - ускорить выполнение действия питомцем(1 ускорение = 2 монеты)
                /balance - узнать количество денег на вашем виртуальном счету
                /change_skin - сменить скин
                /buy - купить питомцу улучшение в магазине\s""",
                pet.runCommand("/help", user));
    }

    /**
     * тест для неизвестной команды
     */
    @Test
    public void unknownMessageTest() {
        PetSim pet = new PetSim();
        User user = new User();
        assertEquals("Я не знаю такой команды!", pet.runCommand("1234", user));
    }

    /**
     * тест для создания питомца
     */
    @Test
    public void createPetTest() {
        PetSim pet = new PetSim();
        User user = new User();
        assertEquals("Как ты его назовешь?", pet.runCommand("/dog", user));
    }

    /**
     * тест для обновления состояния
     */
    @Test
    public void updateConditionTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        pet.runCommand("Jack", user);
        pet.runCommand("/walk", user);
        pet.runCommand("/play", user);
        assertEquals("Я счастлив!\uD83D\uDC95", pet.runCommand("/state", user));
    }

    /**
     * тест для вызова функции
     */
    @Test
    public void FunctionTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        pet.runCommand("Jack", user);
        assertEquals("Jack  пока не голоден!", pet.runCommand("/eat", user));
    }

    /**
     * тест для вызова функции при отсутствии питомца
     */
    @Test
    public void noPetTest() {
        PetSim pet = new PetSim();
        User user = new User();
        assertEquals("У вас еще нет питомца! Напишите /create, чтобы создать его.",
                pet.runCommand("/sleep", user));
    }

    /**
     * тест для имени питомца
     */
    @Test
    public void nameTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        assertEquals("Теперь у тебя есть новый питомец по имени Rex", pet.runCommand("Rex", user));
    }

    /**
     * тест на начисление монет
     */
    @Test
    public void balanceTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        pet.runCommand("Dog", user);
        pet.runCommand("/sleep", user);
        pet.runCommand("/walk", user);
        pet.runCommand("/play", user);
        assertEquals("0 coins", pet.runCommand("/balance", user));
    }

    /**
     * тест на создание нескольких питомцев
     */
    @Test
    public void severalPetsTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        pet.runCommand("Dog", user);
        pet.runCommand("/cat", user);
        pet.runCommand("Cat", user);
        assertEquals(2, user.getPets().size());
    }

    /**
     * тест на переключение между питомцами
     */
    @Test
    public void shiftPetsTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        pet.runCommand("Dog", user);
        pet.runCommand("/cat", user);
        pet.runCommand("Cat", user);
        pet.runCommand("/shift", user);
        pet.runCommand("Dog", user);
        assertEquals("Dog", user.currentPet.name);
    }

    /**
     * тест на покупку улучшений в магазине
     */
    @Test
    public void shopTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        pet.runCommand("Dog", user);
        user.currentPet.setMoney(25);
        assertEquals("Недостаточно средств!", pet.runCommand("/all", user));
        assertEquals("Теперь вашему питомцу требуется меньше времени на сон!",
                pet.runCommand("/maxEnergy", user));
    }

    /**
     * тест на изменение скина питомца
     */
    @Test
    public void changeSkinTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        pet.runCommand("Dog", user);
        assertEquals("У тебя нет других скинов!", pet.runCommand("/change_skin", user));
        user.currentPet.setMoney(30);
        pet.runCommand("/hussie_skin", user);
        assertEquals("Скин изменен!", pet.runCommand("/change_skin", user));
    }

    /**
     * тест на вывод стикеров в соответствии вида и настроения питомца
     */
    @Test
    public void stickerStateTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        pet.runCommand("Dog", user);
        assertEquals("CAACAgIAAxkBAAEDV1Jhm_AtBsGJ_jsyvmn5E_XhM2qt4wACWAADW__yCpRmb5wHENngIgQ",
                user.currentPet.checkStickerState());
        pet.runCommand("/cat", user);
        pet.runCommand("Cat", user);
        assertEquals("CAACAgIAAxkBAAEDV_xhnMXKDZOz0iLlL4joDhhvwziQ1QACowADECECEI0MfB_BOTEXIgQ",
                user.currentPet.checkStickerState());
    }

    /**
     * тест на понижение состояния питомца
     */
    @Test
    public void decreaseTest() {
        PetSim pet = new PetSim();
        User user = new User();
        pet.runCommand("/dog", user);
        pet.runCommand("Dog", user);
        user.currentPet.setPreviousTimeOfDecrease(new GregorianCalendar(2021, Calendar.DECEMBER, 14).getTime());
        assertEquals("Я голодный как волк!\uD83D\uDC3A", pet.runCommand("/state", user));
    }
}