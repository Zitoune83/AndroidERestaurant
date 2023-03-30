package fr.isen.bernhard.androiderestaurant.model

class DisheDeserialized ( var quantity: Int = 0){
    var nameFr:String? = null
    var listIngredients: ArrayList<String> = ArrayList()
    var listURL:ArrayList<String> = ArrayList()
    var prices:Double = 0.0
}