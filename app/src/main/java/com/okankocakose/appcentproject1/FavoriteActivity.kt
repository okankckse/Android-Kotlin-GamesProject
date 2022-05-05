package com.okankocakose.appcentproject1


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.okankocakose.appcentproject1.Adapters.RecyclerViewAdapter2
import com.okankocakose.appcentproject1.databinding.ActivityFavoriteBinding


class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var recyclerViewAdapter2 : RecyclerViewAdapter2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        //RecyclerView Adapter
        binding.recyclerView2.layoutManager = LinearLayoutManager(applicationContext)
        recyclerViewAdapter2 = RecyclerViewAdapter2(DetailsActivity.list)
        binding.recyclerView2.adapter = recyclerViewAdapter2


        //Bottom Nav Selected Item
        binding.bottomNavigator.selectedItemId = R.id.favorite

        binding.bottomNavigator.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.home){
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    finish()
                    overridePendingTransition(0,0)
                    return true
                }else{
                    return true
                }
            }
        })



    }

}