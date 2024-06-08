package com.example.gestiondeempleadosupemor

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ConsultarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_consultar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewEmpleados = findViewById<RecyclerView>(R.id.viewEmpleados)

        val context = this
        var db = DataBaseLITE(context)

        val empleados = db.getEmpleados()

        val adapter = ListEmpleadosAdapter(empleados)

        viewEmpleados.layoutManager = LinearLayoutManager(context)
        viewEmpleados.adapter = adapter
    }
}