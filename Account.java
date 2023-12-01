package com.experiment.domain;

public class Account {
    protected double balance;

    public Account(){
        this.balance = 0.0;
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean deposit(double amount){
        this.balance = getBalance() + amount;
        return true;
    }

    /*
    public boolean withdraw(double amount){
        if(this.balance < amount){
            return false;
        } else{
            this.balance = getBalance() - amount;
            return true;
        }
    }
    */

    // Rewrite the witdraw method so that it does not return a value (that is, void).
    // Declare that this method throws the OverdraftException.
    public void withdraw(double amount) throws OverdraftException{
        if(this.balance > amount){
            this.balance = getBalance() - amount;
        } else{
            throw new OverdraftException("Insufficient funds for overdraft protection  ", amount - getBalance());
        }
    }

}
