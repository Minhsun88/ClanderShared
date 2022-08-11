package com.example.gp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.example.gp.databinding.ActivityIndexBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class index extends AppCompatActivity {
    private ActivityIndexBinding B;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = ActivityIndexBinding.inflate(getLayoutInflater());
        setContentView(B.getRoot());

        B.buttonGetTheDay.setOnClickListener(v -> {
            /**利用forEach迴圈找出指定元素*/
            for (Calendar calendar : B.calendarView.getSelectedDates()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Toast.makeText(this, sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
            }
        });

        B.buttonToday.setOnClickListener(v -> {
            /**取得今日之Date*/
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfM = new SimpleDateFormat("MM");
            SimpleDateFormat sdfD = new SimpleDateFormat("dd");
            /**calender設置為今日*/
            calendar.set(Integer.parseInt(sdfY.format(date))
                    , Integer.parseInt(sdfM.format(date)) - 1
                    , Integer.parseInt(sdfD.format(date)));
            try {
                /**刷新介面*/
                B.calendarView.setDate(calendar);
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }
        });

        List<EventDay> event = new ArrayList<>();

        B.buttonSetTarget.setOnClickListener(v -> {
            new Thread(() -> {
                /**利用forEach迴圈找出指定元素*/
                for (Calendar calendar : B.calendarView.getSelectedDates()) {
                    /**取得選定日之Date*/
                    calendar.setTime(calendar.getTime());
                    /**在event陣列中新增一個元素*/
                    event.add(new EventDay(calendar, R.drawable.ic_baseline_save_24));
                    runOnUiThread(() -> {
                        /**刷新介面*/
                        B.calendarView.setEvents(event);
                    });
                }
            }).start();
        });


        B.buttonCancelTarget.setOnClickListener(v -> {
            new Thread(() -> {
                /**利用forEach迴圈找出指定元素*/
                for (Calendar calendar : B.calendarView.getSelectedDates()) {
                    /**取得選定日之Date*/
                    calendar.setTime(calendar.getTime());
                    /**利用for迴圈找出指定元素之index*/
                    for (int i = 0; i < event.size(); i++) {
                        Long select = calendar.getTimeInMillis();
                        Long target = event.get(i).getCalendar().getTimeInMillis();
                        if (select.equals(target)){
                            /**刪除指定元素*/
                            event.remove(i);
                            runOnUiThread(() -> {
                                /**刷新介面*/
                                B.calendarView.setEvents(event);
                            });
                        }
                    }
                }
            }).start();
        });
    }
}