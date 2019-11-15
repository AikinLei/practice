package com.Leon;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Leon.RetrofitPratice.R;
import com.Leon.view.CustomerActivity;
import com.Leon.view.ImageTextActivity;
import com.Leon.view.PeiChartActivity;
import com.Leon.view.coordinator.CoordinatorActivity;
import com.Leon.view.coordinator.CoordinatorLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<ClassBean> mClassBeanArrayList = new ArrayList<>();
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        mClassBeanArrayList.add(new ClassBean(CustomerActivity.class, "CustomerActivity"));
        mClassBeanArrayList.add(new ClassBean(CoordinatorActivity.class, "CoordinatorActivity"));
        mClassBeanArrayList.add(new ClassBean(CoordinatorLayoutActivity.class, "CoordinatorLayoutActivity"));
        mClassBeanArrayList.add(new ClassBean(PeiChartActivity.class, "PeiChartActivity"));
        mClassBeanArrayList.add(new ClassBean(ImageTextActivity.class, "ImageTextActivity"));


        mAdapter = new MyAdapter(mClassBeanArrayList, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


    }


    class MyAdapter extends RecyclerView.Adapter {
        List<ClassBean> mBeanList;
        private Activity mActivity;

        public MyAdapter(List<ClassBean> beanList, Activity activity) {
            this.mBeanList = beanList;
            this.mActivity = activity;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_recycler, viewGroup, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ClassBean classBean = mBeanList.get(i);
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

            myViewHolder.mTextView.setText(classBean.getTitle());

            myViewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.startActivity(new Intent(mActivity, classBean.getClassN()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mBeanList.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.text_view);
            }
        }
    }
}
