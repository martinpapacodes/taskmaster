package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmaster.TaskFragment.OnListFragmentInteractionListener;
import com.example.taskmaster.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AllTaskRecyclerViewAdapter extends RecyclerView.Adapter<AllTaskRecyclerViewAdapter.ViewHolder> {

    static final String TAG = "martipapa.ViewAdapter";

    private final List<Task> mValues;
    private final OnTaskListener mListener;

    public AllTaskRecyclerViewAdapter(List<Task> items, OnTaskListener listener) {
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

        CharSequence text = holder.mItem.getTitle() + " was clicked!";
        int duration = Toast.LENGTH_SHORT;
        Toast newToast = Toast.makeText(context, text, duration );
        newToast.show();
//        goToTaskDetail.putExtra("title", holder.mItem.getTitle());
//        goToTaskDetail.putExtra("body", holder.mItem.getBody());
//        goToTaskDetail.putExtra("state", holder.mItem.getState());
//        context.startActivity(goToTaskDetail);

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
