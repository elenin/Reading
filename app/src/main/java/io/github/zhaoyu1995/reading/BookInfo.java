package io.github.zhaoyu1995.reading;

public class BookInfo {
    private String book_name;//书名
    private String book_author;//作者
    private int total_pages;//总页数
    private int current_page;//当前阅读进度

    public BookInfo(String book_name, String book_author, int total_pages, int current_page) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.total_pages = total_pages;
        this.current_page = current_page;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }
}
