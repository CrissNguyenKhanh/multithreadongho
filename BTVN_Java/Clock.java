import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Clock extends JFrame {
    private JLabel clockLabel;
    private JTextField timezoneField;
    private JButton createClockButton;

    public Clock() {
        setTitle("Continuous Running Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
        getContentPane().setLayout(null);

        clockLabel = new JLabel();
        clockLabel.setBounds(230, 1, 198, 100);
        getContentPane().add(clockLabel);

        timezoneField = new JTextField(10);
        timezoneField.setBounds(131, 86, 100, 34);
        getContentPane().add(timezoneField);

        createClockButton = new JButton("Create Clock");
        createClockButton.setBounds(270, 86, 110, 34);
        createClockButton.addActionListener(e -> createNewClock());
        getContentPane().add(createClockButton);

        setVisible(true);

        // Start the clock
        startClock();
    }

    private void startClock() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                Date now = new Date();
                clockLabel.setText(sdf.format(now));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    private void createNewClock() {
        String timezone = timezoneField.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        try {
            Date time = sdf.parse(timezone);
            sdf.setTimeZone(TimeZone.getTimeZone(timezone));
            String formattedTime = sdf.format(time);
            ClockFrame clockFrame = new ClockFrame(formattedTime, timezone);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid time format. Please enter time in HH:mm:ss format.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Clock());
    }
}




