package com.example.peiyu_wang.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.peiyu_wang.databinding.databinding.ActivityRecycleBinding;
import com.example.peiyu_wang.databinding.databinding.RecycleItemBinding;
import com.example.peiyu_wang.databinding.entity.StudentObserve;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {

    ActivityRecycleBinding recycleBinding;

    RecyclerView recyclerView;

    List<StudentObserve> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recycleBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycle);

        recycleBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentObserve observe = list.get(0);
                observe.setName("button change name");
                observe.setAddress("button change address");
            }
        });

        recyclerView = recycleBinding.recycleview;


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();
        for(int i=0;i<50;i++){
            StudentObserve observe = new StudentObserve("name" + i, "address" + i, "phone" + i);
            list.add(observe);
        }

        BaseRecycleViewAdapter<StudentObserve, RecycleItemBinding> recycleViewAdapter = new BaseRecycleViewAdapter(list, R.layout.recycle_item, BR.recycleStu);

        recyclerView.setAdapter(recycleViewAdapter);

        recycleViewAdapter.setListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewDataBinding dataBinding, int position) {
                StudentObserve observe = list.get(position);
                observe.setName("new "+observe.getName());
                observe.setAddress("new " + observe.getAddress());
            }

            @Override
            public void onLongItemClick(ViewDataBinding dataBinding, int position) {
                StudentObserve observe = list.get(position);
                observe.setName("long new "+observe.getName());
                observe.setAddress("long new " + observe.getAddress());
            }
        });
    }
}
