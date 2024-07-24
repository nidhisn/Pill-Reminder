package com.example.pillreminder

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pillreminder.databinding.ActivityReminderFullScreenBinding

class ReminderFullScreen : AppCompatActivity() {
    private lateinit var binding: ActivityReminderFullScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reminder_full_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityReminderFullScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Retrieve the reminder text from the intent
        val reminderText = intent.getStringExtra("REMINDER_TEXT")
        // Display the reminderText in your UI
        binding.tvReminderText.text = reminderText
    }
}
