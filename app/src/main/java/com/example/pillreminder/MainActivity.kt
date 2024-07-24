package com.example.pillreminder

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pillreminder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: ReminderAdapter
    private  var ReminderList= mutableListOf<Reminder>()

    companion object {
        const val ADD_REMINDER_REQUEST = 1
        const val CHANNEL_ID = "PILL_REMINDER_CHANNEL"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Create notification channel
        createNotificationChannel()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //setup RecyclerView
        binding.rvReminder.layoutManager = LinearLayoutManager(this)
        //initialise adapter and set it to recyclerView
        adapter = ReminderAdapter(this, ReminderList)
        binding.rvReminder.adapter = adapter


        binding.btnAddReminder.setOnClickListener {
            val intent = Intent(this@MainActivity, AddPillReminder::class.java)
            startActivityForResult(intent, ADD_REMINDER_REQUEST)
        }


    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if(requestCode== ADD_REMINDER_REQUEST && resultCode== Activity.RESULT_OK){
                val newReminder=data?.getParcelableExtra<Reminder>("NEW_REMINDER")
                newReminder?.let {
                    ReminderList.add(it)
                    adapter.notifyDataSetChanged()
                }
            }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Pill Reminder"
            val descriptionText = "Channel for pill reminders"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}
