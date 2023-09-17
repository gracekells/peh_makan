package com.dicoding.pehmakan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMakanan: RecyclerView
    private val list = ArrayList<Makanan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setContentView(R.layout.activity_main)

        rvMakanan = findViewById(R.id.rv_makanan)
        rvMakanan.setHasFixedSize(true)

        list.addAll(getListMakanan())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_about -> {
                val intent = Intent(this, AboutPage::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListMakanan(): ArrayList<Makanan>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listMakanan = ArrayList<Makanan>()
        for (i in dataName.indices){
            val makanan = Makanan(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listMakanan.add(makanan)
        }
        return listMakanan
    }

    private fun showRecyclerList(){
        rvMakanan.layoutManager = LinearLayoutManager(this)
        val listMakananAdapter = ListMakananAdapter(list)
        rvMakanan.adapter = listMakananAdapter

        listMakananAdapter.setOnItemClickCallback(object : ListMakananAdapter.OnItemClickCallback{
            override fun onItemClicked(data:Makanan){
                showSelecetedMakanan(data)
            }
        })
    }

    private fun showSelecetedMakanan(makanan: Makanan){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("MAKANAN", makanan)
        startActivity(intent)
    }

}