package LibraryManagementSystem;
import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    JButton addBtn = new JButton("Thêm Sách");
    JButton displayBtn = new JButton("Hiển Thị");
    JButton borrowBtn = new JButton("Mượn Sách");
    JButton returnBtn = new JButton("Trả & Tính Phí");
    JButton findBtn = new JButton("Tìm Sách");
    JButton removeBtn = new JButton("Xóa Sách");
    JButton listBtn = new JButton("Liệt Kê Theo Tác Giả");
    JButton saveBtn = new JButton("Lưu File");
    JButton loadBtn = new JButton("Mở File");

    public MenuPanel() {
        // màu và font
        Font f = new Font("Arial", Font.BOLD, 12);
        for (JButton b : new JButton[]{addBtn, displayBtn, borrowBtn, returnBtn, findBtn, removeBtn, listBtn, saveBtn, loadBtn}) {
            b.setFont(f);
            b.setFocusPainted(false);
        }

        addBtn.setBackground(new Color(100,149,237)); addBtn.setForeground(Color.WHITE);
        displayBtn.setBackground(new Color(60,179,113)); displayBtn.setForeground(Color.WHITE);
        borrowBtn.setBackground(new Color(70,130,180)); borrowBtn.setForeground(Color.WHITE);
        returnBtn.setBackground(new Color(255,165,0)); returnBtn.setForeground(Color.BLACK);
        findBtn.setBackground(new Color(186,85,211)); findBtn.setForeground(Color.WHITE);
        removeBtn.setBackground(new Color(220,20,60)); removeBtn.setForeground(Color.WHITE);
        listBtn.setBackground(new Color(100,149,237)); listBtn.setForeground(Color.WHITE);
        saveBtn.setBackground(new Color(34,139,34)); saveBtn.setForeground(Color.WHITE);
        loadBtn.setBackground(new Color(112,128,144)); loadBtn.setForeground(Color.WHITE);

        // nền panel
        setBackground(new Color(230,240,250));

        // sắp xếp dạng lưới: 3 hàng, 3 cột
        setLayout(new GridLayout(3, 3, 10, 10));

        add(addBtn);
        add(displayBtn);
        add(borrowBtn);
        add(returnBtn);
        add(findBtn);
        add(removeBtn);
        add(listBtn);
        add(saveBtn);
        add(loadBtn);
    }
}
