package com.experiment.domain;

/*
 * This class creates the program to test the banking classes.
 * It creates a new Bank, sets the Customer (with an initial balance),
 * and performs a series of transactions with the Account object.
 */

import com.experiment.reports.CustomerReport;

import java.text.NumberFormat;

public class Test {

    public static void main(String[] args) {
        System.out.println("Test function");
    }
    public static void testFunction05() {
        Bank bank = Bank.getBank();

        Customer customer;
        CustomerReport report = new CustomerReport();

        // Create several customers and their accounts
        bank.addCustomer("Jane", "Simms");
        customer = bank.getCustomerByIndex(0);
        customer.addAccount(new SavingsAccount(500.00, 0.05));
        customer.addAccount(new CheckingAccount(200.00, 400.00));

        bank.addCustomer("Owen", "Bryant");
        customer = bank.getCustomerByIndex(1);
        customer.addAccount(new CheckingAccount(200.00));

        bank.addCustomer("Tim", "Soley");
        customer = bank.getCustomerByIndex(2);
        customer.addAccount(new SavingsAccount(1500.00, 0.05));
        customer.addAccount(new CheckingAccount(200.00));

        bank.addCustomer("Maria", "Soley");
        customer = bank.getCustomerByIndex(3);
        // Maria and Tim have a shared checking account
        customer.addAccount(bank.getCustomerByIndex(2).getAccount(1));
        customer.addAccount(new SavingsAccount(150.00, 0.05));

        // Generate a report
        report.generateReport();
    }

    public static void testFunction06(){
        Bank bank = Bank.getBank();
        Customer customer;
        CustomerReport report = new CustomerReport();

        // Create several customers and their accounts
        bank.addCustomer("Jane", "Simms");
        customer = bank.getCustomerByIndex(0);
        customer.addAccount(new SavingsAccount(500.00, 0.05));
        customer.addAccount(new CheckingAccount(200.00, 400.00));

        bank.addCustomer("Owen", "Bryant");
        customer = bank.getCustomerByIndex(1);
        customer.addAccount(new CheckingAccount(200.00));

        bank.addCustomer("Tim", "Soley");
        customer = bank.getCustomerByIndex(2);
        customer.addAccount(new SavingsAccount(1500.00, 0.05));
        customer.addAccount(new CheckingAccount(200.00));

        bank.addCustomer("Maria", "Soley");
        customer = bank.getCustomerByIndex(3);
        // Maria and Tim have a shared checking account
        customer.addAccount(bank.getCustomerByIndex(2).getAccount(1));
        customer.addAccount(new SavingsAccount(150.00, 0.05));

        // Generate a report
        report.generateReport();
    }

    public static void testFunction07(){
        Bank bank = Bank.getBank();
        Customer customer;
        Account  account;

        // Create two customers and their accounts
        bank.addCustomer("Jane", "Simms");
        customer = bank.getCustomerByIndex(0);
        customer.addAccount(new SavingsAccount(500.00, 0.05));
        customer.addAccount(new CheckingAccount(200.00, 500.00));
        bank.addCustomer("Owen", "Bryant");
        customer = bank.getCustomerByIndex(1);
        customer.addAccount(new CheckingAccount(200.00));

        // Test the checking account of Jane Simms (with overdraft protection)
        customer = bank.getCustomerByIndex(0);
        account = customer.getAccount(1);
        System.out.println("Customer [" + customer.getLastName()
                + ", " + customer.getFirstName() + "]"
                + " has a checking balance of "
                + account.getBalance()
                + " with a 500.00 overdraft protection.");
        try {
            System.out.println("Checking Acct [Jane Simms] : withdraw 150.00");
            account.withdraw(150.00);
            System.out.println("Checking Acct [Jane Simms] : deposit 22.50");
            account.deposit(22.50);
            System.out.println("Checking Acct [Jane Simms] : withdraw 147.62");
            account.withdraw(147.62);
            System.out.println("Checking Acct [Jane Simms] : withdraw 470.00");
            account.withdraw(470.00);
        } catch (OverdraftException e1) {
            System.out.println("Exception: " + e1.getMessage()
                    + "   Deficit: " + e1.getDeficit());
        } finally {
            System.out.println("Customer [" + customer.getLastName()
                    + ", " + customer.getFirstName() + "]"
                    + " has a checking balance of "
                    + account.getBalance());
        }
        System.out.println();

        // Test the checking account of Owen Bryant (without overdraft protection)
        customer = bank.getCustomerByIndex(1);
        account = customer.getAccount(0);
        System.out.println("Customer [" + customer.getLastName()
                + ", " + customer.getFirstName() + "]"
                + " has a checking balance of "
                + account.getBalance());
        try {
            System.out.println("Checking Acct [Owen Bryant] : withdraw 100.00");
            account.withdraw(100.00);
            System.out.println("Checking Acct [Owen Bryant] : deposit 25.00");
            account.deposit(25.00);
            System.out.println("Checking Acct [Owen Bryant] : withdraw 175.00");
            account.withdraw(175.00);
        } catch (OverdraftException e1) {
            System.out.println("Exception: " + e1.getMessage()
                    + "   Deficit: " + e1.getDeficit());
        } finally {
            System.out.println("Customer [" + customer.getLastName()
                    + ", " + customer.getFirstName() + "]"
                    + " has a checking balance of "
                    + account.getBalance());
        }
    }
}
