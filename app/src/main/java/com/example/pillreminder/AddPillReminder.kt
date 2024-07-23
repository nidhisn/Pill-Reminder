package com.example.pillreminder

import android.app.Activity
import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pillreminder.databinding.ActivityAddPillReminderBinding
import com.example.pillreminder.databinding.ActivityMainBinding

class AddPillReminder : AppCompatActivity() {

    private lateinit var binding:ActivityAddPillReminderBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_pill_reminder)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding= ActivityAddPillReminderBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)





        // Retrieve the string array from resources
        val reminderTypes = resources.getStringArray(R.array.reminder_types)
        // Create an ArrayAdapter using the string array and a default spinner layout
        val arrayAdapter= ArrayAdapter(this, android.R.layout.simple_spinner_item,reminderTypes)
        // Specify the layout to use when the list of choices appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        binding.spinnerType.adapter=arrayAdapter

        binding.spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem=parent?.getItemAtPosition(position).toString()
                binding.tvTypeTitle.setText(selectedItem+" Pill")
                //binding.tvTypeTitle.text = "$selectedItem Pill"

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        binding.btnSave.setOnClickListener {
            val hour=binding.timePicker.hour
            val minute=binding.timePicker.minute
            val soundEnabled=binding.swSound.isChecked
            val vibrationEnabled=binding.swVibration.isChecked
            val type=binding.spinnerType.selectedItem.toString()
            val reminderText=binding.etReminderText.text.toString()


            val resultIntent = Intent()
            val reminder = Reminder(type, "$hour:$minute", "Everyday", soundEnabled, vibrationEnabled, reminderText)
            resultIntent.putExtra("NEW_REMINDER", reminder)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()

        }

    }
}