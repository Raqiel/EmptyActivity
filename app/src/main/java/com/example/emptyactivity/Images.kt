package com.example.emptyactivity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_images.*

class Images : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        pick_button.setOnClickListener {
            //Se o sdk desse dispositivo for maior que o "M" marshmallow, foi a partir do marshmallow
            //que começaram as permissoes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                //checo se deu as permissoes. se a permissão nao foi dada
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                ==PackageManager.PERMISSION_DENIED ) {
                    //entao pede a permissao
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)

                    }
                else {
                    pickImageFromGalery()
                }

            }
            else{

                pickImageFromGalery()

            }
        }
    }
    //função que trata a permissão de pegar as fotos, pede ao usuario a permissao
    override fun onRequestPermissionsResult(
                                            requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {

        //quando o codigo da requisição...
        when(requestCode) {
            //for igual a permission code
            PERMISSION_CODE -> {
                //se ele autorizou a acessar a biblioteca de fotos
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGalery()
                } else {
                    Toast.makeText(this, "Permissão Negada" , Toast.LENGTH_SHORT).show()
                }
                
            }
        }

    }

    //pega as imagens da galeria
    private fun pickImageFromGalery() {
        val intent = Intent(Intent.ACTION_PICK)
        //tudo que for imagem você pode pegar
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //se ele clicou na biblioteca e deu tudo certo
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //irno lugar do xml e colocar a imagem
            image_view.setImageURI(data?.data)

        }
    }

    companion object{
        private val PERMISSION_CODE = 1000
        private val IMAGE_PICK_CODE = 1001

    }
}