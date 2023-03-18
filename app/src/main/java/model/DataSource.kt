package model

import com.google.gson.annotations.SerializedName


data class DataSource (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

)