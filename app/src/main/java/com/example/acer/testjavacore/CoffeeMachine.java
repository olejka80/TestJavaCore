package com.example.acer.testjavacore;

import com.example.acer.testjavacore.Utils.ConstantManager;

import static com.example.acer.testjavacore.MainActivity.LOGGER;

class CoffeeMachine {

    /**
     * This method make a coffee in coffee machine
     * @param typeOfCoffee int that user insert from console
     * @return array of Strings type of coffee's number, name of coffee and time for prepare
     */
    String[] makeCoffee(int typeOfCoffee) {
        String coffee = "";
        int time = 0;

        switch (typeOfCoffee) {
            case ConstantManager.AMERICANO_1:
                coffee = ConstantManager.AMERICANO_NAME;
                time = (int) ConstantManager.AMERICANO_TIME;
                break;

            case ConstantManager.CAPPUCCINO_2:
                coffee = ConstantManager.CAPPUCCINO_NAME;
                time = (int) ConstantManager.CAPPUCCINO_TIME;
                break;

            case ConstantManager.LATTE_3:
                coffee = ConstantManager.LATTE_NAME;
                time = (int) ConstantManager.LATTE_TIME;
                break;

            default: LOGGER.info("Wrong type of coffee!!!");
        }
        return new String[] {String.valueOf(typeOfCoffee), coffee, String.valueOf(time)};
    }

}
