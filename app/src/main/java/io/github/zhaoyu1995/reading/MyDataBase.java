package io.github.zhaoyu1995.reading;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


class MyDataBase extends SQLiteOpenHelper {
    private Context context;
    MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREAT_TABLE = "create table book (id integer primary key autoincrement, " +
                "book_name text, book_author text, book_pages integer, cur_pages integer)";

        sqLiteDatabase.execSQL(CREAT_TABLE);
        Toast.makeText(context, "初始化成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
