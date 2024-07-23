package com.example.pillreminder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReminderAdapter(private val context: Context, private val ReminderList:MutableList<Reminder>):RecyclerView.Adapter<ReminderAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView=LayoutInflater.from(parent.context).inflate(R.layout.reminder_view_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ReminderList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val reminder=ReminderList[position]
        holder.bind(reminder)

    }


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(reminder: Reminder){
            itemView.findViewById<TextView>(R.id.tvType).text=reminder.type
            itemView.findViewById<TextView>(R.id.tvTime).text=reminder.time
            itemView.findViewById<TextView>(R.id.tvRepeat).text=reminder.repeat
        }

    }


}