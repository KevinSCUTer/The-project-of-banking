package com.experiment.domain;

import java.util.*;
import java.io.*;

// CustomerDAO类封装了Customer文件访问的所有方法

public class CustomerDAO {
    private List<Customer> customers;
    private final String filepath = "customers.dat";

    public CustomerDAO() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        customers = loadCustomers();
    }
    // The method: add delete search sort
    // 1. use the method of List
    // 2. saveCustomers

    public void addCustomer(Customer customer) {
        customers.add(customer);
        saveCustomers();
    }

    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
        saveCustomers();
    }

    public List<Customer> searchCustomer(String firstName, String lastName) {
        List<Customer> results = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getFirstName().equalsIgnoreCase(firstName) && customer.getLastName().equalsIgnoreCase(lastName)) {
                results.add(customer);
            }
        }
        return results;
    }

    public void sortCustomers() {
        Collections.sort(customers, Comparator.comparing(Customer::getLastName).thenComparing(Customer::getFirstName));
        saveCustomers();
    }

    // After each addition, deletion, modification and query, do a save operation and write the entire file again
    void saveCustomers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Customer> loadCustomers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            return (List<Customer>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer findCustomer(String firstName, String lastName) {
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                return customer;
            }
        }
        return null; // Return null if no match is found
    }


}

