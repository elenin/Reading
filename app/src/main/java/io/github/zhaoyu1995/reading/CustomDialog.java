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


class CustomDialog extends Dialog {
    private Context context;
    private MyDataBase myDataBase;

    CustomDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this.context).inflate(R.layout.add_dialog, null);

        super.setContentView(view);

        final EditText book_name = (EditText) view.findViewById(R.id.new_book_name);
        final EditText book_author = (EditText) view.findViewById(R.id.new_book_author);
        final EditText total_pages = (EditText) view.findViewById(R.id.new_total_pages);

        myDataBase = new MyDataBase(context, "book_info.db", null, 1);


        Button add_info = (Button) view.findViewById(R.id.add_book);
        add_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDataBase.getWritableDatabase();
                String name = book_name.getText().toString();
                String author = book_author.getText().toString();
                String pages = total_pages.getText().toString();
                if (name.equals("") || author.equals("") || pages.equals("")) {
                    Toast.makeText(context, "信息不完整", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put("book_name", name);
                    values.put("book_author", author);
                    values.put("book_pages", Integer.valueOf(pages));
                    values.put("cur_pages", 0);
                    db.insert("book", null, values);
                    dismiss();
                }
            }
        });
    }

}
