package com.example.agenda.ui.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.agenda.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show()

        setContentView(R.layout.activity_main)


        val alunos: List<String> = ArrayList(listOf("Alex", "Fran", "Jose"))

        val listaDeAlunos = findViewById<ListView>(R.id.activity_main_lista_de_alunos)

        listaDeAlunos.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            alunos
        )

        val color = resources.getString(R.color.teal_200.toInt())

        val actionBar: ActionBar? = supportActionBar

        val colorDrawable = ColorDrawable(Color.parseColor(color))
        actionBar?.setBackgroundDrawable(colorDrawable)
    }


}