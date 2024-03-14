package ConfirmMail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Confirm {
    public JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private Connection connection;
    private PreparedStatement statement;
    private PreparedStatement statement2;


    public Confirm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendNotificationToCustomer();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Order Confirmation");
            frame.setContentPane(new Confirm().panel1);
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void sendMail(String customerMail, int orderID, int date) {
        // Recipient's email ID needs to be mentioned.
        String to = customerMail;

        // Sender's email ID needs to be mentioned
        String from = "anrdspro@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "shczrfhwjjwxsmfq");
            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Car Care Order status update !");

            message.setContent("<h2>Your Order has been completed!</h2>"
                            + String.format("<ul><li>Order ID : %d </li>", orderID)
                            + String.format("<li>Order Date : %d </li></ul>", date)
                            + "<h3>Please contact Car Care to get more details..</h3>",
                    "text/html");

            // Send message
            Transport.send(message);
            JOptionPane.showMessageDialog(panel1, "Message Sent Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void getMail() {
        // ... (method implementation)
    }

    public void dbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
            System.out.println("Connected to DB");
            statement2 = connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = statement2.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void SendNotificationToCustomer() {
        String customerMail;
        int orderID = Integer.parseInt(textField1.getText());
        int date = 2034478638;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
            System.out.println("Connected to DB");
            statement2 = connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = statement2.executeQuery();

            while (resultSet.next()) {
                // Retrieve data from the result set
                int orderID2 = Integer.parseInt(resultSet.getString("orderno"));
                String customerMail2 = resultSet.getString("name");

                if (orderID == orderID2) {
                    customerMail = customerMail2;
                    sendMail(customerMail, orderID, date);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
