
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;


public class AirportGUI extends JFrame {

    private Map<String, Airport> airportMap;
    private ArrayList<Flight> bclArrivals;
    private Timer timer;

    private JPanel mapPanel;
    private JLabel[] airportLabels;
    private JTextArea infoArea;

    public JPanel createAirPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(this.getContentPane());
        return mainPanel;
    }

    public AirportGUI() {
        super("Airport Map");

        // Initialize airport map and BCL arrivals list
        airportMap = new HashMap<String, Airport>();
        bclArrivals = new ArrayList<Flight>();

        loadAirportData();

        // Create map panel with airport labels
        mapPanel = new JPanel();
        mapPanel.setLayout(null);

        airportLabels = new JLabel[airportMap.size()];
        int i = 0;
        for (Map.Entry<String, Airport> entry : airportMap.entrySet()) {
            Airport airport = entry.getValue();
            JLabel label = new JLabel(airport.getName());
            label.setBounds(airport.getX(), airport.getY(), 100, 20);
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        showArrivals(airport);
                    } else if (e.getClickCount() == 2) {
                        showDelayedArrivals(airport);
                    }
                }
            });
            airportLabels[i] = label;
            mapPanel.add(label);
            i++;
        }

        // Load map image and add to map panel
        ImageIcon mapIcon = new ImageIcon("map.jpg");
        JLabel mapLabel = new JLabel(mapIcon);
        mapLabel.setBounds(0, 0, mapIcon.getIconWidth(), mapIcon.getIconHeight());
        mapPanel.add(mapLabel);

        infoArea = new JTextArea(5,20);
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(10, 10, 300, 200);
        add(scrollPane);

        timer = new Timer(300000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBCLArrivals();
            }
        });
        timer.start();

        setSize(1200, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(mapPanel, BorderLayout.CENTER);
        getContentPane().add(scrollPane, BorderLayout.WEST);
    }

    private void loadAirportData() {
        // Create sample airports and add to the airportMap
        Airport airportA = new Airport("AAA", 100, 100);
        Airport airportB = new Airport("BBB", 200, 200);
        Airport airportC = new Airport("CCC", 300, 300);
        Airport airportD = new Airport("DDD", 400, 400);
        Airport airportE = new Airport("BCL", 500, 500);

        airportMap.put("AAA", airportA);
        airportMap.put("BBB", airportB);
        airportMap.put("CCC", airportC);
        airportMap.put("DDD", airportD);
        airportMap.put("BCL", airportE);

        // Create sample flights and add to the airports
        Flight flight1 = new Flight("F001", airportA, airportB, new Date(), 0);
        Flight flight2 = new Flight("F002", airportB, airportC, new Date(), 15);
        Flight flight3 = new Flight("F003", airportC, airportD, new Date(), 45);
        Flight flight4 = new Flight("F004", airportD, airportE, new Date(), 30);
        airportA.addArrival(flight1);
        airportB.addArrival(flight2);
        airportC.addArrival(flight3);
        airportD.addArrival(flight4);
    }


    private void showArrivals(Airport airport) {
        String arrivals = "Arrivals at " + airport.getName() + ":\n";
        for (Flight flight : airport.getArrivals()) {
            arrivals += flight.toString() + "\n";
        }
        infoArea.setText(arrivals);
    }


    private void showDelayedArrivals(Airport airport) {
        String delays = "Delayed Arrivals at " + airport.getName() + ":\n";
        for (Flight flight : airport.getArrivals()) {
            if (flight.getDelay() >= 30) {
                delays += flight.toString() + "\n";
            }
        }
        infoArea.setText(delays);
    }


    private void updateBCLArrivals() {
        Date now = new Date();
        bclArrivals.clear();
        for (Flight flight : airportMap.get("BCL").getArrivals()) {
            if (flight.getArrivalTime().after(now)) {
                bclArrivals.add(flight);
            }
        }
        String arrivals = "Upcoming BCL Arrivals:\n";
        for (Flight flight : bclArrivals) {
            if (flight.getArrivalTime().getTime() - now.getTime() <= 1800000) {
                arrivals += "<html><font color='red'>" + flight.toString() + "</font></html>\n";
            } else {
                arrivals += flight.toString() + "\n";
            }
        }
        infoArea.setText(arrivals);
    }

    public static void main(String[] args) {
        AirportGUI airportGUI = new AirportGUI();
        airportGUI.setVisible(true);
    }
}

class Airport {
    private String name;
    private int x;
    private int y;
    private ArrayList<Flight> arrivals;

    public Airport(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.arrivals = new ArrayList<Flight>();
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Flight> getArrivals() {
        return arrivals;
    }

    public void addArrival(Flight flight) {
        arrivals.add(flight);
    }
}

class Flight {
    private String flightNumber;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Date originalArrivalTime;
    private int delay;

    public Flight(String flightNumber, Airport departureAirport, Airport arrivalAirport, Date originalArrivalTime, int delay) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.originalArrivalTime = originalArrivalTime;
        this.delay = delay;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public Date getOriginalArrivalTime() {
        return originalArrivalTime;
    }

    public int getDelay() {
        return delay;
    }

    public Date getArrivalTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(originalArrivalTime);
        cal.add(Calendar.MINUTE, delay);
        return cal.getTime();
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return flightNumber + " from " + departureAirport.getName() + " to " + arrivalAirport.getName()
                + " - Original Arrival: " + sdf.format(originalArrivalTime)
                + " - Delayed Arrival: " + sdf.format(getArrivalTime())
                + " - Delay: " + delay + " minutes";
    }
}
