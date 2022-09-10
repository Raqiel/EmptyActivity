package com.example.emptyactivity

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.jar.Manifest

val REQUEST_CONTACT = 1
val LINEAR_LAYOUT_VERTICAL = 1

class Contatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)



        //se a minha aplicação tem permissão de leitura, e se essa leitura for diferente de permissao garantida...
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED) {
            //pedir a permissão
            ActivityCompat.requestPermissions(this,
                //procura dentro do meu array de manifest
            arrayOf(android.Manifest.permission.READ_CONTACTS),
                //identificador que estou pedindo permissao, que foi declarado em val
            REQUEST_CONTACT)
        }else {
            //pega os contatos...
            setContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {


        //se o usuario autorizar , pode setar os contatos
        if (requestCode == REQUEST_CONTACT) setContacts()

    }

    @SuppressLint("Range")
    private fun setContacts() {
        //array list dos contatos que ta na data class
        val contactList: ArrayList<Contact> = ArrayList()

        //cursor que vai lendo item a item da tabela, pega no container que tem os dados em comum
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,
        null,
        null,
        null)

        if(cursor != null) {
            //enquanto o cursor tiver um proximo elemento
            while (cursor.moveToNext()){

                //Cria objeto contact que vai receber dois objetos, o nome e o numero
                contactList.add(Contact(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                ))
            }
            //sempre que abre um cursor, depois tem que fechar
            cursor.close()
        }
        // juntar tudo no codigo
        val adapter = ContactAdapter(contactList)
        val contactRecyclerView = findViewById<RecyclerView>(R.id.contacts_recycle_view)

        contactRecyclerView.layoutManager = LinearLayoutManager(this,
            LINEAR_LAYOUT_VERTICAL, false)

        contactRecyclerView.adapter = adapter

    }
}