package com.example.peiyu_wang.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.peiyu_wang.databinding.databinding.ActivityMainBinding;
import com.example.peiyu_wang.databinding.entity.Student;
import com.example.peiyu_wang.databinding.entity.StudentFieldObserve;
import com.example.peiyu_wang.databinding.entity.StudentObserve;

public class MainActivity extends AppCompatActivity {

    Student student;

    StudentFieldObserve studentObserveField;

    StudentObserve studentObserve;

    ActivityMainBinding activityMainBinding;

    int i=0;

    View.OnClickListener listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {


//            student.setName("new xiaohng");
//            student.setAddress("new address");
          //  activityMainBinding.invalidateAll();


//            studentObserveField.name.set("new name");
//            studentObserveField.address.set("new address");

            studentObserve.setName("new name"+i);
            studentObserve.setAddress("new address"+i);
            i++;


            Toast.makeText(MainActivity.this, "change", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //使用最基本的Student
        student = new Student("xiaohong", "guangdong", "cc");


        //这个是使用ObserveField
        studentObserveField = new StudentFieldObserve();
        studentObserveField.name.set("name");
        studentObserveField.address.set("address");
        studentObserveField.phone.set("phone");


        //使用StudentObserve集成BaseObserve
        studentObserve = new StudentObserve("xiaohong", "guangdong", "cc");

        activityMainBinding.setStu(studentObserve);
        activityMainBinding.setClicklistener(listener);


        //View中有id，可以直接绑定id
//        activityMainBinding.change


        activityMainBinding.toNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecycleActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

}
