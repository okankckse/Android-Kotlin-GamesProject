package com.okankocakose.appcentproject1.Repository


import androidx.lifecycle.MutableLiveData
import com.okankocakose.appcentproject1.API.ApiClient
import com.okankocakose.appcentproject1.Model.GamesDataModel
import com.okankocakose.appcentproject1.Model.GamesDetailDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository  {
    private var getGamesLiveData: MutableLiveData<GamesDataModel?>
    private var getGamesDetailLiveData: MutableLiveData<GamesDetailDataModel?>

    init {
        this.getGamesLiveData = MutableLiveData<GamesDataModel?>()
        this.getGamesDetailLiveData = MutableLiveData<GamesDetailDataModel?>()
    }


 fun getGamesObserver() : MutableLiveData<GamesDataModel?> {
     return getGamesLiveData
 }

 fun getGamesDetailObserver() : MutableLiveData<GamesDetailDataModel?>{
        return getGamesDetailLiveData
    }




 fun getGamesDetail(id : Int){
        val retrofitData = ApiClient.getInstance()?.myApiInterface?.getGamesDetail(id,"e21c40f60da34e159141c3f15971fb9e")
        if (retrofitData != null) {
            retrofitData.enqueue(object : Callback<GamesDetailDataModel?> {
                override fun onResponse(call: Call<GamesDetailDataModel?>, response: Response<GamesDetailDataModel?>) {
                    if(response.isSuccessful ){

                        getGamesDetailLiveData.postValue(response.body())

                    }else{
                        getGamesDetailLiveData.postValue(null)
                    }


                }

                override fun onFailure(call: Call<GamesDetailDataModel?>, t: Throwable) {
                      getGamesDetailLiveData.postValue(null)
                }
            })
        }
    }


 fun getGames() {
     val retrofitData = ApiClient.getInstance()?.myApiInterface?.getGames("e21c40f60da34e159141c3f15971fb9e")
     if (retrofitData != null) {
         retrofitData.enqueue(object : Callback<GamesDataModel?> {
             override fun onResponse(call: Call<GamesDataModel?>, response: Response<GamesDataModel?>) {
                 if(response.isSuccessful){
                    getGamesLiveData.postValue(response.body())
                 }else{
                     getGamesLiveData.postValue(null)
                 }


             }

             override fun onFailure(call: Call<GamesDataModel?>, t: Throwable) {
                   getGamesLiveData.postValue(null)
             }
         })
     }

 }
}