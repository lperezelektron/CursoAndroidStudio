package com.example.androidexercises

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidexercises.ImcCalculator.imcCalculatorActivity
import com.example.androidexercises.Saludo.MainActivity
import com.example.androidexercises.ToDoApp.ToDoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSaludApp = findViewById<AppCompatButton>(R.id.btnSaludarApp)
        val btnIMCApp = findViewById<AppCompatButton>(R.id.btnIMCApp)
        val btnToDo = findViewById<AppCompatButton>(R.id.btnToDo)
        btnSaludApp.setOnClickListener{ navigateToSaludarApp() }
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        btnToDo.setOnClickListener { navigateToAppToDo() }
    }

    private fun navigateToSaludarApp(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIMCApp(){
        val intent = Intent(this, imcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAppToDo() {
        val intent = Intent(this, ToDoActivity::class.java)
        startActivity(intent)
    }
}