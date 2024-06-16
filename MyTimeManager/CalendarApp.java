/**
 *  CalendarApp.java
 *  カレンダークラス
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalendarApp extends JFrame {
    private YearMonth currentYearMonth;
    private Map<LocalDate, String> events;
    private JTextArea eventTextArea;
    private JTextField TimeField;
    private Properties properties;
    private String propertiesFilePath = "times.properties";

    public CalendarApp(JPanel planPanel) {
        super("Calendar Display");
        this.currentYearMonth = YearMonth.now();
        this.events = new HashMap<>();

        this.setLayout(new BorderLayout());

        // カレンダーパネルを作成
        JPanel calendarPanel = new JPanel(new GridLayout(0, 7));
        this.add(calendarPanel, BorderLayout.CENTER);

        // イベント表示エリアを作成
        eventTextArea = new JTextArea(5, 40);
        eventTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(eventTextArea);
        this.add(scrollPane, BorderLayout.SOUTH);

        updateCalendar(calendarPanel, planPanel);
        
    }

    private void updateCalendar(JPanel calendarPanel, JPanel planPanel) {
        calendarPanel.removeAll();

        // 曜日のヘッダーを追加
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek) {
            calendarPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        // 日付を追加
        LocalDate calendarDate = currentYearMonth.atDay(1);
        int dayOfWeekOfFirstDay = calendarDate.getDayOfWeek().getValue() % 7;
        for (int i = 0; i < dayOfWeekOfFirstDay; i++) {
            calendarPanel.add(new JLabel(""));
        }
        while (calendarDate.getMonth() == currentYearMonth.getMonth()) {
            JButton dateButton = new JButton(Integer.toString(calendarDate.getDayOfMonth()));
            LocalDate finalCalendarDate = calendarDate;
            dateButton.addActionListener(e -> addEvent(finalCalendarDate));
            calendarPanel.add(dateButton);
            calendarDate = calendarDate.plusDays(1);
            dateButton.addActionListener(this::save);
            loadProperties();
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
        planPanel.add(calendarPanel);
        
    }

    private void addEvent(LocalDate date) {
        // イベント追加のためのダイアログを表示
        String event = JOptionPane.showInputDialog(this, "Enter event for " + date);
        if (event != null && !event.trim().isEmpty()) {
            events.put(date, event);
            updateEventTextArea();
        }
    }

    private void updateEventTextArea() {
        eventTextArea.setText("");
        events.forEach((date, event) -> eventTextArea.append(date + ": " + event + "\n"));
    }


    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ファイルの読み書き
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
}
