package io.github.zhaoyu1995.reading;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<BookInfo> bookList = new ArrayList<>();
    private MyDataBase myDataBase;
    private ListView listView;
    private InfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();//注意我的是V7包，使用getSupportActionBar()
        if (actionBar != null) {
            actionBar.hide();//隐藏actionBar
        }

        TextView back = (TextView) findViewById(R.id.back);
        if (back != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        myDataBase = new MyDataBase(this, "book_info.db", null, 1);

        //给适配器中显示进度的控件设置监听，便于更新进度
        adapter = new InfoAdapter(MainActivity.this, R.layout.show_book, bookList,
                new UpdateCallback() {
                    @Override
                    public void updateProgress(String name, int total) {
                        //获取新进度并更新数据库
                        NewPageDialog pageDialog = new NewPageDialog(MainActivity.this, R.style.dialog_style);
                        pageDialog.setCanceledOnTouchOutside(true);
                        pageDialog.setBookName(name);
                        pageDialog.setTotal(total);
                        pageDialog.show();
                        //重新显示
                        pageDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                showBookList();
                            }
                        });
                    }
                });

        listView = (ListView) findViewById(R.id.show_book);
        //长按删除
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final BookInfo book = bookList.get(i);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("警告!");
                alertDialog.setMessage("确定删除此条信息");
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase db = myDataBase.getWritableDatabase();
                        db.delete("book", "book_name = ?", new String[]{book.getBook_name()});
                        showBookList();
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.show();
                return true;
            }
        });

        //添加新的数据
        TextView add_book = (TextView) findViewById(R.id.add_book);
        if (add_book != null) {
            add_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final CustomDialog dialog = new CustomDialog(MainActivity.this, R.style.dialog_style);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            showBookList();
                        }
                    });
                }
            });
        }

        showBookList();
    }

    private void showBookList() {
        bookList.clear();

        //遍历数据库
        SQLiteDatabase db = myDataBase.getWritableDatabase();
        Cursor cursor = db.query("book", null, null, null, null, null, null);//注意是表名
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("book_name"));
                String author = cursor.getString(cursor.getColumnIndex("book_author"));
                int total_pages = cursor.getInt(cursor.getColumnIndex("book_pages"));
                int cur_pages = cursor.getInt(cursor.getColumnIndex("cur_pages"));
                BookInfo bookInfo = new BookInfo(name, "作者:\t"+author, total_pages, cur_pages);
                bookList.add(bookInfo);
            } while (cursor.moveToNext());
        }
        cursor.close();

        if (listView != null) {
            listView.setAdapter(adapter);
        }
    }


}
