package org.example;

import org.example.scheduler.Chron;
import org.example.scheduler.Scheduler;
import org.example.scheduler.SchedulerThread;
import org.example.scheduler.abstractions.IProvideNextExecutionTime;
import org.example.scheduler.abstractions.IRunNotSafeAction;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        checkThisOut();

        IProvideNextExecutionTime nextExecutionTimeProvider;

        //nextExecutionTimeProvider = LocalDateTime::now;
        nextExecutionTimeProvider = () -> LocalDateTime.now();

        IProvideNextExecutionTime startsNowFor5SecondsMax5TimesWithDurationOf500Millis = Chron.builder()
                .setStartTime(LocalDateTime.now())
                .setEndDate(LocalDateTime.now().plusSeconds(5))
                .setMaxExecutionTimes(5)
                .setIntervalDuration(Duration.ofMillis(500))
                .buildNextTimeExecutionProvider();



        IRunNotSafeAction throwAnError = ()->{ throw new Exception(); };

        try {
            throwAnError.executeNotSafeAction();
            if(true) {
                System.out.println("tutaj powinien wystąpić błąd, a nie wystąpił :(");
                return;
            }
        }catch (Exception ex){
        }

        IRunNotSafeAction randomlyThrowsAnError = () -> randomlyThrowException();
        //albo inaczej:
        //IRunNotSafeAction randomlyThrowsAnError = Main::randomlyThrowException;


        Scheduler scheduler = Scheduler.getInstance();
         scheduler
                .forAction(randomlyThrowsAnError)
                .useExecutionTimeProvider(startsNowFor5SecondsMax5TimesWithDurationOf500Millis)
                .onError(ex->handleException(ex))
                .onSingleActionCompleted(()->System.out.println("wykonano akcje z powodzeniem"))
                .onCompleted(()->System.out.println("Zakończyłem pracę"))
                .Schedule();

        Runnable schedulerThread = new SchedulerThread();

        new Thread(schedulerThread).start();

        scheduler.forAction(()->System.out.println("chyna zaczynam to rozumieć"))
                .useExecutionTimeProvider(Chron.builder().setMaxExecutionTimes(1).buildNextTimeExecutionProvider())
                .onCompleted(()->System.out.println("Nie wierzę... działa!"))
                .Schedule();


        runForever();

    }

    private static void handleException(Throwable ex) {
        System.out.println("Wystąpił błąd :(");
    }


    static void randomlyThrowException() throws Exception {
        int nextInt = new Random().nextInt(10);
        if(nextInt<2){
            System.out.println("O nie... coś się popsuło");
            throw new Exception(":(");
        }
        System.out.println("Działam sobie normalnie :)");
        Thread.sleep(nextInt*100);
    }
    static void runForever(){
        new Thread(()->{
            while (true){
                try {
                    Thread.currentThread().join();
                    //Thread.sleep(1000);
                }catch (Exception ignored){}
            }
        }).start();
    }

    static void checkThisOut(){

        IProvide<Person> janKowalskiProvider = Person.builder()
                .setName("Jan")
                .setSurname("Kowalski")
                .buildPersonProvider();

        Person janKowalski = janKowalskiProvider.provide();

    }

}

class Person{
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static PersonBuilder builder(){
        return new PersonBuilder();
    }
}

interface IProvide<T>{
    T provide();
}

class PersonBuilder{
    String name, surname;

    public PersonBuilder setName(String name){
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname){
        this.surname = surname;
        return this;
    }

    public IProvide<Person> buildPersonProvider()
    {
        return ()->{
            Person p = new Person();
            p.setName(name);
            p.setSurname(surname);
            return p;
        };
    }

}