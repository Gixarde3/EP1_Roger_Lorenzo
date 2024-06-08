package com.example.gestiondeempleadosupemor

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


const val DATABASE_NAME ="MyDB"
const val TABLE_NAME="Empleados"
const val COL_NOMBRE = "nombre"
const val COL_FECHANAC = "fecha_nac"
const val COL_SALARIO = "salario"
const val COL_EMAIL = "email"
const val COL_CARGO = "cargo"
const val COL_ID = "id"

class DataBaseLITE(var context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE $TABLE_NAME " +
                "($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_NOMBRE VARCHAR(255)," +
                "$COL_FECHANAC DATE," +
                "$COL_SALARIO DOUBLE," +
                "$COL_EMAIL VARCHAR(255)," +
                "$COL_CARGO VARCHAR(255))"

        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertEmpleado(empleado: Empleado): kotlin.Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NOMBRE, empleado.nombre)
        cv.put(COL_FECHANAC, empleado.fechaNac)
        cv.put(COL_SALARIO, empleado.salario)
        cv.put(COL_EMAIL, empleado.email)
        cv.put(COL_CARGO, empleado.cargo)

        val result = db.insert(TABLE_NAME, null, cv)
        if (result != (-1).toLong())
            Toast.makeText(context, "Insertado Correctamente con el id $result", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Fallo", Toast.LENGTH_SHORT).show()

        return result
    }

    fun getEmpleados(): MutableList<Empleado>{
        val list : MutableList<Empleado> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()) do {
            var empleado = Empleado()
            empleado.id = result.getString(result.getColumnIndexOrThrow(COL_ID)).toInt()
            empleado.nombre = result.getString(result.getColumnIndexOrThrow(COL_NOMBRE))
            empleado.fechaNac = result.getString(result.getColumnIndexOrThrow(COL_FECHANAC))
            empleado.salario = result.getString(result.getColumnIndexOrThrow(COL_SALARIO)).toDouble()
            empleado.email = result.getString(result.getColumnIndexOrThrow(COL_EMAIL))
            empleado.cargo = result.getString(result.getColumnIndexOrThrow(COL_CARGO))
            list.add(empleado)
        } while (result.moveToNext())
        result.close()
        return list
    }


    fun getEmpleado(id: Int) : Empleado{
        var empleado = Empleado()
        val db = this.writableDatabase
        val query = "Select * from $TABLE_NAME where $COL_ID = $id"
        val result = db.rawQuery(query,null)
        result.moveToFirst()
        empleado.id = result.getString(result.getColumnIndexOrThrow(COL_ID)).toInt()
        empleado.nombre = result.getString(result.getColumnIndexOrThrow(COL_NOMBRE))
        empleado.fechaNac = result.getString(result.getColumnIndexOrThrow(COL_FECHANAC))
        empleado.salario = result.getString(result.getColumnIndexOrThrow(COL_SALARIO)).toDouble()
        empleado.email = result.getString(result.getColumnIndexOrThrow(COL_EMAIL))
        empleado.cargo = result.getString(result.getColumnIndexOrThrow(COL_CARGO))
        result.close()
        return empleado
    }

    fun updateEmpleado(empleado: Empleado): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NOMBRE, empleado.nombre)
        cv.put(COL_FECHANAC, empleado.fechaNac)
        cv.put(COL_SALARIO, empleado.salario)
        cv.put(COL_EMAIL, empleado.email)
        cv.put(COL_CARGO, empleado.cargo)
        val result = db.update(TABLE_NAME, cv, "$COL_ID = ?", arrayOf(empleado.id.toString()))
        return result
    }
    fun deleteEmpleado(id: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$COL_ID = ?", arrayOf(id.toString()))

        return result
    }
}