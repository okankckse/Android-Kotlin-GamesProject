package com.okankocakose.appcentproject1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.okankocakose.appcentproject1.Model.GamesDetailDataModel
import com.okankocakose.appcentproject1.ViewModel.ApiViewModel
import com.okankocakose.appcentproject1.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso



class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val apiViewModel: ApiViewModel by viewModels()
    private lateinit var name : String
    companion object {
        var list: ArrayList<String> = arrayListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val id = intent.getIntExtra("id",0)

        //Api Call
        apiViewModel.getGamesDetail(id)

        apiViewModel.getGamesDetailObserver().observe(this,object : Observer<GamesDetailDataModel?> {
            override fun onChanged(t: GamesDetailDataModel?) {
                if(t == null){

                    Toast.makeText(applicationContext,"Failed",Toast.LENGTH_LONG,).show()

                }else{
                    binding.imageView.visibility=View.VISIBLE
                    Picasso.get().load(t.background_image).into(binding.DetailsActivityImageView)
                    binding.nameOfGame.text = t.name
                    binding.releaseDate.text = t.released
                    binding.metacriticRate.text = t.metacritic.toString()
                    binding.description.text = t.description

                    binding.imageView.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(v: View?) {
                             name = t.name
                             if(!list.contains(name)){
                                list.add(name)

                            }

                        }
                    })
                }
            }
        } )



    }


}
