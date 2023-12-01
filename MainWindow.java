package com.experiment.bankingUI;

import com.experiment.domain.*;
import com.experiment.reports.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.io.*;

// MainWindow: Utilized for Basic GUI
public class MainWindow extends JFrame {
    // Initialization
    Bank bank = Bank.getBank();
    // private CustomerDAO customerDAO = new CustomerDAO();
    private DefaultListModel<String> customerListModel = new DefaultListModel<>();
    private JList<String> customerList = new JList<>(customerListModel);
    private JTextField firstNameField = new JTextField(10);
    private JTextField lastNameField = new JTextField(10);

    public MainWindow() {
        setTitle("Banking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the operation of exit
        setLayout(new FlowLayout());

        loadCustomerListToModel(); // Load customers into the list model

        // Add components to frame
        add(new JScrollPane(customerList));
        add(new JLabel("FirstName:"));
        add(firstNameField);
        add(new JLabel("LastName:"));
        add(lastNameField);
        add(createButton("ADD"));
        add(createButton("DEL"));
        add(createButton("SEARCH"));
        add(createButton("SORT"));
        pack();
        setVisible(true);
    }

    private JButton createButton(String title) {
        JButton button = new JButton(title);
        button.addActionListener(new ButtonClickListener());
        return button;
    }

    // Load customers and then add them to the list model
    private void loadCustomerListToModel() {
        customerListModel.clear();
        // List<Customer> customers = customerDAO.getCustomers();
        List<Customer> customers = bank.getCustomerList(); // Getting the list of customers
        for (Customer customer : customers) {
            customerListModel.addElement(customer.toString()); // Customer has a toString override
        }
    }

    // ButtonClickListener is an anonymous inner class to handle add, delete, search and sort
    // Using a series of operations such as encapsulation to process addition, deletion, searching and sorting.
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(); // use ActionEvent to get command
            switch (command) {
                // choose command
                case "ADD":
                    // Add the customer
                    Customer newCustomer = new Customer(firstNameField.getText(), lastNameField.getText());
                    // customerDAO.addCustomer(newCustomer);
                    bank.addCustomer(newCustomer.getFirstName(), newCustomer.getLastName());
                    customerListModel.addElement(newCustomer.toString());
                    break;
                case "DEL":
                    // Delete the customer
                    String selectedValue = customerList.getSelectedValue();
                    System.out.println("The text is selected.");
                    if (selectedValue != null) {
                        // Split the selectedValue string
                        String[] parts = selectedValue.split(" ");
                        // Check if the parts array has exactly two elements: LastName and FirstName
                        if (parts.length == 2) {
                            String firstName = parts[0].trim(); // Remove any leading/trailing whitespace
                            System.out.println(firstName);
                            String lastName = parts[1].trim(); // Remove any leading/trailing whitespace
                            System.out.println(lastName);

                            // Find the exact customer in the customerDAO using a search
                            // Since there's a method in CustomerDAO that can find a customer by full name
                            // Customer customer = customerDAO.findCustomer(firstName, lastName);
                            Customer customer = bank.getCustomer(firstName, lastName);
                            System.out.println(customer);

                            if (customer != null) {
                                System.out.println("即将执行删除操作");
                                bank.deleteCustomer(customer.getFirstName(), customer.getLastName());
                                // customerDAO.deleteCustomer(customer);
                                customerListModel.removeElement(selectedValue);
                            }
                        }
                    }
                    System.out.println("删除完毕");
                    break;
                case "SEARCH":
                    String firstName = firstNameField.getText(); // get the information of firstname
                    String lastName = lastNameField.getText(); // get the information of lastname
                    // List<Customer> searchResults = customerDAO.searchCustomer(firstName, lastName);
                    List<Customer> searchResults = bank.searchCustomers(firstName, lastName);
                    customerListModel.clear(); // clear the model, and then add element if the name was found
                    for (Customer c : searchResults) {
                        customerListModel.addElement(c.toString());
                    }
                    break;
                case "SORT":
                    // customerDAO.sortCustomers();
                    bank.sortCustomers();
                    loadCustomerListToModel();
                    break;
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });
    }
}

