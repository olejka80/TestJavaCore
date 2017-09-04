package com.example.acer.testjavacore;

import com.example.acer.testjavacore.Utils.ConstantManager;

import java.util.logging.Level;
import java.util.logging.Logger;

class Barista {

    private static final Logger LOGGER = Logger.getLogger(Barista.class.getName());

    void takeOder() {
        LOGGER.info("What kind of coffee would you like? (Push a button)");
    }

    String[] insertBill(int typeOfCoffee, long checkNumber) {
        String coffee;
        double price;
        Bill bill = new Bill();
        if (typeOfCoffee == ConstantManager.AMERICANO_1) {
            price = ConstantManager.AMERICANO_PRICE;
            coffee = ConstantManager.AMERICANO_NAME;
        } else if (typeOfCoffee == ConstantManager.CAPPUCCINO_2) {
            price = ConstantManager.CAPPUCCINO_PRICE;
            coffee = ConstantManager.CAPPUCCINO_NAME;
        } else {
            price = ConstantManager.LATTE_PRICE;
            coffee = ConstantManager.LATTE_NAME;
        }
        String dateTime = bill.getCurrentDateTime();
        LOGGER.info("check: #" + checkNumber + "\n"
                + "oder: " + coffee + "\n"
                + "price: " + price + "\n"
                + "date tame: " + dateTime);
        return new String[]{String.valueOf(checkNumber), coffee, String.valueOf(price), dateTime};
    }

    boolean makeCoffee(String[] args, Customer customer) {

        int typeOfCoffee = Integer.valueOf(args[0]);
        String coffee = args[1];
        long time = Long.valueOf(args[2]);

        if (typeOfCoffee == 1 || typeOfCoffee == 2 || typeOfCoffee == 3) {
            try {
                LOGGER.info("Your " + coffee + " will be ready in "
                        + time/1000 + " minutes");
                customer.waiting();
                Thread.sleep(time);
                LOGGER.info("Your " + coffee + " is ready");
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        } else  {
            LOGGER.info("Take another oder, please...");
            return false;
        }
        return true;
    }

}
