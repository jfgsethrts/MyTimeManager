/*
 * ToDoListPane.java
 * ToDoリスト設定クラス
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * ToDoリスト設定1
 */
public class ToDoListPane extends JPanel {
        private JList toDoList;
        private DefaultListModel toDoListModel;
        private JTextField toDoInputField;
        private JButton addButton;
        private JButton modifyButton;
        private JButton removeButton;
public ToDoListPane() {
            super(new BorderLayout());
            // 一覧を生成
            toDoListModel = new DefaultListModel();
            toDoList = new JList(toDoListModel);
            JScrollPane listScrollPane = new JScrollPane(toDoList);
            // TODOリストにリスナを設定
            toDoList.addListSelectionListener
            (new ToDoListSelectionHandler());
            // ToDo追加用テキストフィールド
            toDoInputField = new JTextField();
            // 各ボタンの生成
            JPanel buttonPanel = new JPanel();
            addButton = new JButton("追加");
            modifyButton = new JButton("編集");
            removeButton = new JButton("削除");
            // ボタンにリスナを設定
            addButton.addActionListener
            (new AddActionHandler());
            modifyButton.addActionListener
            (new ModifyActionHandler());
            removeButton.addActionListener
            (new RemoveActionHandler());
            buttonPanel.add(addButton);
            buttonPanel.add(modifyButton);
            buttonPanel.add(removeButton);
            add(listScrollPane, BorderLayout.NORTH);
            add(toDoInputField, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
        }
        /**
         * TODOリスト選択アクション
         */
        private class ToDoListSelectionHandler
        implements ListSelectionListener {
                public void valueChanged(ListSelectionEvent arg0) {
                        if (toDoList.getSelectedIndices().
                        length!=1){
                                    return;
                        }
                        toDoInputField.setText((String)toDoList.
                        getSelectedValue());
                }
        }
        /**
         * 追加ボタンアクション
         */
        private class AddActionHandler implements ActionListener {
                @SuppressWarnings("unchecked")
                public void actionPerformed(ActionEvent e) {
                        // テキストフィールドの内容をリストに追加
                        toDoListModel.addElement
                        (toDoInputField.getText());
                }
        }
        /**
         * 編集ボタンアクション
         */
        private class ModifyActionHandler
        implements ActionListener {
                @SuppressWarnings("unchecked")
                public void actionPerformed(ActionEvent e) {
                        // テキストフィールドの内容でリストを編集
                        if (toDoList.getSelectedIndices().
                        length != 1) {
                                return;
                        }
                        toDoListModel.set(toDoList.
                        getSelectedIndex(),
                        toDoInputField.getText());
                }
        }
        /**
         * 削除ボタンアクション
         */
        private class RemoveActionHandler
        implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                        // テキストフィールドの内容をリストに追加
                        if (toDoList.getSelectedIndices().
                        length != 1) {
                                return;
                        }
                        setButtonsEnabled(false);
                        Thread removeThread = new
                        RemoveThread(toDoList.getSelectedIndex());
                        removeThread.start();
                }
        }
        /**
         * ボタンの状態を更新します
         */
        private void setButtonsEnabled(boolean enabled) {
                addButton.setEnabled(enabled);
                modifyButton.setEnabled(enabled);
                removeButton.setEnabled(enabled);
        }
        /**
         * 削除処理を行うクラス
         */
        class RemoveThread extends Thread {
                int index;
                RemoveThread(int index) {
                        this.index = index;
                }
                public void run() {
                        // 時間のかかる処理を実行
                        doLongTask();
                        //ボタンを変更
                        SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                        toDoListModel.
                                        remove(index);
                                        setButtonsEnabled(true);
                                }
                        });
                }
        }
        /**
         *　時間のかかる処理
         */
        private void doLongTask() {
                try {
                        Thread.sleep(10000);
                } catch (InterruptedException ex) {
                }
        }
}