package com.example.pillreminder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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


}
