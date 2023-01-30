package Pet;

import Constants.GameConstants;
import Constants.Improvements;
import Constants.PetSkins;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * класс Pet предназначен для реализации функционала животного:
 */
public abstract class Pet implements IPet, Serializable {
    /**
     * имя питомца
     */
    public final String name;

    /**
     * текущий скин для стикеров
     */
    protected PetSkins currentSkin = this.getClass().getSimpleName().equals(GameConstants.DOG)
            ? PetSkins.STANDART_DOG : PetSkins.STANDART_CAT;

    /**
     * оплачено ли ускорение
     */
    public boolean isPay = false;

    /**
     * шкала голода
     */
    protected int hunger = 5;

    /**
     * шкала энергии
     */
    protected int energy = 5;

    /**
     * шкала досуга
     */
    protected int leisure = 5;

    /**
     * шкала нужды
     */
    protected int need = 5;

    /**
     * шкала потребности в общении
     */
    protected int communication = 5;

    /**
     * баланс питомца
     */
    private int money;

    /**
     * количество монет, начисляемых за взаимодействие с питомцем
     */
    private int coinForAction = 1;
    /**
     * занят ли питомец
     */
    private boolean isBusy = false;

    /**
     * чем занят питомец
     */
    private String whatPetDoNow;

    /**
     * дата начала выполнения какого-либо действия питомцем
     */
    private Date dateStartAction;

    /**
     * кол-во секунд, которое питомец должен затратить на выполнение какого-либо действия
     */
    private int secondsToDoSomething;

    /**
     * дата последнего понижения потребностей
     */
    private Date previousTimeOfDecrease = new Date();

    /**
     * сообщение о том, что питомец закончил что-то делать
     */
    private String petFinished = "";

    /**
     * сообщение о том, что питомец умирает
     */
    private boolean isPetDying;

    /**
     * сообщение о том, что питомец пока не хочет выполнять действие
     */
    private String unableToDoActionMessage = "";

    /**
     * список купленных улучшений
     */
    private List<Improvements> improvements = new LinkedList<>();

    /**
     * список скинов
     */
    public List<PetSkins> petSkins = new ArrayList<>(Arrays.asList(currentSkin));

    protected Pet(String name) {
        this.name = name;
    }

    /**
     * метод, проверяющий текущее состояние питомца
     *
     * @return желание питомца
     */
    public abstract String checkState();

    /**
     * метод, определяющий вид стикера по состоянию питомца
     *
     * @return id стикера для конкретного желания питомца
     */
    public abstract String checkStickerState();

    /**
     * метод для понижения потребностей, когда с питомцем не контактируют
     */
    public void decrease() {
        if (!isBusy) {
            Date dateNow = new Date();
            long duration = dateNow.getTime() - previousTimeOfDecrease.getTime();
            long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            if (diffInSeconds > 600) {
                previousTimeOfDecrease = new Date();
                if (hunger > 0)
                    hunger--;
                if (need > 0)
                    need--;
                if (leisure > 0)
                    leisure--;
                if (energy > 0)
                    energy--;
                if (communication > 0)
                    communication--;
            }
        }
    }

    /**
     * метод, обновляющий состояние питомца
     */
    public void updateState() {
        if (improvements.contains(Improvements.MORE_MONEY))
            coinForAction = 2;
        decrease();
        checkPetIsDying();
        if (isBusy) {
            Date dateNow = new Date();
            long duration = dateNow.getTime() - dateStartAction.getTime();
            long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            switch (whatPetDoNow) {
                case GameConstants.EATING:
                    if (diffInSeconds >= secondsToDoSomething || isPay) {
                        isPay = false;
                        isBusy = false;
                        petFinished = String.format("%s %s", name, GameConstants.FINISHED_EATING);
                        hunger = 5;
                    }
                    break;
                case GameConstants.SLEEPING:
                    if (diffInSeconds >= secondsToDoSomething || isPay) {
                        isPay = false;
                        isBusy = false;
                        petFinished = String.format("%s %s", name, GameConstants.FINISHED_SLEEPING);
                        energy = 5;
                    }
                    break;
                case GameConstants.WALKING:
                    if (diffInSeconds >= secondsToDoSomething || isPay) {
                        isPay = false;
                        isBusy = false;
                        petFinished = String.format("%s %s", name, GameConstants.FINISHED_WALKING);
                        need = 5;
                    }
                    break;
            }
        }
    }


