import javax.swing.SwingUtilities;

public class Main extends Object{
  /**
   * エントリポイント
   * 
   * @param args
   */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyTimeManager manager = new MyTimeManager();
            manager.setVisible(true);
        });
    } 
}
