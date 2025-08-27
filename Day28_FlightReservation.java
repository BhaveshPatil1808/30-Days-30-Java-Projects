import java.sql.*;
import java.util.Scanner;

public class Day28_FlightReservation {
    private static final String URL = "jdbc:mysql://localhost:3306/flightDB";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password"; // change accordingly

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            int choice;
            do {
                System.out.println("\n===== ✈️ Flight Reservation System =====");
                System.out.println("1. View Available Flights");
                System.out.println("2. Book a Flight");
                System.out.println("3. View My Bookings");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> viewFlights(conn);
                    case 2 -> bookFlight(conn);
                    case 3 -> viewBookings(conn);
                    case 4 -> System.out.println("👋 Exiting system. Thank you!");
                    default -> System.out.println("⚠️ Invalid choice!");
                }
            } while (choice != 4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewFlights(Connection conn) throws SQLException {
        String sql = "SELECT * FROM flights";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("\n--- Available Flights ---");
        while (rs.next()) {
            System.out.println("Flight ID: " + rs.getInt("id") +
                    " | From: " + rs.getString("source") +
                    " | To: " + rs.getString("destination") +
                    " | Seats: " + rs.getInt("seats"));
        }
    }

    private static void bookFlight(Connection conn) throws SQLException {
        System.out.print("Enter Flight ID to book: ");
        int flightId = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        // Check seat availability
        String checkSql = "SELECT seats FROM flights WHERE id=?";
        PreparedStatement psCheck = conn.prepareStatement(checkSql);
        psCheck.setInt(1, flightId);
        ResultSet rs = psCheck.executeQuery();

        if (rs.next()) {
            int seats = rs.getInt("seats");
            if (seats > 0) {
                // Book the ticket
                String insertSql = "INSERT INTO bookings (flight_id, passenger_name) VALUES (?, ?)";
                PreparedStatement psInsert = conn.prepareStatement(insertSql);
                psInsert.setInt(1, flightId);
                psInsert.setString(2, name);
                psInsert.executeUpdate();

                // Update seats
                String updateSql = "UPDATE flights SET seats = seats - 1 WHERE id=?";
                PreparedStatement psUpdate = conn.prepareStatement(updateSql);
                psUpdate.setInt(1, flightId);
                psUpdate.executeUpdate();

                System.out.println("✅ Flight booked successfully!");
            } else {
                System.out.println("❌ No seats available on this flight!");
            }
        } else {
            System.out.println("❌ Flight not found!");
        }
    }

    private static void viewBookings(Connection conn) throws SQLException {
        System.out.print("Enter your name to view bookings: ");
        sc.nextLine(); // consume newline
        String name = sc.nextLine();

        String sql = "SELECT b.id, f.source, f.destination FROM bookings b JOIN flights f ON b.flight_id = f.id WHERE passenger_name=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Your Bookings ---");
        boolean hasBooking = false;
        while (rs.next()) {
            hasBooking = true;
            System.out.println("Booking ID: " + rs.getInt("id") +
                    " | From: " + rs.getString("source") +
                    " | To: " + rs.getString("destination"));
        }
        if (!hasBooking) {
            System.out.println("⚠️ No bookings found for " + name);
        }
    }
}
