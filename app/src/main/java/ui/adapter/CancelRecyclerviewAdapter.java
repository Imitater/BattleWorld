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

public class CancelRecyclerviewAdapter extends RecyclerView.Adapter<CancelRecyclerviewAdapter.MyViewHolder> {
    private Context context;

    public CancelRecyclerviewAdapter(Context context) {
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
            viewHolder.cancelRl.setBackgroundColor(context.getResources().getColor(R.color.colorbuy));
            viewHolder.cancelState.setText("对方已取消");
        }else{
            viewHolder.cancelRl.setBackgroundColor(context.getResources().getColor(R.color.colorsell));
            viewHolder.cancelState.setText("超时已取消");
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }


    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        //货币类型
        TextView cancelType;
        //订单时间
        TextView cancelTime;
        //交易状态
        TextView cancelState;
        //货币比例
        TextView cancelShallow;
        //货币总量
        TextView cancelCount;
        //货币总金额
        TextView cancelAmount;
        //货币计价方式
        TextView cancelTransaction;
        //买or卖
        RelativeLayout cancelRl;
        public MyViewHolder(View view) {
            super(view);
            cancelType=view.findViewById(R.id.type);
            cancelTime=view.findViewById(R.id.time);
            cancelState=view.findViewById(R.id.state);
            cancelShallow=view.findViewById(R.id.shallow);
            cancelCount=view.findViewById(R.id.count);
            cancelAmount=view.findViewById(R.id.amount);
            cancelTransaction=view.findViewById(R.id.Transaction);
            cancelRl=view.findViewById(R.id.rl);
        }
    }

}
