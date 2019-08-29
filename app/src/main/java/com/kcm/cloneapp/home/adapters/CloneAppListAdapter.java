package com.kcm.cloneapp.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.kcm.cloneapp.R;
import com.kcm.cloneapp.abs.ui.VUiKit;
import com.kcm.cloneapp.home.models.AppInfo;
import com.kcm.cloneapp.widgets.DragSelectRecyclerViewAdapter;
import com.kcm.cloneapp.widgets.LabelView;

/**
 * @author Lody
 */
public class CloneAppListAdapter extends DragSelectRecyclerViewAdapter<CloneAppListAdapter.ViewHolder> {

    private static final int TYPE_FOOTER = -2;
    private final View mFooterView;
    private LayoutInflater mInflater;
    private List<AppInfo> mAppList;
    private ItemEventListener mItemEventListener;

    public CloneAppListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mFooterView = new View(context);
        StaggeredGridLayoutManager.LayoutParams params = new StaggeredGridLayoutManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, VUiKit.dpToPx(context, 60)
        );
        params.setFullSpan(true);
        mFooterView.setLayoutParams(params);

    }

    public void setOnItemClickListener(ItemEventListener mItemEventListener) {
        this.mItemEventListener = mItemEventListener;
    }

    public List<AppInfo> getList() {
        return mAppList;
    }

    public void setList(List<AppInfo> models) {
        this.mAppList = models;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }
        return new ViewHolder(mInflater.inflate(R.layout.item_clone_app, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            return;
        }
        super.onBindViewHolder(holder, position);
        AppInfo info = mAppList.get(position);
        holder.iconView.setImageDrawable(info.icon);
        holder.nameView.setText(info.name);

        holder.itemView.setOnClickListener(v -> {
            mItemEventListener.onItemClick(info, position);
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mAppList == null ? 1 : mAppList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    public interface ItemEventListener {

        void onItemClick(AppInfo appData, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconView;
        private TextView nameView;

        ViewHolder(View itemView) {
            super(itemView);
            if (itemView != mFooterView) {
                iconView = (ImageView) itemView.findViewById(R.id.item_app_icon);
                nameView = (TextView) itemView.findViewById(R.id.item_app_name);
            }
        }
    }
}
