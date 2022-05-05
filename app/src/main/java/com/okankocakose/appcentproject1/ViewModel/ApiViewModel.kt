package com.okankocakose.appcentproject1.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okankocakose.appcentproject1.Model.GamesDataModel
import com.okankocakose.appcentproject1.Model.GamesDetailDataModel
import com.okankocakose.appcentproject1.Repository.ApiRepository

class ApiViewModel : ViewModel(){

   private var apiRepository: ApiRepository
   private var getGamesLiveData: MutableLiveData<GamesDataModel?>
   private var getGamesDetailLiveData: MutableLiveData<GamesDetailDataModel?>

   init {
       apiRepository = ApiRepository()
       getGamesLiveData = MutableLiveData<GamesDataModel?>()
       getGamesDetailLiveData = MutableLiveData<GamesDetailDataModel?>()
   }


    fun getGamesObserver() : MutableLiveData<GamesDataModel?> {
        return apiRepository.getGamesObserver()
    }

    fun getGamesDetailObserver() : MutableLiveData<GamesDetailDataModel?>{
        return apiRepository.getGamesDetailObserver()
    }

    fun getGames(){
        apiRepository.getGames()
    }

    fun getGamesDetail(id: Int){
        apiRepository.getGamesDetail(id)
    }
}