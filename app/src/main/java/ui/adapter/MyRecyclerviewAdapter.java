package ui.adapter;

import android.content.Context;
import android.content.Intent;
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

import ui.activity.AboutActivity;
import ui.activity.FeedBackActivity;
import ui.activity.IdentityActivity;
import ui.activity.MyOrderActivity;
import ui.activity.NodeActivity;
import ui.activity.PayWayActivity;
import ui.activity.SafeActivity;
import ui.activity.SystemSetActivity;
import ui.activity.VisitActivity;

public class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.MyViewHolder> {
    private final String[] title;
    private Context context;
    private int[] lable={R.mipmap.mipmap_shengfen,R.mipmap.mipmap_list,R.mipmap.mipmap_safe,R.mipmap.mipmap_pay,R.mipmap.mipmap_visit,R.mipmap.mipmap_user,R.mipmap.mipmap_info,R.mipmap.mipmap_write,R.mipmap.mipmap_setting};
    public MyRecyclerviewAdapter(Context context, String[] strTitle) {
        this.context=context;
        this.title=strTitle;
    }

    @NonNull
    @Override
    public MyRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_my_layout, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int i) {
        //按钮点击
        viewHolder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i){
                    case 0:
                        //身份认证
                        Intent intent2 = new Intent(context, IdentityActivity.class);
                        context.startActivity(intent2);
                        break;
                    case 1:
                        //我的订单
                        Intent intent8 = new Intent(context, MyOrderActivity.class);
                        context.startActivity(intent8);
                        break;
                    case 2:
                        //安全中心
                        Intent intent1 = new Intent(context, SafeActivity.class);
                        context.startActivity(intent1);
                        break;
                    case 3:
                        //收款方式
                        Intent intent = new Intent(context, PayWayActivity.class);
                        context.startActivity(intent);
                        break;
                    case 4:
                        //邀请返佣
                        Intent intent7 = new Intent(context, VisitActivity.class);
                        context.startActivity(intent7);
                        break;
                    case 5:
                        //节点架构
                        Intent intent6 = new Intent(context, NodeActivity.class);
                        context.startActivity(intent6);
                        break;
                    case 6:
                        //关于我们
                        Intent intent3 = new Intent(context, AboutActivity.class);
                        context.startActivity(intent3);
                        break;
                    case 7:
                        //用户反馈
                        Intent intent4 = new Intent(context, FeedBackActivity.class);
                        context.startActivity(intent4);
                        break;
                    case 8:
                        //系统设置
                        Intent intent5 = new Intent(context, SystemSetActivity.class);
                        context.startActivity(intent5);
                        break;
                }

            }
        });
        //item点击
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i){
                    case 0:
                        //身份认证
                        Intent intent2 = new Intent(context, IdentityActivity.class);
                        context.startActivity(intent2);
                        break;
                    case 1:
                        //我的订单
                        Intent intent8 = new Intent(context, MyOrderActivity.class);
                        context.startActivity(intent8);
                        break;
                    case 2:
                        //安全中心
                        Intent intent1 = new Intent(context, SafeActivity.class);
                        context.startActivity(intent1);
                        break;
                    case 3:
                        //收款方式
                        Intent intent = new Intent(context, PayWayActivity.class);
                        context.startActivity(intent);
                        break;
                    case 4:
                        //邀请返佣
                        Intent intent7 = new Intent(context, VisitActivity.class);
                        context.startActivity(intent7);
                        break;
                    case 5:
                        //节点架构
                        Intent intent6 = new Intent(context, NodeActivity.class);
                        context.startActivity(intent6);
                        break;
                    case 6:
                        //关于我们
                        Intent intent3 = new Intent(context, AboutActivity.class);
                        context.startActivity(intent3);
                        break;
                    case 7:
                        //用户反馈
                        Intent intent4 = new Intent(context, FeedBackActivity.class);
                        context.startActivity(intent4);
                        break;
                    case 8:
                        //系统设置
                        Intent intent5 = new Intent(context, SystemSetActivity.class);
                        context.startActivity(intent5);
                        break;
                }
            }
        });
        //设置图片
        viewHolder.lable.setBackgroundResource(lable[i]);
        //设置title
        viewHolder.title.setText(title[i]);


    }

    @Override
    public int getItemCount() {
        return title.length;
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
