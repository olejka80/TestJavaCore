package com.example.acer.testjavacore;

import com.example.acer.testjavacore.Utils.ConstantManager;

import java.util.ArrayDeque;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class Customer implements Runnable {

    private Logger LOGGER = Logger.getLogger(MainActivity.class.getName());
    private Random mRandom;
    private long mCustomerNumber;

    Customer() {

    }

    private Customer(long customerNumber) {
        mCustomerNumber = customerNumber;
    }

    long getCustomerNumber() {
        return mCustomerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        mCustomerNumber = customerNumber;
    }

    boolean isComing(ArrayDeque<Customer> customerQueue) {
        customerQueue.add(new Customer(1));
        System.out.println("First customer is came " + customerQueue.size());
        mRandom = new Random();
        long timeDelay = mRandom.nextInt(8_000) + 2_000;
        Thread thread = new Thread(() -> {
            for (int i = 1; customerQueue.size() < 100 && customerQueue.size() > 0; i++) {
                try {
                    Thread.sleep(timeDelay);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
                customerQueue.add(new Customer(i));
                LOGGER.info(String.valueOf("Customer index: "
                        + customerQueue.peekLast().getCustomerNumber()
                        + "\n" + "      Size of Queue: " + customerQueue.size()));
            }
        }, Thread.currentThread().getName());
        thread.start();
        return true;
    }

    private void leave() {
        LOGGER.info("Customer leaving");
    }

    synchronized void waiting() {
        LOGGER.info("OK");
    }

    synchronized boolean takeCoffee(ArrayDeque<Customer> customerQueue) {
        Customer customer = customerQueue.pollFirst();
        LOGGER.info("Customer #" + customer.getCustomerNumber() + ": \'Thanks!\'");
        return true;
    }

    private boolean drinking() {
        mRandom = new Random();
        long timeForDrink = mRandom.nextInt(4_000) + 2_000;

            try {
                Thread.sleep(timeForDrink);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }

        LOGGER.info("Customer finished.");
        leave();
        return true;
    }

    int makeOder(int typeOfCoffee) {
        switch (typeOfCoffee) {
            case ConstantManager.AMERICANO_1:
                LOGGER.info("Make me Americano, please");
                break;

            case ConstantManager.CAPPUCCINO_2:
                LOGGER.info("Make me Cappuccino, please");
                break;

            case ConstantManager.LATTE_3:
                LOGGER.info("Make me Latte, please");
                break;

            default:
                LOGGER.info("Make me another type of coffee, please");
        }
        return typeOfCoffee;
    }

    public void makeOder(int[] typeOfCoffee, int[] count) {

    }

    public void makeOder(int[] americano, int[] capuchino, int[] latte, int[] amountOfAmericano,
                         int[] amountOfCapuchino, int[] amountOfLatte) {

    }

    @Override
    public void run() {
        drinking();
    }
}
