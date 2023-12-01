package com.experiment.domain;

import com.experiment.domain.Account;

/*
    SavingsAccount继承了Account，用于处理存款的相关事宜
 */
public class SavingsAccount extends Account {

    private double interestRate; // 增加了变量：利率

    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }
}
