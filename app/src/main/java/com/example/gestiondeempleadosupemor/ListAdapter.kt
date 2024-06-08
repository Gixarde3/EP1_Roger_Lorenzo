package com.example.gestiondeempleadosupemor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListEmpleadosAdapter(val empleados: List<Empleado>) : RecyclerView.Adapter<ListEmpleadosAdapter.EmpleadoViewHolder>() {

    class EmpleadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val empleadoNombre: TextView = itemView.findViewById(R.id.viewNombre)
        val empleadoCargo: TextView = itemView.findViewById(R.id.viewCargo)
        val empleadoEmail: TextView = itemView.findViewById(R.id.viewEmail)
        val empleadoSalario: TextView = itemView.findViewById(R.id.viewSalario)
        val empleadoFechaNac: TextView = itemView.findViewById(R.id.viewFechaNac)
        var idEmpleado: Int = 0

        init {
            itemView.setOnClickListener {
                //TODO OnClick
                val intent = Intent(itemView.context, VerEmpleadoActivity::class.java)
                intent.putExtra("idEmpleado", idEmpleado.toString())
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpleadoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_empleado_item, null, false)
        return EmpleadoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmpleadoViewHolder, position: Int) {
        val empleado = empleados.get(position)
        holder.empleadoNombre.text = empleado.nombre
        holder.empleadoCargo.text = empleado.cargo
        holder.empleadoEmail.text = empleado.email
        holder.empleadoSalario.text = empleado.salario.toString()
        holder.empleadoFechaNac.text = empleado.fechaNac
        holder.idEmpleado = empleado.id
    }

    override fun getItemCount(): Int {
        return empleados.size
    }
}