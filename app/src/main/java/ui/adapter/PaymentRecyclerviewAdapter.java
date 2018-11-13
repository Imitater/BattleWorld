package ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

public class PaymentRecyclerviewAdapter extends RecyclerView.Adapter<PaymentRecyclerviewAdapter.MyViewHolder> {
    private Context context;

    public PaymentRecyclerviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_payment_layout, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int i) {
        if (i==0){
            viewHolder.State.setText("等待对方放币");
            viewHolder.Rl.setBackgroundColor(context.getResources().getColor(R.color.colorbuy));
            viewHolder.complete.setVisibility(View.INVISIBLE);
            viewHolder.dismiss.setVisibility(View.INVISIBLE);
            viewHolder.paymentSellDismiss.setVisibility(View.VISIBLE);
        }else{
            viewHolder.State.setText("对方已支付");
            viewHolder.Rl.setBackgroundColor(context.getResources().getColor(R.color.colorsell));
        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }


    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        //货币类型
        TextView Type;
        //订单时间
        TextView Time;
        //交易状态
        TextView State;
        //货币比例
        TextView Shallow;
        //货币总量
        TextView Count;
        //货币总金额
        TextView Amount;
        //货币计价方式
        TextView Transaction;
        //买or卖
        RelativeLayout Rl;
        //超过时间
        TextView passTime;
        //超过结果
        TextView passTv;
        //取消
        Button dismiss;
        //确定
        Button complete;
        //sell 确定
        Button paymentSellDismiss;
        public MyViewHolder(View view) {
            super(view);
            Type=view.findViewById(R.id.type);
            Time=view.findViewById(R.id.time);
            State=view.findViewById(R.id.state);
            Shallow=view.findViewById(R.id.shallow);
            Count=view.findViewById(R.id.count);
            Amount=view.findViewById(R.id.amount);
            Transaction=view.findViewById(R.id.Transaction);
            Rl=view.findViewById(R.id.rl);
            passTime=view.findViewById(R.id.payment_passtime);
            passTv=view.findViewById(R.id.payment_pass_tv);
            dismiss=view.findViewById(R.id.payment_dismiss);
            complete=view.findViewById(R.id.payment_complete);
            paymentSellDismiss=view.findViewById(R.id.payment_sell_dismiss);
        }
    }

}
