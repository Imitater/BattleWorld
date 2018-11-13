package ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

public class AllOrdersRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public static final int ONE_ITEM = 1;
    public static final int TWO_ITEM = 2;
    private ButtonInterface buttonInterface;
    public AllOrdersRecyclerviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder holder = null;
        if (ONE_ITEM == i) {
            View v = mInflater.inflate(R.layout.order1_item_layout, viewGroup, false);
            holder = new OneViewHolder(v);
        } else {
            View v = mInflater.inflate(R.layout.order2_item_layout, viewGroup, false);
            holder = new TwoViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int i) {
        if (holder instanceof OneViewHolder) {
            if (i == 2) {
                ((OneViewHolder) holder).State.setText("对方已取消");
            } else if (i == 3) {
                ((OneViewHolder) holder).State.setText("交易成功");
            }

        } else {
            if (i == 0) {
                ((TwoViewHolder) holder).State.setText("对方已支付");
                ((TwoViewHolder) holder).button1.setText("申诉");
                ((TwoViewHolder) holder).button2.setText("确认放币");
            } else if (i == 1) {
                ((TwoViewHolder) holder).State.setText("等待支付");
                ((TwoViewHolder) holder).button1.setVisibility(View.INVISIBLE);
                ((TwoViewHolder) holder).button2.setVisibility(View.INVISIBLE);
                ((TwoViewHolder) holder).button3.setVisibility(View.VISIBLE);
                ((TwoViewHolder) holder).button3.setText("支付订单");
                ((TwoViewHolder) holder).Rl.setBackgroundColor(context.getResources().getColor(R.color.colorbuy));
            } else if (i == 4) {
                ((TwoViewHolder) holder).State.setText("申诉中");
                ((TwoViewHolder) holder).button1.setText("取消申诉");
                ((TwoViewHolder) holder).button2.setText("确认放币");
            }
            ((TwoViewHolder) holder).adapterItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttonInterface!=null) {
//                  接口实例化后的而对象，调用重写后的方法
                        buttonInterface.onclick(v,i);
                    }

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TWO_ITEM;
        } else if (position == 1) {
            return TWO_ITEM;
        } else if (position == 2) {
            return ONE_ITEM;
        } else if (position == 3) {
            return ONE_ITEM;
        } else if (position == 4) {
            return TWO_ITEM;
        } else {
            return ONE_ITEM;
        }
    }


    class OneViewHolder extends RecyclerView.ViewHolder {
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

        public OneViewHolder(View itemView) {
            super(itemView);
            Type = itemView.findViewById(R.id.type);
            Time = itemView.findViewById(R.id.time);
            State = itemView.findViewById(R.id.state);
            Shallow = itemView.findViewById(R.id.shallow);
            Count = itemView.findViewById(R.id.count);
            Amount = itemView.findViewById(R.id.amount);
            Transaction = itemView.findViewById(R.id.Transaction);
            Rl = itemView.findViewById(R.id.rl);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
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
        Button button1;
        //确定
        Button button2;
        //sell 确定
        Button button3;
        //item
        LinearLayout adapterItem;
        public TwoViewHolder(View itemView) {
            super(itemView);
            Type = itemView.findViewById(R.id.type);
            Time = itemView.findViewById(R.id.time);
            State = itemView.findViewById(R.id.state);
            Shallow = itemView.findViewById(R.id.shallow);
            Count = itemView.findViewById(R.id.count);
            Amount = itemView.findViewById(R.id.amount);
            Transaction = itemView.findViewById(R.id.Transaction);
            Rl = itemView.findViewById(R.id.rl);
            passTime = itemView.findViewById(R.id.payment_passtime);
            passTv = itemView.findViewById(R.id.payment_pass_tv);
            button1 = itemView.findViewById(R.id.button1);
            button2 = itemView.findViewById(R.id.button2);
            button3 = itemView.findViewById(R.id.button3);
            adapterItem=itemView.findViewById(R.id.adapter_item);
        }
    }
    /**
     *按钮点击事件需要的方法
     */
    public void buttonSetOnclick(ButtonInterface buttonInterface){
        this.buttonInterface=buttonInterface;
    }

    /**
     * 按钮点击事件对应的接口
     */
    public interface ButtonInterface{
        public void onclick( View view,int position);
    }

}
