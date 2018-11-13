package ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhonglian.battleworld.R;

public class DealRecyclerviewAdapter extends RecyclerView.Adapter<DealRecyclerviewAdapter.MyViewHolder>{


    private final Context context;

    public DealRecyclerviewAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_deal_layout, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (i==5){
            myViewHolder.line.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        View line;
        public MyViewHolder(View view)
        {
            super(view);
            line=view.findViewById(R.id.deal_line);
        }
    }
}
