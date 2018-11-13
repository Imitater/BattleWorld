package ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import ui.activity.PayWayActivity;

public class PayWayRecyclerviewAdapter  extends RecyclerView.Adapter<PayWayRecyclerviewAdapter.MyViewHolder>{


    private final PayWayActivity context;

    public PayWayRecyclerviewAdapter(PayWayActivity payWayActivity) {
        this.context=payWayActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_payway_layout, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private ImageButton next;
        private RelativeLayout item;
        private ImageView lable;
        public MyViewHolder(View view)
        {
            super(view);
            title= view.findViewById(R.id.adapter_title);
            next = view.findViewById(R.id.adapter_next);
            item = view.findViewById(R.id.adapter_item);
            lable = view.findViewById(R.id.adapter_lable);
        }
    }
}