    /**
     * метод, проверяющий умирает ли питомец
     */
    public void checkPetIsDying() {
        if (hunger < 2 || leisure < 2 || need < 2 || energy < 2 || communication < 2)
            isPetDying = true;
    }

    @Override
    public void eat() {
        if (hunger > 4) {
            unableToDoActionMessage = String.format("%s %s", name, GameConstants.NOT_WANT_EAT);
            return;
        }
        unableToDoActionMessage = "";
        petFinished = "";
        dateStartAction = new Date();
        whatPetDoNow = GameConstants.EATING;
        if (improvements.contains(Improvements.NO_HUNGER))
            secondsToDoSomething = (5 - hunger) * 20;
        else secondsToDoSomething = (5 - hunger) * 50;
        isBusy = true;
        if (need > 0)
            need -= 2;
        money += coinForAction;
    }

    @Override
    public void sleep() {
        if (energy > 4) {
            unableToDoActionMessage = String.format("%s %s", name, GameConstants.NOT_WANT_SLEEP);
            return;
        }
        unableToDoActionMessage = "";
        petFinished = "";
        dateStartAction = new Date();
        whatPetDoNow = GameConstants.SLEEPING;
        isBusy = true;
        if (improvements.contains(Improvements.MAX_ENERGY))
            secondsToDoSomething = (5 - energy) * 100;
        else secondsToDoSomething = (5 - energy) * 200;
        if (communication > 0)
            communication -= 2;
        if (leisure > 0)
            leisure -= 2;
        if (need > 0)
            need--;
        money += coinForAction;
    }

    @Override
    public void stroke() {
        if (leisure > 4) {
            unableToDoActionMessage = String.format("%s %s", name, GameConstants.NOT_WANT_STROKE);
            return;
        }
        unableToDoActionMessage = "";
        leisure = 5;
        communication = 5;
        if (hunger > 0)
            hunger--;
        money += coinForAction;
    }

    @Override
    public void walk() {
        if (need > 4) {
            unableToDoActionMessage = String.format("%s %s", name, GameConstants.NOT_WANT_WALK);
            return;
        }
        unableToDoActionMessage = "";
        petFinished = "";
        dateStartAction = new Date();
        whatPetDoNow = GameConstants.WALKING;
        isBusy = true;
        if (improvements.contains(Improvements.NO_NEED))
            secondsToDoSomething = (5 - need) * 90;
        else secondsToDoSomething = (5 - need) * 200;
        leisure = 5;
        if (energy > 0)
            energy -= 2;
        if (leisure > 0)
            leisure--;
        if (hunger > 0)
            hunger -= 2;
        money += coinForAction;
    }

    @Override
    public void play() {
        if (communication > 4) {
            unableToDoActionMessage = String.format("%s %s", name, GameConstants.NOT_WANT_PLAY);
            return;
        }
        unableToDoActionMessage = "";
        communication = 5;
        leisure = 5;
        if (energy > 0)
            energy -= 2;
        money += coinForAction;
    }

    public boolean getPetDying() {
        return isPetDying;
    }

    public String getUnableToDoActionMessage() {
        return unableToDoActionMessage;
    }

    public String getPetFinished() {
        return petFinished;
    }

    public boolean getIsBusy() {
        return isBusy;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int coins) {
        if (coins >= 0)
            money = coins;
    }

    public String getwhatPetDoNow() {
        return whatPetDoNow;
    }

    public List<Improvements> getImprovements() {
        return improvements;
    }

    public void setSkin(PetSkins skin) {
        currentSkin = skin;
    }

    public PetSkins getSkin() {
        return currentSkin;
    }

    public void setImprovements(Improvements improvement) {
        if (!improvements.contains(improvement))
            improvements.add(improvement);
    }

    public void setPreviousTimeOfDecrease(Date date) {
        previousTimeOfDecrease = date;
    }
}
