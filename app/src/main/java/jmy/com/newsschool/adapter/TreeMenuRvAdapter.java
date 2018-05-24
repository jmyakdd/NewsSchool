package jmy.com.newsschool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jmy.com.newsschool.R;
import jmy.com.newsschool.bean.Menu;

public class TreeMenuRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Menu> data;
    private Context context;
    private OnMenuItemClickListener onMenuItemClickListener;

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    public TreeMenuRvAdapter(List<Menu> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getLevel();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.item_menu, null);
            return new ViewHolder1(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_sub_menu, null);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder1) {
            ((ViewHolder1) holder).textView.setText(data.get(position).getName());
            if(onMenuItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onMenuItemClickListener.onItemClick(position);
                    }
                });
            }
        } else if (holder instanceof ViewHolder2) {
            ((ViewHolder2) holder).textView.setText(data.get(position).getName());
            if(onMenuItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onMenuItemClickListener.onItemClick(position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder1(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder2(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    public interface OnMenuItemClickListener {
        void onItemClick(int position);
    }
}
