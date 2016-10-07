package io.github.zhaoyu1995.reading;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

class NewPageDialog extends Dialog {
    private Context context;
    private MyDataBase myDataBase;
    private String book_name;
    private int total_pages;

    NewPageDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this.context).inflate(R.layout.new_page, null);
        super.setContentView(view);
        myDataBase = new MyDataBase(context, "book_info.db", null, 1);

        final EditText new_progress = (EditText) view.findViewById(R.id.new_pages);

        Button update = (Button) view.findViewById(R.id.update_progress);
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (new_progress.getText().toString().equals("")) {
                    Toast.makeText(context, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(new_progress.getText().toString()) > total_pages) {
                    Toast.makeText(context, "超过总页数", Toast.LENGTH_SHORT).show();
                }
                else {
                    int new_pages = Integer.valueOf(new_progress.getText().toString());
                    SQLiteDatabase db = myDataBase.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("cur_pages", new_pages);
                    db.update("book", values, "book_name = ?", new String[]{book_name});
                    dismiss();
                }
            }
        });
    }

    void setBookName(String name) {
        this.book_name = name;
    }
    void setTotal(int total) {
        this.total_pages = total;
    }
}
