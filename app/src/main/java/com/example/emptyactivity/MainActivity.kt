package com.example.emptyactivity

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events.*
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //abrir nova activity pagina
        val button: Button = findViewById(R.id.set_contacts)
        button.setOnClickListener {
            openContactsActivity()
        }
        //abrir pagina images
        val button2: Button = findViewById(R.id.set_images)
            button2.setOnClickListener {
                openImagesActivity()
            }

        //abrir pagina tirar foto
        val button3: Button = findViewById(R.id.set_camera)
        button3.setOnClickListener {
            openCameraActivity()
        }

        //abrir pagina mapas
        val button4: Button = findViewById(R.id.set_maps)
        button4.setOnClickListener {
            openMapsActivity()
        }




        //ação do botao
        val btnSetEvent = findViewById(R.id.set_event) as Button
        btnSetEvent.setOnClickListener {

            //cria um evento pre estabelecido no calendario, do horario de agora- até 1 hora mais tarde
            //esse evento no calendario nao precisa de permissão no manifest
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CONTENT_URI)
                .putExtra(TITLE, "Bootcamp")
                .putExtra(EVENT_LOCATION, "online")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis() + (60*60*1000))

            startActivity(intent)
        }
    }

    private fun openMapsActivity() {
        val intent = Intent (this, MapsActivity :: class.java)
        startActivity(intent)
    }

    private fun openContactsActivity(){
        val intent = Intent(this, Contatos::class.java)
        startActivity(intent)
    }


    private fun openImagesActivity(){
        val intent = Intent(this, Images::class.java)
        startActivity(intent)
    }


    private fun openCameraActivity(){
        val intent = Intent(this, Camera::class.java)
        startActivity(intent)
    }


}