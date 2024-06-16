/**
 * MyTimeManager.java
 * 画面設定クラス
 */

import javax.swing.*;
import java.awt.BorderLayout;


public class MyTimeManager extends JFrame {

    public MyTimeManager() {
        createUI();
    }

    private void createUI() {
        setTitle("my時間アプリ");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ホーム画面のタブ
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel homePanel = new JPanel(new BorderLayout());
        JPanel planPanel = new JPanel(new BorderLayout());
        JPanel todoPanel = new JPanel(new BorderLayout());

        tabbedPane.addTab("ホーム", homePanel);
        tabbedPane.addTab("カレンダー", planPanel);
        tabbedPane.addTab("ToDo", todoPanel);

        // 各ページのコンテンツを追加
        addHomeContent(homePanel);
        addPlanContent(planPanel);
        addToDoContent(todoPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }
    
        // ホーム画面
    private void addHomeContent(JPanel homePanel) {
        new AnalogClock(homePanel);
    }

        // カレンダーページ
    private void addPlanContent(JPanel planPanel) {
        new CalendarApp(planPanel);
    }
    // ToDoページ
    private void addToDoContent(JPanel todoPanel) {
        new ToDo(todoPanel);
    }
}