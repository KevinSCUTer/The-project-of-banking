package com.experiment.domain;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Bank{
    private final CustomerDAO customerDAO; // utilize customerDAO to handle directory access operation
    private final String filepath = "D:\\demo\\customers.dat"; // absolute filepath
    private ArrayList<Customer> customers = new ArrayList<>();
    // private Customer[] customers;
    private int numberOfCustomers;

    private static Bank bankInstance; // Add a private static variable to hold the instance of the Bank class

    private Bank() {
        customerDAO = new CustomerDAO();
        if (isFileNotEmpty(filepath)) {
            loadCustomers();
        }
    }

    // check if the file is empty
    private boolean isFileNotEmpty(String filepath) {
        try {
            return Files.size(Paths.get(filepath)) > 0;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // update the customers
    public void loadCustomers() {
        // ObjectInputStream is used to simplify the process of input stream and get the list of customers
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            customers = (ArrayList<Customer>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found! ");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Bank getBank() {
        if (bankInstance == null) {
            bankInstance = new Bank(); // Create a new instance of Bank if it doesn't exist
        }
        return bankInstance;
    }

    public void addCustomer(String f, String l) {
        // customers[numberOfCustomers++] = new Customer(f, l);
        Customer newCustomer = new Customer(f, l);
        customers.add(newCustomer);
        customerDAO.addCustomer(newCustomer);
        numberOfCustomers++;
    }

    public void deleteCustomer(String firstName, String lastName) {
        Customer removed = customerDAO.findCustomer(firstName, lastName);
        if (removed != null) {
            customerDAO.deleteCustomer(removed);
            customers.remove(removed);
        }
    }

    public int getNumOfCustomers() {
        return this.numberOfCustomers;
    }

    public List<Customer> getCustomerList() {
        return customerDAO.getCustomers();
    }

    public void saveCustomersToFile() {
        customerDAO.saveCustomers();
    }

    public List<Customer> searchCustomers(String firstName, String lastName) {
        return customerDAO.searchCustomer(firstName, lastName);
    }

    public void sortCustomerList() {
        customerDAO.sortCustomers();
    }

    // get customer by index
    public Customer getCustomerByIndex(int index) {
        if (index >= customers.size()) {
            System.out.println("Invalid index, return the first customer in default!");
            return customers.get(0);
        } else {
            return customers.get(index);
        }
    }

    public Customer getCustomer(String firstname, String lastname) {
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(firstname) && customer.getLastName().equals(lastname)){
                return customer;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "customers=" + toString(customers) +
                ", numberOfCustomers=" + numberOfCustomers +
                '}';
    }

    private String toString(ArrayList<Customer> customers) {
        StringBuilder sb = new StringBuilder();
        customers.forEach(customer -> {
            sb.append(customer.toString());
            sb.append('\n');
        });
        return sb.toString();
    }

    /*
    Step 6 : 1. Add the sortCustomers and searchCustomers method to the Bank class.
     */
    public void sortCustomers(){
        customerDAO.sortCustomers();
    }

    //    searchCustomersByIndex

    public void searchCustomersByIndex(int index){
        if (index < 0 || index >= customers.size()){
            System.out.println("用户不存在，请重新输入");
        } else {
            customers.get(index).toString();
        }
    }

}
