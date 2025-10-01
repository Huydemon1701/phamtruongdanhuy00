package LibraryManagementSystem;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);

            LibraryApp app = new LibraryApp();
            JPanel mainPanel = app.getMainPanel();

            // màu nền giống mẫu
            frame.getContentPane().setBackground(new Color(230,240,250));
            frame.add(mainPanel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

