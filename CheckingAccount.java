package com.experiment.domain;

import com.experiment.domain.Account;

/*
    CheckingAccount继承了Account，用于处理转出的相关事宜
 */
public class CheckingAccount extends Account {
    private double overdraftProtection; // 增加了变量：保护机制

    public CheckingAccount(double balance) {
        super(balance);
    }

    public CheckingAccount(double balance, double overdraftProtection) {
        super(balance);
        this.overdraftProtection = overdraftProtection;
    }

    public double getOverdraftProtection() {
        return overdraftProtection;
    }

    /*
        The CheckingAccount class must override the withdraw method.
        It must it perform the following check: if the current balance is adequate to cover the amount to withdraw
        , then proceed as usual. If not and if there is overdraft protection
        , then attempt to cover the difference (balance - amount) by value of the overdraftProtection.
        If the amount needed to cover the overdraft is greater than the current level of protection
        ,then the whole transaction must fail with the checking balance unaffected.
        */
    /*
    @Override
    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            super.withdraw(amount); // Sufficient balance, proceed with regular withdrawal
            return true;
        } else if (this.balance + this.overdraftProtection >= amount) {
            double overdraftAmount = amount - this.balance;
            this.balance = 0; // Set balance to zero
            this.overdraftProtection -= overdraftAmount; // Deduct overdraft amount from protection
            return true;
        } else {
            return false; // Insufficient funds and overdraft protection, transaction fails
        }
    }
    */
    @Override
    public void withdraw(double amount) throws OverdraftException{
        if (this.balance >= amount) {
            super.withdraw(amount); // Sufficient balance, proceed with regular withdrawal
        } else if (this.balance + this.overdraftProtection >= amount) {
            double overdraftAmount = amount - this.balance;
            this.balance = 0; // Set balance to zero
            this.overdraftProtection -= overdraftAmount; // Deduct overdraft amount from protection
            throw new OverdraftException("Insufficient funds for overdraft protection  ", amount - getBalance());
        } else {
            throw new OverdraftException("No overdraft protection  ", amount - getBalance());
        }
    }
}