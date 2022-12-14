package com.D121191055.myapplication.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.D121191055.myapplication.R

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var list = mutableListOf<user>()
    private var actionUpdate: ((user) -> Unit)? = null
    private var actionDelete: ((user) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view,parent,false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.viewJudul.text = user.title
        holder.viewIsi.text = user.note
        holder.viewKategori.text = user.Kategori
        holder.actionUpdate.setOnClickListener{actionUpdate?.invoke(user)}
        holder.actionDelete.setOnClickListener{actionDelete?.invoke(user)}
    }

    override fun getItemCount() = list.size

    fun setData(data: List<user>){
        list.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun setOnActionEditListener(callback: (user) -> Unit){
        this.actionUpdate = callback
    }

    fun setOnActionDeleteListener(callback: (user) -> Unit){
        this.actionDelete = callback
    }

    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val viewJudul : TextView = itemView.findViewById(R.id.ViewJudul)
        val viewIsi : TextView = itemView.findViewById(R.id.ViewIsi)
        val viewKategori : TextView = itemView.findViewById(R.id.ViewKategori)
        val actionUpdate : ImageView = itemView.findViewById(R.id.ViewUpdate)
        val actionDelete : ImageView = itemView.findViewById(R.id.ViewDelete)
    }

}