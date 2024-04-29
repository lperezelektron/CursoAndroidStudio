package com.example.androidexercises.SuperHeroApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidexercises.R
import com.example.androidexercises.SuperHeroApp.DetailSuperHeroActivity.Companion.EXTRA_ID
import com.example.androidexercises.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchViewHeroes.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Se ejecuta cuando damos click a la lupita de busqueda del teclado
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            //Se comenta porque en este caso no nos interesa
            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = SuperHeroAdapter(){ heroId -> navigateToDetail(heroId) }
        binding.rvHeroes.setHasFixedSize(true)
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvHeroes.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.pbHero.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            //Todo lo que hagamos en estas llaves se ejecutará en un hilo secundario (Corrutina)
            val myResponse: Response<SuperHeroDataResponse> = retrofit.create(ApiService::class.java).getSuperHeroes(query)
            if (myResponse.isSuccessful){
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null){
                    Log.i("MemoDev", response.toString())
                    runOnUiThread{
                        //Esta función lo que hace es que ejecuta lo que esta dentro en el hilo principal y no en la corrutina, ya que hacer eso sería muy malo y causaria errores fatales
                        adapter.updateList(response.superheroes)
                        binding.pbHero.isVisible = false
                    }
                }
            }else{
                Log.i("MemoDev", "No funciona :(")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        //Creamos y regresamos un objeto retrofit
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String){
        val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)
    }
}