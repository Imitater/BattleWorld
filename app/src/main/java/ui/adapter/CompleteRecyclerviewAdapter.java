package ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

public class CompleteRecyclerviewAdapter extends RecyclerView.Adapter<CompleteRecyclerviewAdapter.MyViewHolder> {
    private Context context;

    public CompleteRecyclerviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_complete_layout, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int i) {
        if (i==0){
            viewHolder.completeRl.setBackgroundColor(context.getResources().getColor(R.color.colorbuy));
        }else{
            viewHolder.completeRl.setBackgroundColor(context.getResources().getColor(R.color.colorsell));
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }


    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        //货币类型
        TextView completeType;
        //订单时间
        TextView completeTime;
        //交易状态
        TextView completeState;
        //货币比例
        TextView completeShallow;
        //货币总量
        TextView completeCount;
        //货币总金额
        TextView completeAmount;
        //货币计价方式
        TextView completeTransaction;
        //买or卖
        RelativeLayout completeRl;
        public MyViewHolder(View view) {
            super(view);
            completeType=view.findViewById(R.id.type);
            completeTime=view.findViewById(R.id.time);
            completeState=view.findViewById(R.id.state);
            completeShallow=view.findViewById(R.id.shallow);
            completeCount=view.findViewById(R.id.count);
            completeAmount=view.findViewById(R.id.amount);
            completeTransaction=view.findViewById(R.id.Transaction);
            completeRl=view.findViewById(R.id.rl);
        }
    }

}
