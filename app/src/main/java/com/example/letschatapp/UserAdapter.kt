package com.example.letschatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

//Once you've determined your layout, you need to implement your Adapter and ViewHolder.
//These two classes work together to define how your data is displayed.
//The ViewHolder is a wrapper around a View that contains the layout for an individual
//item in the list. The Adapter creates ViewHolder objects as needed, and also sets the
//data for those views. The process of associating views to their data is called binding.

class UserAdapter(val context:Context, val userList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.textName.text=currentUser.name

        //choose to chat on click
       holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)

           //sending name and uid to chatActivity
           intent.putExtra("name",currentUser.name)
           intent.putExtra("uid",currentUser.uid)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textName = itemView.findViewById<TextView>(R.id.txt_name)
    }


}