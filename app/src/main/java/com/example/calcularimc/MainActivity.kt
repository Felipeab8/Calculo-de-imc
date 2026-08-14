package com.example.calcularimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextPeso: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var buttonCalcularIMC: Button
    private lateinit var textViewResultado: TextView
    private lateinit var recyclerViewHistory: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private val historyList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa os componentes da UI
        editTextPeso = findViewById(R.id.edit_text_peso)
        editTextAltura = findViewById(R.id.edit_text_altura)
        buttonCalcularIMC = findViewById(R.id.button_calcular_imc)
        textViewResultado = findViewById(R.id.text_view_resultado)
        recyclerViewHistory = findViewById(R.id.recycler_view_history)

        // Configura o RecyclerView
        historyAdapter = HistoryAdapter(historyList)
        recyclerViewHistory.adapter = historyAdapter
        recyclerViewHistory.layoutManager = LinearLayoutManager(this)

        // Configura o botão de cálculo do IMC
        buttonCalcularIMC.setOnClickListener {
            val peso = editTextPeso.text.toString().toDoubleOrNull()
            val altura = editTextAltura.text.toString().toDoubleOrNull()

            if (peso != null && altura != null) {
                val imc = calcularIMC(peso, altura)
                val classificacao = obterClassificacaoIMC(imc)
                textViewResultado.text = "Seu IMC é: $imc\nClassificação: $classificacao"

                // Adiciona o resultado ao histórico
                historyAdapter.addToHistory("Seu IMC é: $imc\nClassificação: $classificacao")
            } else {
                textViewResultado.text = "Por favor, insira um peso e altura válidos."
            }
        }
    }

    private fun calcularIMC(peso: Double, altura: Double): Double {
        return peso / (altura * altura)
    }

    private fun obterClassificacaoIMC(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 24.9 -> "Peso normal"
            imc < 29.9 -> "Sobrepeso"
            imc < 34.9 -> "Obesidade grau I"
            imc < 39.9 -> "Obesidade grau II"
            else -> "Obesidade grau III"
        }
    }
}
