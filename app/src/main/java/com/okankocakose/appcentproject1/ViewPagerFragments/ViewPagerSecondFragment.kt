package com.okankocakose.appcentproject1.ViewPagerFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.okankocakose.appcentproject1.DetailsActivity
import com.okankocakose.appcentproject1.Model.GamesDataModel
import com.okankocakose.appcentproject1.ViewModel.ApiViewModel
import com.okankocakose.appcentproject1.databinding.FragmentViewPagerSecondBinding
import com.squareup.picasso.Picasso


class ViewPagerSecondFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerSecondBinding
    private val apiViewModel : ApiViewModel by activityViewModels()
    private var id : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentViewPagerSecondBinding.inflate(inflater,container,false)

        //Observe changing
        apiViewModel.getGamesObserver().observe(viewLifecycleOwner,object :
            Observer<GamesDataModel?> {
            override fun onChanged(t: GamesDataModel?) {
                if (t==null){
                    Toast.makeText(activity,"Server Hata", Toast.LENGTH_LONG).show()
                }else{
                    Picasso.get().load(t.results.get(1).background_image).into(binding.secondFragmentImageView)
                    id= t.results.get(1).id
                }
            }
        })


        binding.secondFragmentImageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent : Intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }
        })

        return binding.root
    }


}