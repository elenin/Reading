package io.github.zhaoyu1995.reading;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class InfoAdapter extends ArrayAdapter<BookInfo> {
    private int resource_id;
    public InfoAdapter(Context context, int resource, List<BookInfo> objects) {
        super(context, resource, objects);
        resource_id = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookInfo bookInfo = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (null == convertView) {
            view = LayoutInflater.from(getContext()).inflate(resource_id, null);
            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.book_name);
            viewHolder.author = (TextView) view.findViewById(R.id.book_author);
            viewHolder.customView = (CustomView) view.findViewById(R.id.custom_view);
            viewHolder.progress = (TextView) view.findViewById(R.id.progress_info);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }



        viewHolder.name.setText(bookInfo.getBook_name());
        viewHolder.author.setText(bookInfo.getBook_author());
        viewHolder.customView.drawProgress(bookInfo.getTotal_pages(), bookInfo.getCurrent_page());
        viewHolder.progress.setText(bookInfo.getCurrent_page()+"/"+bookInfo.getTotal_pages()+"页");

        return view;
    }

    class ViewHolder {
        private TextView name;//书名
        private TextView author;//作者
        private CustomView customView;
        private TextView progress;//当前阅读进度
    }
}
