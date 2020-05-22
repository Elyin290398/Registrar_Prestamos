package com.example.billeterasimple

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import kotlinx.android.synthetic.main.activity_agregar_transaccion.*

class AgregarTransaccion : AppCompatActivity() {

    private lateinit var editTransaccionView: EditText
    private lateinit var tipoTransaccionViewModel: TipoTransaccionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_transaccion)
        //Llenar spinner
        cargarSpinner()
        //
        editTransaccionView = findViewById(R.id.etLibro)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(editTransaccionView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                var tipo = 1
                var movimiento = spTipoTransaccion.selectedItem.toString()
                    if (movimiento.equals("Est.Primaria")) {
                        tipo = 1
                    } else {
                        tipo = 2
                    }
                val monto = editTransaccionView.text.toString()
                replyIntent.putExtra("Monto", monto)
                replyIntent.putExtra("Tipo", tipo.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

   fun validarDatos() {
        if (etLibro.text.toString().isEmpty()) {
            Toast.makeText(this, "Todos los datos son requeridos",
                Toast.LENGTH_SHORT).show()
        }
        else {
        }
    }

    fun cargarSpinner(){
        var ingresos = TipoTransaccion(1, "Est.Primaria")
        var egresos = TipoTransaccion(2, "Est.Secundaria")
        var opcionesTipoTrasaccion = listOf(ingresos.tipo, egresos.tipo)
        var adapterTipo = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            opcionesTipoTrasaccion
        )
        spTipoTransaccion.adapter = adapterTipo
    }


}
