package com.example.myapplicationproverca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.TimeZone;
import androidx.appcompat.app.AlertDialog;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView timePick;
    Button btnTime, btnDate;
    Calendar dateTime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTime = findViewById(R.id.btnTime);
        btnDate = findViewById(R.id.btnDate);
        timePick = findViewById(R.id.timePick);

        btnTime.setOnClickListener(v -> { getTime(); });
        btnDate.setOnClickListener(v -> { getDate(); });
        Button btnArts = findViewById(R.id.btnArts);
        // Получите часовой пояс устройства
        TimeZone deviceTimeZone = TimeZone.getDefault();

        // Установите часовой пояс для Calendar
        dateTime.setTimeZone(deviceTimeZone);

        setInitialDateTime();

        btnArts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем диалоговое окно с вопросом
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Хочешь посмотреть арты?");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // При нажатии на "Да" выводим сообщение с помощью Toast
                        Toast.makeText(MainActivity.this, "А вот нет артов!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // При нажатии на "Нет" выводим другое сообщение с помощью Toast
                        Toast.makeText(MainActivity.this, "Молодец, не прогнулся!", Toast.LENGTH_SHORT).show();
                    }
                });

                // Показываем диалоговое окно
                builder.show();
            }
        });
    }
    private void setInitialDateTime(){
        timePick.setText(DateUtils.formatDateTime( this,
                dateTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }
    public void getDate(){
        new DatePickerDialog(MainActivity.this, d,
                dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }
    public void getTime(){
        new TimePickerDialog(MainActivity.this, t,
                dateTime.get(Calendar.HOUR_OF_DAY),
                dateTime.get(Calendar.MINUTE), true)
                .show();
    }
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}