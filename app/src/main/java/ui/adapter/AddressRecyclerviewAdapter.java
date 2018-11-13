package ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhonglian.battleworld.R;

import java.util.ArrayList;

import utils.BaseViewHolder;

public class AddressRecyclerviewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mList;

    public AddressRecyclerviewAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BaseViewHolder(mInflater.inflate(R.layout.adapter_address_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BaseViewHolder viewHolder = (BaseViewHolder) holder;
        viewHolder.setText(R.id.adapter_address_name, "测试");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
