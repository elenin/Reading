package io.github.zhaoyu1995.reading;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


class InfoAdapter extends ArrayAdapter<BookInfo> {
    private int resource_id;
    private UpdateCallback callback;

    InfoAdapter(Context context, int resource, List<BookInfo> objects, UpdateCallback callback) {
        super(context, resource, objects);
        resource_id = resource;
        this.callback = callback;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final BookInfo bookInfo = getItem(position);
        View view;
        final ViewHolder viewHolder;

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

        if (bookInfo != null) {
            viewHolder.name.setText(bookInfo.getBook_name());

            viewHolder.author.setText(bookInfo.getBook_author());
            viewHolder.customView.drawProgress(bookInfo.getTotal_pages(), bookInfo.getCurrent_page());
            viewHolder.progress.setText(bookInfo.getCurrent_page()+"/"+bookInfo.getTotal_pages()+"页");
            viewHolder.progress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.updateProgress(viewHolder.name.getText().toString(), bookInfo.getTotal_pages());
                }
            });
        }

        return view;
    }

    private class ViewHolder {
        private TextView name;//书名
        private TextView author;//作者
        private CustomView customView;
        private TextView progress;//当前阅读进度
    }
}
