package com.experiment.reports;

import com.experiment.domain.*;

import java.text.NumberFormat;

public class CustomerReport {
    Bank bank = Bank.getBank();
    Customer customer;

    public CustomerReport() {

    }

    public void generateReport() {
        bank.sortCustomers(); // 先对顾客进行排序
        // 其余代码保持不变
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        // Generate a report
        System.out.println("\t\t\tCUSTOMERS REPORT");
        System.out.println("\t\t\t================");

        for (int cust_idx = 0; cust_idx < bank.getNumOfCustomers(); cust_idx++) {
            Customer customer = bank.getCustomerByIndex(cust_idx);

            System.out.println();
            System.out.println("Customer: "
                    + customer.getLastName() + ", "
                    + customer.getFirstName());

            for (int acct_idx = 0; acct_idx < customer.getNumOfAccounts(); acct_idx++) {
                Account account = customer.getAccount(acct_idx);
                String account_type = "";

                // Determine the account type
                if (account instanceof SavingsAccount) {
                    account_type = "Savings Account";
                } else if (account instanceof CheckingAccount) {
                    account_type = "Checking Account";
                } else {
                    account_type = "Account";
                }

                // Print the current balance of the account
                double balance = account.getBalance();
                String formattedBalance = currencyFormat.format(balance);

                System.out.println(account_type + "  " + "current balance is " + formattedBalance);
            }
        }
    }
}
