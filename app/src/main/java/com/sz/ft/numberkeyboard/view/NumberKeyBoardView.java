package com.sz.ft.numberkeyboard.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sz.ft.numberkeyboard.R;
import com.sz.ft.numberkeyboard.adapter.NumberKeyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CJYJ-6 on 2017/3/27.
 */

public class NumberKeyBoardView extends LinearLayout implements NumberKeyAdapter.OnItemClickListener {
    private Context context;
    private List<String> strings;
    private StringBuilder mBuilder;
    private OnKeyBoardClickListener mOnKeyBoardClickListener;
    private String score_text;
    private boolean isInputAble = true;
    private RecyclerView mRecyclerViewNumberKey;

    public NumberKeyBoardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberKeyBoardView(Context context) {
        this(context, null);
    }

    public NumberKeyBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View mPopupView = LayoutInflater.from(context).inflate(R.layout.view_num_berkey, this);
        init(context, mPopupView);
    }

    public void setOnKeyBoardClickListener(OnKeyBoardClickListener l) {
        mOnKeyBoardClickListener = l;
    }

    public interface OnKeyBoardClickListener {
        void scoreChanged(String score);
    }

    private void init(Context context, View view) {
        mBuilder = new StringBuilder();
        strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");
        strings.add("8");
        strings.add("9");
        strings.add(".");
        strings.add("0");
        strings.add("清除");
        mRecyclerViewNumberKey = (RecyclerView) view.findViewById(R.id.mRecyclerViewNumberKey);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        mRecyclerViewNumberKey.setLayoutManager(manager);
        NumberKeyAdapter myRecycleAdapter = new NumberKeyAdapter(context, strings);
        mRecyclerViewNumberKey.setAdapter(myRecycleAdapter);
        myRecycleAdapter.setOnItemClickListener(this);
    }


    public void clear() {
        score_text = "";
        mBuilder = new StringBuilder();
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder) {
        if (isInputAble) {
            String s = strings.get(holder.getAdapterPosition());
            if (s.equals(".")) { //.
                if (mBuilder.toString().contains(".")) {
                } else {
                    if (mBuilder.toString().trim().length() == 0) {
                        mBuilder.append("0.");
                    } else if (mBuilder.toString().trim().length() == 1) {
                        mBuilder.append(".");
                    }
                }
                score_text = mBuilder.toString();
                if (mOnKeyBoardClickListener != null) {
                    mOnKeyBoardClickListener.scoreChanged(score_text);
                }
            } else if (s.equals("清除")) { //清除
                if (mBuilder.length() > 0) {
                    mBuilder.delete(mBuilder.length() - 1, mBuilder.length());
                    score_text = mBuilder.toString();
                    if (mOnKeyBoardClickListener != null) {
                        mOnKeyBoardClickListener.scoreChanged(score_text);
                    }
                } else if (mBuilder.length() == 0) {
                    score_text = mBuilder.toString();
                    if (mOnKeyBoardClickListener != null) {
                        mOnKeyBoardClickListener.scoreChanged(score_text);
                    }
                }
            } else {   //正常字符
                score_text = mBuilder.toString();
                if (score_text.contains(".")) {     //带小数点
                    if (score_text.length() >= 3) {  //小数点后第二位不处理

                    } else {
                        mBuilder.append(s);
                    }
                } else {     //不带小数点
                    if (score_text.length() == 0) {  //第一位都是增加此字符
                        mBuilder.append(s);
                    } else {
                        if (score_text.startsWith("0")) {   //以0开头取第二位
                            mBuilder = new StringBuilder();
                            mBuilder.append(s);
                        } else {
                            mBuilder = new StringBuilder();   //两位直接改为10分
                            mBuilder.append(1);
                            mBuilder.append(0);
                        }
                    }
                }
                score_text = mBuilder.toString();
                if (mOnKeyBoardClickListener != null) {
                    mOnKeyBoardClickListener.scoreChanged(score_text);
                }
            }
        }
    }
}

