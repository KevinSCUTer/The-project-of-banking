package com.experiment.domain;

import com.experiment.domain.Account;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.Serializable;
import java.util.Objects;

/*
    Step 6 : 2. Modify the Customer class to implement the Comparable interface.
 */
public class Customer implements Comparable<Object>, Serializable{
    private static final long serialVersionUID = 1L; // UID
    private String firstName;
    private String lastName;

    // create unlimited array with ArrayList class.
    // It must include the public methods: addAccount(Account), getAccount(int), and getNumOfAccounts().
    private ArrayList<Account> accounts = new ArrayList<>();

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public int getNumOfAccounts(){
        return accounts.size();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    // get account using index
    public Account getAccount(int i) {
        return accounts.get(i);
    }

    public void sort() {
        accounts.sort((o1, o2) -> {
            // Compare the balances of two accounts
            if (o1.balance - o2.balance < 0) {
                return -1;  // o1 balance is smaller, so it comes before o2
            } else if (o1.balance - o2.balance > 0) {
                return 1;   // o1 balance is larger, so it comes after o2
            } else {
                return 0;   // balances are equal, no change in order
            }
        });
    }

    // 实现compareTo函数
    @Override
    public int compareTo(Object o) {
        // 先确定对象是不是 Customer
        // 再利用 String 已经实现的按字母排序的 compareTo 进行对比
        // 先比较 lastName， 再比较 firstName
        if (o instanceof Customer){
            int lastNameComparison = this.getLastName().compareTo(((Customer) o).getLastName());
            if (lastNameComparison != 0) {
                return lastNameComparison;
            } else {
                return this.getFirstName().compareTo(((Customer) o).getFirstName());
            }
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(getFirstName(), customer.getFirstName()) && Objects.equals(getLastName(), customer.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}
