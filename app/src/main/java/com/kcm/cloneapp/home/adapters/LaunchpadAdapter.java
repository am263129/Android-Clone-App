package com.kcm.cloneapp.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.kcm.cloneapp.R;
import com.kcm.cloneapp.abs.ui.VUiKit;
import com.kcm.cloneapp.home.models.AppData;
import com.kcm.cloneapp.home.models.MultiplePackageAppData;
import com.kcm.cloneapp.widgets.LabelView;
import com.kcm.cloneapp.widgets.LauncherIconView;

/**
 * @author Lody
 */
public class LaunchpadAdapter extends RecyclerView.Adapter<LaunchpadAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<AppData> mList;
    private SparseIntArray mColorArray = new SparseIntArray();
    private OnAppClickListener mAppClickListener;

    public LaunchpadAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void add(AppData model) {
        int insertPos = mList.size();
//        int insertPos = mList.size() - 1;
        mList.add(model);
//        mList.add(insertPos, model);
        notifyItemInserted(insertPos);
    }

    public void replace(int index, AppData data) {
        mList.set(index, data);
        notifyItemChanged(index);
    }

    public void remove(AppData data) {
        if (mList.remove(data)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_launcher_app, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppData data = mList.get(position);
        holder.iconView.setImageDrawable(data.getIcon());

        holder.itemView.setBackgroundColor(holder.color);
        holder.itemView.setOnClickListener(v -> {
            if (mAppClickListener != null) {
                mAppClickListener.onAppClick(position, data);
            }
        });
        if (data.isLoading()) {
            startLoadingAnimation(holder.iconView);
        } else {
            holder.iconView.setProgress(100, false);
        }
    }

    private void startLoadingAnimation(LauncherIconView iconView) {
        iconView.setProgress(40, true);
        VUiKit.defer().when(() -> {
            try {
                Thread.sleep(900L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).done((res) -> iconView.setProgress(80, true));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public List<AppData> getList() {
        return mList;
    }

    public void setList(List<AppData> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setAppClickListener(OnAppClickListener mAppClickListener) {
        this.mAppClickListener = mAppClickListener;
    }

    public void moveItem(int pos, int targetPos) {
        AppData model = mList.remove(pos);
        mList.add(targetPos, model);
        notifyItemMoved(pos, targetPos);
    }

    public void refresh(AppData model) {
        int index = mList.indexOf(model);
        if (index >= 0) {
            notifyItemChanged(index);
        }
    }

    public interface OnAppClickListener {
        void onAppClick(int position, AppData model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public int color;
        LauncherIconView iconView;
        ViewHolder(View itemView) {
            super(itemView);
            iconView = (LauncherIconView) itemView.findViewById(R.id.item_app_icon);
        }
    }
}
