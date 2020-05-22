package com.example.billeterasimple

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var transacciones = mutableListOf<Transaccion>()
    private lateinit var transaccionViewModel: TransaccionViewModel
    private val newTransaccionActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TransaccionListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        transaccionViewModel = ViewModelProvider(this).get(TransaccionViewModel::class.java)

        transaccionViewModel.allTransacciones.observe(this, Observer { transacciones ->
            // Update the cached copy of the words in the adapter.
            transacciones?.let { adapter.setTransacciones(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AgregarTransaccion::class.java)
            startActivityForResult(intent, newTransaccionActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == newTransaccionActivityRequestCode && resultCode == Activity.RESULT_OK) {

            var tipoestudiante = ""
            var monto = ""

            data?.getStringExtra("Tipo")?.let {
                tipoestudiante = it
            }
            data?.getStringExtra("Monto")?.let {
                monto = it
            }

            var transaccion =
                Transaccion(id = 0, tipoTransaccionId = tipoestudiante.toInt(), monto = monto)
            transaccionViewModel.insert(transaccion)
            add(transaccion)

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun add(transaction: Transaccion) {
        var objeto = mutableListOf(transaction)
        transacciones.addAll(objeto)
    }


}
