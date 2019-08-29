package com.kcm.cloneapp.widgets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import com.kcm.cloneapp.VCommends;
import com.kcm.cloneapp.home.adapters.CloneAppListAdapter;
import com.kcm.cloneapp.home.models.AppInfo;
import com.kcm.cloneapp.home.models.AppInfoLite;

/**
 * @author Aidan Follestad (afollestad)
 */
public abstract class DragSelectRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private ArrayList<Integer> mSelectedIndices;
    private SelectionListener mSelectionListener;
    private int mLastCount = -1;
    private int mMaxSelectionCount = -1;
    protected DragSelectRecyclerViewAdapter() {
        mSelectedIndices = new ArrayList<>();
    }

    private void fireSelectionListener() {
        if (mLastCount == mSelectedIndices.size())
            return;
        mLastCount = mSelectedIndices.size();
        if (mSelectionListener != null)
            mSelectionListener.onDragSelectionChanged(mLastCount);
    }
    public void saveInstanceState(Bundle out) {
        saveInstanceState("selected_indices", out);
    }

    public void saveInstanceState(String key, Bundle out) {
        out.putSerializable(key, mSelectedIndices);
    }
    public final void setSelected(int index, boolean selected) {
        if (!isIndexSelectable(index))
            selected = false;
        if (selected) {
            if (!mSelectedIndices.contains(index) &&
                    (mMaxSelectionCount == -1 ||
                            mSelectedIndices.size() < mMaxSelectionCount)) {
                mSelectedIndices.add(index);
                notifyItemChanged(index);
            }
        } else if (mSelectedIndices.contains(index)) {
            mSelectedIndices.remove((Integer) index);
            notifyItemChanged(index);
        }
        fireSelectionListener();
    }
    protected boolean isIndexSelectable(int index) {
        return true;
    }

    @CallSuper
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setTag(holder);
    }

    public interface SelectionListener {
        void onDragSelectionChanged(int count);
    }
}