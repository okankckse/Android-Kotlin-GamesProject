package com.okankocakose.appcentproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.okankocakose.appcentproject1.Adapters.RecyclerViewAdapter1
import com.okankocakose.appcentproject1.Adapters.ViewPagerAdapter
import com.okankocakose.appcentproject1.Model.GamesDataModel
import com.okankocakose.appcentproject1.Model.Result
import com.okankocakose.appcentproject1.ViewModel.ApiViewModel
import com.okankocakose.appcentproject1.ViewPagerFragments.ViewPagerFirstFragment
import com.okankocakose.appcentproject1.ViewPagerFragments.ViewPagerSecondFragment
import com.okankocakose.appcentproject1.ViewPagerFragments.ViewPagerThirdFragment
import com.okankocakose.appcentproject1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiViewModel: ApiViewModel by viewModels()
    private lateinit var nameList : ArrayList<Result>
    private lateinit var recyclerViewAdapter1 : RecyclerViewAdapter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       //Bottom Nav Selected Item
        binding.bottomNavigator.selectedItemId = R.id.home

        binding.bottomNavigator.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
          override fun onNavigationItemSelected(item: MenuItem): Boolean {
             if (item.itemId == R.id.favorite){
                 startActivity(Intent(applicationContext,FavoriteActivity::class.java))
                 finish()
                 overridePendingTransition(0,0)
                 return true
             }else{
                 return true
             }
          }
      })


        //Fragment List of ViewPager
        val fragments : ArrayList<Fragment> = arrayListOf(
            ViewPagerFirstFragment(),
            ViewPagerSecondFragment(),
            ViewPagerThirdFragment(),
        )


        //Binding ViewPagerAdapter
        val adapter = ViewPagerAdapter(fragments,this)
        binding.viewPager2.adapter = adapter



        //Api Call
        apiViewModel.getGames()

        apiViewModel.getGamesObserver().observe(this, object : Observer<GamesDataModel?> {
           override fun onChanged(t: GamesDataModel?) {
               if(t == null){

                   Toast.makeText(applicationContext,"Failed",Toast.LENGTH_LONG,).show()

               }else{
                   nameList = arrayListOf()
                   for (item in t.results){
                       nameList.add(item)
                   }

                   binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                   recyclerViewAdapter1 = RecyclerViewAdapter1(nameList)
                   binding.recyclerView.adapter = recyclerViewAdapter1

               }
           }
        })


        //SearchView for Update RecyclerView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               if(newText?.length!! > 3){
                    filter(newText);
                    binding.viewPager2.visibility = View.INVISIBLE
                    return true
                }else{
                    binding.resultTextView.visibility=View.INVISIBLE
                    recyclerViewAdapter1.filterList(nameList)
                    binding.viewPager2.visibility = View.VISIBLE
                    return true
                }

            }
        })



    }


   private fun filter(newText: String?) {
    val filteredList : ArrayList<Result> = arrayListOf()
     for (item in nameList){
         if(item.name.lowercase().contains(newText!!.lowercase())){
             filteredList.add(item)
         }
     }
       if (filteredList.size==0){
           binding.resultTextView.visibility=View.VISIBLE
           binding.viewPager2.visibility=View.INVISIBLE
       }else{
           binding.resultTextView.visibility=View.INVISIBLE
           binding.viewPager2.visibility=View.VISIBLE

       }
     recyclerViewAdapter1.filterList(filteredList)
    }



}