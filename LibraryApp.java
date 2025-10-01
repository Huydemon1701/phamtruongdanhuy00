package LibraryManagementSystem;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class LibraryApp {
    private Library library = new Library();
    private JTextArea displayArea = new JTextArea();
    private MenuPanel menu = new MenuPanel();

    public JPanel getMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Input fields
        JTextField isbnField = new JTextField(8);
        JTextField titleField = new JTextField(12);
        JTextField authorField = new JTextField(10);
        JTextField yearField = new JTextField(5);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(245,245,245));
        inputPanel.add(new JLabel("ISBN:")); inputPanel.add(isbnField);
        inputPanel.add(new JLabel("Tên:")); inputPanel.add(titleField);
        inputPanel.add(new JLabel("Tác giả:")); inputPanel.add(authorField);
        inputPanel.add(new JLabel("Năm:")); inputPanel.add(yearField);

        displayArea.setEditable(false);
        displayArea.setBackground(Color.WHITE);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Add action listeners
        menu.addBtn.addActionListener(e -> {
            try {
                String isbn = isbnField.getText().trim();
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                int year = Integer.parseInt(yearField.getText().trim());
                if (isbn.isEmpty() || title.isEmpty() || author.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Điền đủ ISBN/Tên/Tác giả");
                    return;
                }
                Book b = new Book(isbn, title, author, year);
                library.addBook(b);
                JOptionPane.showMessageDialog(mainPanel, "Đã thêm sách!");
                // clear
                isbnField.setText(""); titleField.setText(""); authorField.setText(""); yearField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Năm phải là số!");
            }
        });

        menu.displayBtn.addActionListener(e -> {
            displayArea.setText("");
            for (Book b : library.getAllBooks()) {
                displayArea.append(b.toString() + "\n");
            }
        });

        menu.borrowBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog(mainPanel, "Nhập ISBN cần mượn:");
            if (isbn == null) return;
            Book b = library.findBook(isbn.trim());
            if (b == null) { JOptionPane.showMessageDialog(mainPanel, "Không tìm thấy sách!"); return; }
            if (b.isBorrowed()) { JOptionPane.showMessageDialog(mainPanel, "Sách đang được mượn!"); return; }
            String borrower = JOptionPane.showInputDialog(mainPanel, "Tên người mượn:");
            if (borrower == null || borrower.trim().isEmpty()) { JOptionPane.showMessageDialog(mainPanel, "Tên người mượn không hợp lệ"); return; }
            library.borrowBook(isbn.trim(), borrower.trim());
            JOptionPane.showMessageDialog(mainPanel, "Đã mượn sách thành công!");
        });

        menu.returnBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog(mainPanel, "Nhập ISBN trả sách:");
            if (isbn == null) return;
            Book b = library.findBook(isbn.trim());
            if (b == null) { JOptionPane.showMessageDialog(mainPanel, "Không tìm thấy sách!"); return; }
            if (!b.isBorrowed()) { JOptionPane.showMessageDialog(mainPanel, "Sách chưa được mượn!"); return; }
            long fee = library.returnBookAndGetFee(isbn.trim());
            if (fee < 0) JOptionPane.showMessageDialog(mainPanel, "Lỗi trả sách");
            else {
                String msg = "Trả sách thành công! Phí (nếu có): " + fee + " VND";
                JOptionPane.showMessageDialog(mainPanel, msg);
            }
        });

        menu.findBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog(mainPanel, "Nhập ISBN để tìm:");
            if (isbn == null) return;
            Book b = library.findBook(isbn.trim());
            if (b == null) JOptionPane.showMessageDialog(mainPanel, "Không tìm thấy!");
            else JOptionPane.showMessageDialog(mainPanel, "Tìm thấy:\n" + b + "\n" + b.borrowInfoString());
        });

        menu.removeBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog(mainPanel, "Nhập ISBN để xóa:");
            if (isbn == null) return;
            library.removeBook(isbn.trim());
            JOptionPane.showMessageDialog(mainPanel, "Đã xóa (nếu có)!");
        });

        menu.listBtn.addActionListener(e -> {
            String author = JOptionPane.showInputDialog(mainPanel, "Nhập tên tác giả để liệt kê:");
            if (author == null) return;
            List<Book> list = library.listByAuthor(author.trim());
            displayArea.setText("");
            if (list.isEmpty()) displayArea.append("Không có sách của tác giả: " + author + "\n");
            else for (Book b : list) displayArea.append(b.toString() + "\n");
        });

        menu.saveBtn.addActionListener(e -> {
            try {
                FileHandler.saveLibrary(library, "library.dat");
                JOptionPane.showMessageDialog(mainPanel, "Đã lưu file library.dat");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Lỗi khi lưu: " + ex.getMessage());
            }
        });

        menu.loadBtn.addActionListener(e -> {
            try {
                library.setBooks(FileHandler.loadLibrary("library.dat"));
                JOptionPane.showMessageDialog(mainPanel, "Đã mở file library.dat");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainPanel, "Không thể mở file: " + ex.getMessage());
            }
        });

        // layout
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        mainPanel.add(menu, BorderLayout.SOUTH);
        return mainPanel;
    }
}
