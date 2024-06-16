/**
 * ToDO.java
 * ToDo作成クラス
 */

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class ToDo {
        ToDo(JPanel todoPanel){
            System.out.println("main : "
                        + SwingUtilities.isEventDispatchThread());
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                createAndShowTodoList(todoPanel);
                        }
                });
        }
        /**
         * ToDoリストの生成と表示。
         */
        private static void createAndShowTodoList(JPanel todoPanel) {
                JFrame mainFrame = new JFrame();
                System.out.println("createAndShowTodoList :"
                                + SwingUtilities.
                                isEventDispatchThread());
                mainFrame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
                Container contentPane =
                mainFrame.getContentPane();
                // ToDoリストを生成
                JComponent newContentPane = new ToDoListPane();
                contentPane.add(newContentPane,
                BorderLayout.CENTER);
                // Windowサイズを調整
                mainFrame.pack();
                // 表示
                mainFrame.setVisible(true);
          }
}