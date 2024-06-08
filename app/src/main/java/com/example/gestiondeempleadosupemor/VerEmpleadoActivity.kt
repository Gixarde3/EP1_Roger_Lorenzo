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

class VerEmpleadoActivity : AppCompatActivity() {

    var idEmpleado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_empleado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEditar = findViewById<Button>(R.id.btnEditar)
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtFecha = findViewById<EditText>(R.id.txtFecha)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtCargo = findViewById<EditText>(R.id.txtCargo)
        val txtSalario = findViewById<EditText>(R.id.txtSalario)

        val btnEliminar = findViewById<Button>(R.id.btnEliminar)

        val db = DataBaseLITE(this)

        idEmpleado = intent.getStringExtra("idEmpleado")?.toInt() ?: -2


        if (idEmpleado == -2) {
            Toast.makeText(this, "Ocurrió un error en el envío de ID entre actividades", Toast.LENGTH_SHORT).show()
        }

        val empleado = db.getEmpleado(idEmpleado)

        txtNombre.setText(empleado.nombre)
        txtFecha.setText(empleado.fechaNac)
        txtEmail.setText(empleado.email)
        txtCargo.setText(empleado.cargo)
        txtSalario.setText(empleado.salario.toString())

        btnEditar.setOnClickListener {
            empleado.nombre = txtNombre.text.toString()
            empleado.fechaNac = txtFecha.text.toString()
            empleado.email = txtEmail.text.toString()
            empleado.cargo = txtCargo.text.toString()
            empleado.salario = txtSalario.text.toString().toDouble()

            db.updateEmpleado(empleado)

            Toast.makeText(this, "Empleado actualizado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ConsultarActivity::class.java)
            startActivity(intent)
        }

        btnEliminar.setOnClickListener {
            db.deleteEmpleado(idEmpleado)
            Toast.makeText(this, "Empleado eliminado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ConsultarActivity::class.java)
            startActivity(intent)
        }
    }
}