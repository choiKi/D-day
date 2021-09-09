package com.ckh.d_day

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.ckh.d_day.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var startDay = ""
        var endDay = ""
        val calendarStart = Calendar.getInstance()
        val calendarEnd = Calendar.getInstance()

        binding.startDayBtn.setOnClickListener {
            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)
            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    startDay = "${year}${month+1}${dayOfMonth}"
                    calendarStart.set(year,month+1,dayOfMonth)
                }
            },year,month,day)
            dlg.show()
        }
        binding.timeBtn.setOnClickListener {
            if (startDay == "") {
                Toast.makeText(this,"시작 날짜를 먼저 설정해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val timeDay = GregorianCalendar()
            val year = timeDay.get(Calendar.YEAR)
            val month = timeDay.get(Calendar.MONTH)
            val day = timeDay.get(Calendar.DATE)

            val timeDlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int){
                    endDay = "${year}${month+1}${dayOfMonth}"
                    calendarEnd.set(year,month+1,dayOfMonth)

                    val finalDay =  TimeUnit.MILLISECONDS.toDays(calendarEnd.timeInMillis - calendarStart.timeInMillis)

                    binding.dDay.setText((finalDay+1).toString())
                }
            },year,month,day)
            timeDlg.show()
        }
        binding.resetBtn.setOnClickListener {
            startDay = ""
            endDay = ""
            binding.dDay.setText("-")
        }
    }
}