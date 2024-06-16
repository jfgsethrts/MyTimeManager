import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Recorder extends JFrame {
    private JTextField TimeField;
    private JButton saveButton;
    private Properties properties;
    private String propertiesFilePath = "study_times.properties";

    public Recorder() {
        saveButton.addActionListener(this::save);
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save(ActionEvent event) {
        String Time = TimeField.getText();
        String currentDate = String.valueOf(System.currentTimeMillis());
        properties.setProperty(currentDate, Time);

        try {
            properties.store(new FileOutputStream(propertiesFilePath), null);
            JOptionPane.showMessageDialog(this, "保存されました。");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "エラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Recorder recorder = new Recorder();
            recorder.setVisible(true);
        });
    }
}

