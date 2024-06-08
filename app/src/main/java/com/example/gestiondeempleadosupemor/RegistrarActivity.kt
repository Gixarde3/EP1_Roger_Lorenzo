package com.example.gestiondeempleadosupemor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarEmpleado)
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtFecha = findViewById<EditText>(R.id.txtFecha)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtCargo = findViewById<EditText>(R.id.txtCargo)
        val txtSalario = findViewById<EditText>(R.id.txtSalario)

        val db = DataBaseLITE(this)

        btnRegistrar.setOnClickListener {
            val empleado = Empleado()
            empleado.nombre = txtNombre.text.toString()
            empleado.fechaNac = txtFecha.text.toString()
            empleado.email = txtEmail.text.toString()
            empleado.cargo = txtCargo.text.toString()
            empleado.salario = txtSalario.text.toString().toDouble()

            db.insertEmpleado(empleado)

            val intent = Intent(this, ConsultarActivity::class.java)
            startActivity(intent)
        }
    }
}