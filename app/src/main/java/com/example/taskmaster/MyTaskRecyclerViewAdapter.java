package com.example.taskmaster;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taskmaster.TaskFragment.OnListFragmentInteractionListener;
import com.example.taskmaster.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    static final String TAG = "martipapa.ViewAdapter";

    private final List<Task> mValues;
    private final OnTaskListener mListener;

    public MyTaskRecyclerViewAdapter(List<Task> items, OnTaskListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mBodyView.setText(mValues.get(position).getBody());
        holder.mStateView.setText(mValues.get(position).getState());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
               Log.i(TAG, "it was clicked!");
               Log.i(TAG, holder.mItem.getTitle());
//               mListener.onTaskClick(holder.mItem);

        Intent goToTaskDetail = new Intent(context, taskDetail.class);
        goToTaskDetail.putExtra("title", holder.mItem.getTitle());
        goToTaskDetail.putExtra("body", holder.mItem.getBody());
        goToTaskDetail.putExtra("state", holder.mItem.getState());
        context.startActivity(goToTaskDetail);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public final TextView mTitleView;
        public final TextView mBodyView;
        public final TextView mStateView;
        public Task mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.titleRecView);
            mBodyView = (TextView) view.findViewById(R.id.bodyRecView);
            mStateView = view.findViewById(R.id.stateRecView);

        }




        @Override
        public String toString() {
            return super.toString() + " '" + mBodyView.getText() + "'";
        }

    }

    public interface OnTaskListener {
        void onTaskClick(Task task);
    }


}
