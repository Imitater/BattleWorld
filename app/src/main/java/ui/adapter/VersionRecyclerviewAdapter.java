package ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

public class VersionRecyclerviewAdapter extends RecyclerView.Adapter<VersionRecyclerviewAdapter.MyViewHolder> {
    private final Context context;
    public VersionRecyclerviewAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public VersionRecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_version_layout, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int i) {



    }

    @Override
    public int getItemCount() {
        return 4;
    }


    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView time;
        public MyViewHolder(View view)
        {
            super(view);
            title=view.findViewById(R.id.version_adapter_title);
            time=view.findViewById(R.id.version_adapter_time);
        }
    }

}
