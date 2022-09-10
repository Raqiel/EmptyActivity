package com.example.emptyactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ContactAdapter (val contactsList:ArrayList<Contact>): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ContactAdapter.ViewHolder {
        //qual o layuot que vc quer adaptar na recyclerView?
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_view, parent, false)
        return ViewHolder(view)

    }



    //pegar a posição de cada item
    override fun onBindViewHolder (holder: ContactAdapter.ViewHolder, position : Int) {
        holder.bindItem(contactsList[position])

    }

    //tamanho da lista
    override fun getItemCount(): Int{
        return contactsList.size
    }

    //contrutor, cada item da view - vai herdar de recyclerviewviewholder o item
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(contact: Contact) {
        //itens dentro do itemview

        val textName = itemView.findViewById<TextView>(R.id.contact_name)
        val textPhone= itemView.findViewById<TextView>(R.id.contact_phone)

        textName.text= contact.name
        textPhone.text = contact.phoneNumber
    }

    }
}