
import java.awt.BorderLayout;

import javax.swing.*;

public class MainApplication {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Integrated Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);


        JTabbedPane tabbedPane = new JTabbedPane();



        UiDesign  ui = new UiDesign();
        JPanel uiPanel = ui.createUiPanel();
        tabbedPane.addTab("Flights", uiPanel);



        Shop shop = new Shop();
        JPanel shopsPanel = shop.createShopPanel();
        tabbedPane.addTab("Shops", shopsPanel);

        AirportGUI air = new AirportGUI();
        JPanel airPanel = air.createAirPanel();
        tabbedPane.addTab("Map", airPanel);



        Restaurant res = new Restaurant();
        JPanel restaurantsPanel = res.createRestaurantPanel();
        tabbedPane.addTab("Restaurant", restaurantsPanel);



        Passenger passenger = new Passenger();
        JPanel passengersPanel = passenger.createPassengerPanel();
        tabbedPane.addTab("Passengers", passengersPanel);


        frame.add(tabbedPane);


        frame.setVisible(true);
    }
}





