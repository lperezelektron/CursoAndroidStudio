package com.example.androidexercises.SuperHeroApp

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidexercises.R
import com.example.androidexercises.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail = getRetrofit().create(ApiService::class.java).getSuperHeroInfo(id)

            if (superHeroDetail.body() != null) {
                runOnUiThread { createUI(superHeroDetail.body()!!) }
            } else {

            }
        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperHeroDetailImage)
        binding.tvSuperHeroName.text = superhero.name
        prepareStats(superhero.powerstats)
        binding.tvSuperHeroFullName.text = superhero.biography.fullName
        binding.tvSuperHeroPublisher.text = superhero.biography.publisher
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun prepareStats(powerStats: PowerStatsResponse) {
        updateHeight(binding.viewIntelligence, powerStats.intelligence.toInt())
        updateHeight(binding.viewStrength, powerStats.strength.toInt())
        updateHeight(binding.viewSpeed, powerStats.speed.toInt())
        updateHeight(binding.viewDurability, powerStats.durability.toInt())
        updateHeight(binding.viewPower, powerStats.power.toInt())
        updateHeight(binding.viewCombat, powerStats.combat.toInt())
    }

    private fun updateHeight(view: View, stat: Int) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float): Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

}