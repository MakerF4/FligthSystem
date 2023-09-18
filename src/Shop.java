
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Shop {
    private List<Product> products = new ArrayList<>();
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private JComboBox<Product> productComboBox;
    private JTextField nameField;
    private JTextField ticketField;
    private JButton addButton;
    private JButton purchaseButton;
    private double totalCost = 0.0;

    public JPanel createShopPanel() {
        return mainPanel;
    }


    private class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String toString() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    private boolean isOfferActive() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        return hour >= 0 && hour <= 11 && minute % 5 == 0;
    }

    private double applyOffer(double price) {
        return isOfferActive() ? price / 2 : price;
    }

    private class PurchaseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String ticket = ticketField.getText();
            if (name.isEmpty() || ticket.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name and ticket number are required to purchase.");
            } else {
                Product product = (Product) productComboBox.getSelectedItem();
                double price = product.getPrice();
                price = applyOffer(price);
                totalCost += price;
                JOptionPane.showMessageDialog(frame, "Purchase of " + product + " successful! Total cost: £" + totalCost);
            }
        }
    }


    public Shop() {
        products.add(new Product("Chanel", 10.0));
        products.add(new Product("Twix", 20.0));
        products.add(new Product("keyboard", 30.0));
        products.add(new Product("sofa", 40.0));
        products.add(new Product("shirt", 50.0));
        products.add(new Product("ball", 60.0));
        products.add(new Product("mouse", 70.0));
        products.add(new Product("Amazon vauncher", 80.0));
        products.add(new Product("headphones", 90.0));
        products.add(new Product("notebook", 100.0));
        products.add(new Product("coke", 110.0));
        products.add(new Product("milk", 120.0));
        frame = new JFrame("Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));

        productComboBox = new JComboBox<>();
        for (Product product : products) {
            productComboBox.addItem(product);
        }
        formPanel.add(new JLabel("Select Product:"));
        formPanel.add(productComboBox);

        nameField = new JTextField();
        formPanel.add(new JLabel("Enter Name:"));
        formPanel.add(nameField);

        ticketField = new JTextField();
        formPanel.add(new JLabel("Enter Ticket Number:"));
        formPanel.add(ticketField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = (Product) productComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(frame, product + " added to cart.");
            }
        });
        buttonPanel.add(addButton);

        purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(new PurchaseActionListener());
        buttonPanel.add(purchaseButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }


}
