package io.github.zhaoyu1995.reading;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<BookInfo> bookList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();//注意我的是V7包，使用getSupportActionBar()
        if (actionBar != null) {
            actionBar.hide();//隐藏actionBar
        }
        init();
        InfoAdapter adapter = new InfoAdapter(MainActivity.this, R.layout.show_book, bookList);
        ListView listView = (ListView) findViewById(R.id.show_book);
        listView.setAdapter(adapter);


    }
    private void init() {
        BookInfo bookInfo = new BookInfo("不该存在的技术1", "作者", 1000, 10);
        bookList.add(bookInfo);

        bookInfo = new BookInfo("不该存在的技术2", "作者",  1000, 10);
        bookList.add(bookInfo);

        bookInfo = new BookInfo("不该存在的技术3", "作者", 1000, 990);
        bookList.add(bookInfo);

        bookInfo = new BookInfo("不该存在的技术2", "作者",  1000, 220);
        bookList.add(bookInfo);

        bookInfo = new BookInfo("不该存在的技术3", "作者", 100, 10);
        bookList.add(bookInfo);


        bookInfo = new BookInfo("不该存在的技术2", "作者",  100, 10);
        bookList.add(bookInfo);

        bookInfo = new BookInfo("不该存在的技术3", "作者", 10000, 1000);
        bookList.add(bookInfo);

        bookInfo = new BookInfo("不该存在的技术2", "作者",  1000, 10);
        bookList.add(bookInfo);

        bookInfo = new BookInfo("不该存在的技术3", "作者", 1000, 10);
        bookList.add(bookInfo);

    }
}
