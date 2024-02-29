package sample.library;

public class BorrowBooks {

    private int numberOfPage;
    private String category;
    private int yearOfPublication;
    private String bookName;
    private String authorName;
    private int userId;
    private String username;
    private boolean status;

    public BorrowBooks(int numberOfPage, String category, int yearOfPublication, String bookName, String authorName, int userId, String username, boolean status)
    {
        this.numberOfPage = numberOfPage;
        this.category = category;
        this.yearOfPublication = yearOfPublication;
        this.bookName = bookName;
        this.authorName = authorName;
        this.userId = userId;
        this.username = username;
        this.status = status;
    }

    public int getNumberOfPage(){ return numberOfPage; }
    public void setNumberOfPage(int numberOfPage) { this.numberOfPage = numberOfPage; }

    public String getCategory(){ return category; }
    public void setCategory(String category) { this.category = category; }

    public int getYearOfPublication(){ return yearOfPublication; }
    public void setYearOfPublication(int yearOfPublication) { this.yearOfPublication = yearOfPublication; }

    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public boolean getStatus(){ return status; }
    public void setStatus(boolean status) { this.status = status; }


}
