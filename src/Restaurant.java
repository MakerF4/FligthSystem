import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;



public class Restaurant extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int NUM_ITEMS = 12;
    private static final int MAX_ORDER = 100;

    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel ticketLabel;
    private JTextField ticketField;
    private JLabel itemsLabel;
    private JCheckBox[] itemCheckBoxes;
    private JLabel[] itemLabels;
    private JTextField[] itemFields;
    private JButton orderButton;
    private JButton clearButton;
    private JTextArea outputArea;





    private int[] itemCounts;
    private Map<String, Integer> itemOrderCounts;
    private Map<String, Long> itemRestockedTimes;





    public JPanel createRestaurantPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(this.getContentPane());
        return mainPanel;
    }



    public Restaurant() {
        super("Restaurant");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(10);
        ticketLabel = new JLabel("Ticket #: ");
        ticketField = new JTextField(10);

        itemsLabel = new JLabel("Select items: ");
        itemCheckBoxes = new JCheckBox[NUM_ITEMS];
        itemLabels = new JLabel[NUM_ITEMS];
        itemFields = new JTextField[NUM_ITEMS];

        itemLabels = new JLabel[] {
                new JLabel("Three Egg Cheese $2.0"),
                new JLabel("Farmer's Breakfast $3.0"),
                new JLabel("Farmer Sausage & Eggs $4.0"),
                new JLabel("Fries $5.0"),
                new JLabel("Onion rings $6.0"),
                new JLabel("Chicken wings $3.0"),
                new JLabel("Pizza $4.0"),
                new JLabel("Pasta $2.0"),
                new JLabel("Steak $6.0"),
                new JLabel("Shrimp $7.0"),
                new JLabel("Soup $4.0"),
                new JLabel("Drink $3.0")
        };

        for (int i = 0; i < NUM_ITEMS; i++) {
            itemCheckBoxes[i] = new JCheckBox();
            itemFields[i] = new JTextField(3);
            itemFields[i].setEnabled(false);
            itemCheckBoxes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JCheckBox checkBox = (JCheckBox) event.getSource();
                    JTextField textField = itemFields[getItemIndex(checkBox)];
                    textField.setEnabled(checkBox.isSelected());
                }
            });
        }


        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        itemCounts = new int[NUM_ITEMS];
        Arrays.fill(itemCounts, 100);
        itemOrderCounts = new HashMap<>();
        itemRestockedTimes = new HashMap<>();

        JPanel northPanel = new JPanel();
        northPanel.add(nameLabel);
        northPanel.add(nameField);
        northPanel.add(ticketLabel);
        northPanel.add(ticketField);
        add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(itemsLabel);
        for (int i = 0; i < NUM_ITEMS; i++) {
            JPanel itemPanel = new JPanel();
            itemPanel.add(itemCheckBoxes[i]);
            itemPanel.add(itemLabels[i]);
            itemPanel.add(itemFields[i]);
            centerPanel.add(itemPanel);
        }
        add(centerPanel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.add(orderButton);
        southPanel.add(clearButton);
        add(southPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.EAST);

        orderButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == orderButton) {
            String name = nameField.getText();
            String ticket = ticketField.getText();
            if (name.isEmpty() || ticket.isEmpty()) {
                outputArea.append("Error: Please enter both name and ticket number.\n");
                return;
            }

            Map<String, Integer> items = new HashMap<>();
            for (int i = 0; i < NUM_ITEMS; i++) {
                if (itemCheckBoxes[i].isSelected()) {
                    String item = itemLabels[i].getText();
                    int count;
                    try {
                        count = Integer.parseInt(itemFields[i].getText());
                    } catch (NumberFormatException e) {
                        outputArea.append("Error: Please enter a valid number for " + item + ".\n");
                        return;
                    }
                    if (count <= 0 || count > MAX_ORDER) {
                        outputArea.append("Error: Invalid number of items for " + item + ".\n");
                        return;
                    }
                    items.put(item, count);
                }
            }
            if (items.isEmpty()) {
                outputArea.append("Error: Please select at least one item.\n");
                return;
            }

            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                String item = entry.getKey();
                int count = entry.getValue();
                int itemIndex = getItemIndex(item);
                int remaining = itemCounts[itemIndex];
                if (count > remaining) {
                    long restockedTime = itemRestockedTimes.getOrDefault(item, 0L);
                    long currentTime = System.currentTimeMillis();
                    if (currentTime < restockedTime) {
                        outputArea.append("Error: " + item + " is out of stock. Please try again in 3 minutes.\n");
                        return;
                    } else {
                        itemRestockedTimes.remove(item);
                    }
                }
                int orderCount = itemOrderCounts.getOrDefault(item, 0);
                if (orderCount + count > MAX_ORDER) {
                    outputArea.append("Error: Cannot order more");
                    itemOrderCounts.put(item, orderCount + count);
                    itemCounts[itemIndex] -= count;
                }
            }
            double totalCost = 0.0;
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                String item = entry.getKey();
                int count = entry.getValue();
                int itemIndex = getItemIndex(item);
                double itemPrice = 0.0;
                switch (itemIndex) {
                    case 0:
                        itemPrice = 2.0;
                        break;
                    case 1:
                        itemPrice = 3.0;
                        break;
                    case 2:
                        itemPrice = 4.0;
                        break;
                    case 3:
                        itemPrice = 5.0;
                        break;
                    case 4:
                        itemPrice = 6.0;
                        break;
                    case 5:
                        itemPrice = 3.0;
                        break;
                    case 6:
                        itemPrice = 4.0;
                        break;
                    case 7:
                        itemPrice = 2.0;
                        break;
                    case 8:
                        itemPrice = 6.0;
                        break;
                    case 9:
                        itemPrice = 7.0;
                        break;
                    case 10:
                        itemPrice = 4.0;
                        break;
                    case 11:
                        itemPrice = 3.0;
                        break;

                }
                totalCost += itemPrice * count;
            }


            outputArea.append("Total cost: $" + totalCost + "\n");



            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                outputArea.append("\t" + entry.getKey() + ": " + entry.getValue() + "\n");
            }
            try {

                FileWriter writer = new FileWriter("orders.csv", true);


                writer.write(name + "," + ticket);
                for (Map.Entry<String, Integer> entry : items.entrySet()) {
                    String item = entry.getKey();
                    int count = entry.getValue();
                    writer.write("," + item + "," + count);
                }
                writer.write("\n");


                writer.close();


                outputArea.append("Order placed successfully!\n");
                outputArea.append("Name: " + name + "\n");
                outputArea.append("Ticket #: " + ticket + "\n");
                outputArea.append("Items:\n");
                for (Map.Entry<String, Integer> entry : items.entrySet()) {
                    outputArea.append("\t" + entry.getKey() + ": " + entry.getValue() + "\n");
                }
            } catch (IOException e) {
                outputArea.append("Error: Could not save order information.\n");
            }
        } else if (event.getSource() == clearButton) {
            nameField.setText("");
            ticketField.setText("");
            for (int i = 0; i < NUM_ITEMS; i++) {
                itemCheckBoxes[i].setSelected(false);
                itemFields[i].setText("");
                itemFields[i].setEnabled(false);
            }
            outputArea.setText("");
        }
    }


    private int getItemIndex(String item) {
        for (int i = 0; i < NUM_ITEMS; i++) {
            if (itemLabels[i].getText().equals(item)) {
                return i;
            }
        }
        return -1;
    }

    private int getItemIndex(JCheckBox checkBox) {
        for (int i = 0; i < NUM_ITEMS; i++) {
            if (itemCheckBoxes[i] == checkBox) {
                return i;
            }
        }
        return -1;
    }



}
