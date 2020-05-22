package com.example.billeterasimple

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class TransaccionListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TransaccionListAdapter.transaccionViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var transacciones = emptyList<Transaccion>() // Cached copy of words

    inner class transaccionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val transaccionItemView: TextView = itemView.findViewById(R.id.textView2)
        val transaccionItemView1: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): transaccionViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return transaccionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: transaccionViewHolder, position: Int) {
        val current = transacciones[position]
        var tipotransaccion=""
        holder.transaccionItemView.text = current.monto.toString()

        if (current.tipoTransaccionId==1)
        {tipotransaccion="Est.Primaria"}
        else{tipotransaccion="Est.Secundaria"}
        holder.transaccionItemView1.text=tipotransaccion
    }

    internal fun setTransacciones(transacciones: List<Transaccion>) {
        this.transacciones = transacciones
        notifyDataSetChanged()
    }

    override fun getItemCount() = transacciones.size
   /* fun calcular():Double{
        var ingresos =
            this.transacciones.filter { it.tipoTransaccionId == 1 }.sumByDouble { it.monto }
        var egresos =
            this.transacciones.filter { it.tipoTransaccionId == 2 }.sumByDouble { it.monto }
       var total = ingresos - egresos
        return total
    }*/
}