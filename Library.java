package LibraryManagementSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book b) {
        // kiểm tra ISBN trùng
        if (findBook(b.getIsbn()) == null) {
            books.add(b);
        } else {
            // nếu muốn: cập nhật thông tin
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book findBook(String isbn) {
        if (isbn == null) return null;
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn.trim())) return b;
        }
        return null;
    }

    public void removeBook(String isbn) {
        books.removeIf(b -> b.getIsbn().equalsIgnoreCase(isbn.trim()));
    }

    public boolean borrowBook(String isbn, String borrowerName) {
        Book b = findBook(isbn);
        if (b == null) return false;
        if (b.isBorrowed()) return false;
        b.borrow(borrowerName);
        return true;
    }

    // trả sách và trả về phí
    public long returnBookAndGetFee(String isbn) {
        Book b = findBook(isbn);
        if (b == null) return -1; // không tồn tại
        if (!b.isBorrowed()) return 0; // chưa mượn
        return b.returnAndComputeFee();
    }

    public List<Book> listByAuthor(String author) {
        return books.stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author.trim()))
                .collect(Collectors.toList());
    }

    public List<Book> listByYear(int year) {
        return books.stream()
                .filter(b -> b.getYear() == year)
                .collect(Collectors.toList());
    }

    public void setBooks(ArrayList<Book> list) {
        this.books = list;
    }
}

