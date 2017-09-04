package com.example.acer.testjavacore;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    public static final Logger LOGGER = Logger.getLogger(MainActivity.class.getName());
    public static Scanner input;
    static boolean haveCoffee;

    private volatile static ArrayDeque<Customer> sCustomerQueue;

    public static void main(String[] args) {

        input = new Scanner(System.in);

        Bill bill = new Bill();
        ArrayList<Bill> billList = new ArrayList<>();
        Barista barista = new Barista();
        Customer customer = new Customer();
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        sCustomerQueue = new ArrayDeque<>();

        // Customer coming
        customer.isComing(sCustomerQueue);

        // customers service

            // check amount of customers
            if (sCustomerQueue.size() != 100 && sCustomerQueue.size() != 0) {

                Thread baristaThread = new Thread(() -> {
                    while (sCustomerQueue.size()>0) {
                        // Barista take an oder
                        barista.takeOder();

                        // Customer make an oder
                        int typeOfCoffee = customer.makeOder(input.nextInt());

                        // Barista making coffee
                        String[] arguments = coffeeMachine.makeCoffee(typeOfCoffee);
                        boolean done = barista.makeCoffee(arguments, customer);
                        while (!done) {
                            done = barista.makeCoffee(coffeeMachine.makeCoffee(customer
                                    .makeOder(input.nextInt())), customer);
                        }

                        long customerNumber = sCustomerQueue.getFirst().getCustomerNumber() + 1;

                        // Insert bill
                        String[] billInfo = barista.insertBill(typeOfCoffee, customerNumber);
                        bill.addToArray(billInfo, billList);

                        // Customer leave the queue
                        haveCoffee = customer.takeCoffee(sCustomerQueue);

                        if (haveCoffee) {
                            Thread customerThread = new Thread(customer);
                            customerThread.start();
                            System.out.println("customer Thread is start");
                        }
                    }
                }, Thread.currentThread().getName());
                baristaThread.start();

            } else LOGGER.info("Your service is too slow, cafe is closing!!!");




    }

}
