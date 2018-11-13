package ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhonglian.battleworld.R;

public class UserInfoRecyclerviewAdapter extends RecyclerView.Adapter<UserInfoRecyclerviewAdapter.MyViewHolder> {
    private Context context;
    private ButtonInterface buttonInterface;
    //true 为购买 false 为出售
    private boolean flag=true;

    public UserInfoRecyclerviewAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public UserInfoRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_userinfo_layout, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int i) {
        if (i==1){
           setBuyOrSell(false);
        }else{
            setBuyOrSell(true);
        }
        viewHolder.adapterItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonInterface!=null) {
//                  接口实例化后的而对象，调用重写后的方法
                    buttonInterface.onclick(v,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout adapterItem;
        public MyViewHolder(View view)
        {
            super(view);
            adapterItem=view.findViewById(R.id.adapter_item);
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

    public void setBuyOrSell(boolean flag){
        this.flag=flag;
    }
    public boolean getBuyOrSell(){
        return flag;
    }
}
