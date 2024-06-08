package com.example.gestiondeempleadosupemor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnConsultar = findViewById<Button>(R.id.btnConsultar)

        btnRegistrar.setOnClickListener {
            // Registrar
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }
        btnConsultar.setOnClickListener {
            // Consultar
            val intent = Intent(this, ConsultarActivity::class.java)
            startActivity(intent)
        }
    }
}