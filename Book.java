package LibraryManagementSystem;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book implements Serializable {
    private String isbn;
    private String title;
    private String author;
    private int year;

    // cho tính năng mượn/trả
    private boolean borrowed;
    private String borrowerName;
    private long borrowTimeMillis; // thời gian mượn (ms)

    // phí: miễn phí trong 14 ngày, sau đó 5000 VND/ngày
    public static final int FREE_DAYS = 14;
    public static final long MILLIS_PER_DAY = 24L * 60 * 60 * 1000;
    public static final long FEE_PER_DAY = 5000L;

    public Book(String isbn, String title, String author, int year) {
        this.isbn = isbn.trim();
        this.title = title.trim();
        this.author = author.trim();
        this.year = year;
        this.borrowed = false;
        this.borrowerName = "";
        this.borrowTimeMillis = 0;
    }

    // getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isBorrowed() { return borrowed; }
    public String getBorrowerName() { return borrowerName; }
    public long getBorrowTimeMillis() { return borrowTimeMillis; }

    // borrow
    public void borrow(String borrowerName) {
        this.borrowed = true;
        this.borrowerName = borrowerName;
        this.borrowTimeMillis = System.currentTimeMillis();
    }

    // return and compute fee (in VND)
    public long returnAndComputeFee() {
        if (!borrowed) return 0;
        long now = System.currentTimeMillis();
        long days = (now - borrowTimeMillis) / MILLIS_PER_DAY;
        long overdueDays = days - FREE_DAYS;
        long fee = (overdueDays > 0) ? overdueDays * FEE_PER_DAY : 0;
        // reset
        this.borrowed = false;
        this.borrowerName = "";
        this.borrowTimeMillis = 0;
        return fee;
    }

    public String borrowInfoString() {
        if (!borrowed) return "Không đang mượn";
        Date d = new Date(borrowTimeMillis);
        String s = new SimpleDateFormat("yyyy-MM-dd").format(d);
        return "Mượn bởi: " + borrowerName + " - ngày: " + s;
    }

    @Override
    public String toString() {
        String base = isbn + " | " + title + " | " + author + " | " + year;
        if (borrowed) base += " | [ĐANG MƯỢN] " + borrowerName;
        return base;
    }
}

