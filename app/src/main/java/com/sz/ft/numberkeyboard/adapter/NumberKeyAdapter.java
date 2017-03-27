package com.sz.ft.numberkeyboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sz.ft.numberkeyboard.R;

import java.util.List;

/**
 * Created by CJYJ-6 on 2017/3/27.
 */

public class NumberKeyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> strings;
    private OnItemClickListener onItemClickListener;
    public NumberKeyAdapter(Context context, List<String> strings) {
        mContext = context;
        this.strings = strings;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_number_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            if (strings.get(position).equals("清除")) {  //.
                ((MyViewHolder) holder).txtOnClick.setText("清除");
                ((MyViewHolder) holder).txtOnClick.setTextSize(34);
            } else if (strings.get(position).equals("确认")) {  //.
                ((MyViewHolder) holder).txtOnClick.setText("确认");
                ((MyViewHolder) holder).txtOnClick.setTextSize(34);
            } else {
                ((MyViewHolder) holder).txtOnClick.setText(strings.get(position));
            }
            ((MyViewHolder) holder).txtOnClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, (MyViewHolder) holder);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtOnClick;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtOnClick = (TextView) itemView.findViewById(R.id.txtOnClick);

        }
    }
}

