package com.dicoding.pehmakan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.pehmakan.databinding.ActivityDetailBinding
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*

class DetailActivity : AppCompatActivity (){
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val makanan = intent.getParcelableExtra<Makanan>("MAKANAN")
        makanan?.let {
            binding.imgDetailPhoto.setImageResource(it.photo)
            binding.tvDetailName.text = it.name
            binding.tvDetailDescription.text = it.description
        }

    }
}