package jmy.com.newsschool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jmy.com.newsschool.R;
import jmy.com.newsschool.bean.ProviceData;

public class MapLoadAdapter extends RecyclerView.Adapter<MapLoadAdapter.MapViewHolder> {
    private List<ProviceData> data;
    private Context context;
    private OnMapMenuClickListener onMapMenuClickListener;
    private int currentLoad = -1;

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
        notifyDataSetChanged();
    }

    public void setOnMapMenuClickListener(OnMapMenuClickListener onMapMenuClickListener) {
        this.onMapMenuClickListener = onMapMenuClickListener;
    }

    public MapLoadAdapter(List<ProviceData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.offlinemap_child, parent, false);
        return new MapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MapViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getProviceKey());
        if (data.get(position).isLoad()) {
            holder.download_progress_status.setText("导入完成");
            holder.download_status_image.setVisibility(View.GONE);
        } else {
            holder.download_progress_status.setText("");
            holder.download_status_image.setVisibility(View.VISIBLE);
        }

        if (data.get(position).isLoading()) {
            holder.download_progress_status.setText("导入中...");
            holder.download_status_image.setVisibility(View.GONE);
        }
        holder.download_status_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMapMenuClickListener != null && !data.get(position).isLoading()) {
                    onMapMenuClickListener.onStart(position);
                    data.get(position).setLoading(true);
                    notifyDataSetChanged();
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(onMapMenuClickListener!=null&&data.get(position).isLoad()){
                    onMapMenuClickListener.onLongClick(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MapViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView download_progress_status;
        ImageView download_status_image;

        public MapViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            download_progress_status = itemView.findViewById(R.id.download_progress_status);
            download_status_image = itemView.findViewById(R.id.download_status_image);
        }
    }

    public interface OnMapMenuClickListener {
        void onStart(int position);

        void onLongClick(int position);
    }
}
