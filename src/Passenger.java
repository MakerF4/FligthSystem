import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.io.FileWriter;
import java.io.IOException;


public class Passenger extends JFrame {

    private JPanel contentPane;
    private JTextField firstNameField;
    private JTextField surnameField;
    private JTextField dateOfBirthField;
    private JTextField passportCountryField;
    private JTextField passportTypeField;
    private JTextField passportNumberField;
    private JTextField countryCodeField;
    private JTextField telephoneNumberField;
    private JTextField emailField;
    private JTextField numOfBagsField;
    private JTextField weightOfBagsField;
    private JTextField disabilityField;
    private JTextField ticketNumberField;


    public JPanel createPassengerPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(this.getContentPane());
        return mainPanel;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Passenger frame = new Passenger();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Passenger() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(10, 11, 70, 14);
        panel.add(lblFirstName);

        firstNameField = new JTextField();
        firstNameField.setBounds(90, 8, 86, 20);
        panel.add(firstNameField);
        firstNameField.setColumns(10);

        JLabel lblSurname = new JLabel("Surname:");
        lblSurname.setBounds(10, 36, 70, 14);
        panel.add(lblSurname);

        surnameField = new JTextField();
        surnameField.setBounds(90, 33, 86, 20);
        panel.add(surnameField);
        surnameField.setColumns(10);

        JLabel lblDateOfBirth = new JLabel("Date of Birth:");
        lblDateOfBirth.setBounds(10, 61, 70, 14);
        panel.add(lblDateOfBirth);

        dateOfBirthField = new JTextField();
        dateOfBirthField.setBounds(90, 58, 86, 20);
        panel.add(dateOfBirthField);
        dateOfBirthField.setColumns(10);

        JLabel lblPassportCountry = new JLabel("Passport Country:");
        lblPassportCountry.setBounds(10, 86, 95, 14);
        panel.add(lblPassportCountry);

        passportCountryField = new JTextField();
        passportCountryField.setBounds(115, 83, 86, 20);
        panel.add(passportCountryField);
        passportCountryField.setColumns(10);

        JLabel lblPassportType = new JLabel("Passport Type:");
        lblPassportType.setBounds(10, 111, 95, 14);
        panel.add(lblPassportType);

        passportTypeField = new JTextField();
        passportTypeField.setBounds(115, 108, 86, 20);
        panel.add(passportTypeField);
        passportTypeField.setColumns(10);

        JLabel lblPassportNumber = new JLabel("Passport Number:");
        lblPassportNumber.setBounds(10, 136, 95, 14);
        panel.add(lblPassportNumber);

        passportNumberField = new JTextField();
        passportNumberField.setBounds(115, 133, 86, 20);
        panel.add(passportNumberField);
        passportNumberField.setColumns(10);

        JLabel lblCountryCode = new JLabel("Country Code:");
        lblCountryCode.setBounds(10, 161, 95, 14);
        panel.add(lblCountryCode);

        countryCodeField = new JTextField();
        countryCodeField.setBounds(115, 158, 86, 20);
        panel.add(countryCodeField);
        countryCodeField.setColumns(10);

        JLabel lblTelephoneNumber = new JLabel("Telephone Number:");
        lblTelephoneNumber.setBounds(10, 186, 95, 14);
        panel.add(lblTelephoneNumber);

        telephoneNumberField = new JTextField();
        telephoneNumberField.setBounds(115, 183, 86, 20);
        panel.add(telephoneNumberField);
        telephoneNumberField.setColumns(10);
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 211, 95, 14);
        panel.add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(115, 208, 86, 20);
        panel.add(emailField);
        emailField.setColumns(10);

        JLabel lblNumOfBags = new JLabel("Num of Bags:");
        lblNumOfBags.setBounds(220, 11, 95, 14);
        panel.add(lblNumOfBags);

        numOfBagsField = new JTextField();
        numOfBagsField.setBounds(325, 8, 86, 20);
        panel.add(numOfBagsField);
        numOfBagsField.setColumns(10);

        JLabel lblWeightOfBags = new JLabel("Weight of Bags:");
        lblWeightOfBags.setBounds(220, 36, 95, 14);
        panel.add(lblWeightOfBags);

        weightOfBagsField = new JTextField();
        weightOfBagsField.setBounds(325, 33, 86, 20);
        panel.add(weightOfBagsField);
        weightOfBagsField.setColumns(10);

        JLabel lblDisability = new JLabel("Disability:");
        lblDisability.setBounds(220, 61, 95, 14);
        panel.add(lblDisability);

        disabilityField = new JTextField();
        disabilityField.setBounds(325, 58, 86, 20);
        panel.add(disabilityField);
        disabilityField.setColumns(10);

        JLabel lblTicketNumber = new JLabel("Ticket Number:");
        lblTicketNumber.setBounds(220, 86, 95, 14);
        panel.add(lblTicketNumber);

        ticketNumberField = new JTextField();
        ticketNumberField.setBounds(325, 83, 86, 20);
        panel.add(ticketNumberField);
        ticketNumberField.setColumns(10);
        ticketNumberField.setEditable(false);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(165, 227, 89, 23);
        panel.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(firstNameField.getText().isEmpty() || surnameField.getText().isEmpty() || dateOfBirthField.getText().isEmpty() || passportCountryField.getText().isEmpty() || passportTypeField.getText().isEmpty() || passportNumberField.getText().isEmpty() || countryCodeField.getText().isEmpty() || telephoneNumberField.getText().isEmpty() || emailField.getText().isEmpty() || numOfBagsField.getText().isEmpty() || weightOfBagsField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = null;
                    try {
                        date = sdf.parse(dateOfBirthField.getText());
                    } catch (ParseException e1) {
                        JOptionPane.showMessageDialog(null, "Invalid date format. Please enter in format 'dd/MM/yyyy'", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    if((month > Calendar.getInstance().get(Calendar.MONTH) && year >= Calendar.getInstance().get(Calendar.YEAR)) || (month == Calendar.getInstance().get(Calendar.MONTH) && day > Calendar.getInstance().get(Calendar.DAY_OF_MONTH) && year >= Calendar.getInstance().get(Calendar.YEAR))) {
                        JOptionPane.showMessageDialog(null, "Invalid date of birth. Please enter a valid date", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Random random = new Random();
                    int ticketNum = random.nextInt(999999999) + 1000000000;
                    ticketNumberField.setText(String.valueOf(ticketNum));
                    JOptionPane.showMessageDialog(null, "Passenger information submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        FileWriter writer = new FileWriter("passenger_info.txt", true);
                        writer.write(firstNameField.getText() + ",");
                        writer.write(surnameField.getText() + ",");
                        writer.write(dateOfBirthField.getText() + ",");
                        writer.write(passportCountryField.getText() + ",");
                        writer.write(passportTypeField.getText() + ",");
                        writer.write(passportNumberField.getText() + ",");
                        writer.write(countryCodeField.getText() + ",");
                        writer.write(telephoneNumberField.getText() + ",");
                        writer.write(emailField.getText() + ",");
                        writer.write(numOfBagsField.getText() + ",");
                        writer.write(weightOfBagsField.getText() + ",");
                        writer.write(disabilityField.getText() + ",");
                        writer.write(String.valueOf(ticketNum) + "\n");
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}