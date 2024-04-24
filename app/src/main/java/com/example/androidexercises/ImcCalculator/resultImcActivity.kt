package com.example.androidexercises.ImcCalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidexercises.ImcCalculator.imcCalculatorActivity.Companion.IMC_KEY
import com.example.androidexercises.R

@Suppress("DEPRECATION")
class resultImcActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvImc: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnReCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initComponents() {
        tvImc = findViewById(R.id.tvImc)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }

    private fun initUI(result: Double) {
        tvImc.text = result.toString()
        when(result){
            in 0.00..18.50 -> { // Peso bajo
                tvResult.text = getString(R.string.title_peso_bajo)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_peso_bajo)
            }
            in 18.51..24.99 -> { // Peso Normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvDescription.text = getString(R.string.description_peso_normal)
            }
            in 25.00..29.99 -> { // Sobrepeso
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                tvDescription.text = getString(R.string.description_sobrepeso)
            }
            in 30.00..99.99 -> { // Obesidad
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_obesidad))
                tvDescription.text = getString(R.string.description_obesidad)
            }
            else -> { //Error
                tvImc.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener { onBackPressed() }
    }

}