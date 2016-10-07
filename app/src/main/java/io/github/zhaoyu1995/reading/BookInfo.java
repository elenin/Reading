package io.github.zhaoyu1995.reading;

class BookInfo {
    private String book_name;//书名
    private String book_author;//作者
    private int total_pages;//总页数
    private int current_page;//当前阅读进度

    BookInfo(String book_name, String book_author, int total_pages, int current_page) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.total_pages = total_pages;
        this.current_page = current_page;
    }

    public String getBook_name() {
        return book_name;
    }

    String getBook_author() {
        return book_author;
    }

    int getTotal_pages() {
        return total_pages;
    }

    int getCurrent_page() {
        return current_page;
    }

}
