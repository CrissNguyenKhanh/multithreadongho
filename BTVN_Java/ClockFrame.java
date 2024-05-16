import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JLabel;

class ClockFrame extends JFrame {
    public ClockFrame(String formattedTime, String timezone) {
        setTitle("Clock for Timezone: " + timezone);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel clockLabel = new JLabel();
        add(clockLabel);

        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            while (true) {
                Date now = new Date();
                clockLabel.setText(formattedTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        setVisible(true);
    }
}