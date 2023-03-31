package fr.isen.bernhard.androiderestaurant

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.isen.bernhard.androiderestaurant.databinding.ActivityShoppingBinding
import fr.isen.bernhard.androiderestaurant.model.DisheDeserialized
import fr.isen.bernhard.androiderestaurant.rss.reWriteLine
import fr.isen.bernhard.androiderestaurant.rss.readLineNumber
import fr.isen.bernhard.androiderestaurant.rss.readNumberOfLine
import org.w3c.dom.Text


class ShoppingActivity: AppCompatActivity(){

    private val tag = "ShoppingActivity"
    private lateinit var binding: ActivityShoppingBinding
    private var total = 0.0
    private var total1 = 0.0
    private var total2 = 0.0
    private var total3 = 0.0
    private var total4 = 0.0
    private var total5 = 0.0
    private var total6 = 0.0
    private var total7 = 0.0
    private var total8 = 0.0
    private var total9 = 0.0
    private var total10 = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Nombre de ligne dans le fichier
        val numberOfLine =readNumberOfLine(this)

        //Itere pour chaque ligne
        for (i in 1 until numberOfLine){
           var text: String? = readLineNumber(this,i)
            var text1: String?= null
            var text2: String?= null
            var price:Double = 0.0

            text1 = traitementTexteLeftSide(text)
            text2 = traitementTexteRightSide(text)
            price = returnPrice(text)

            when(i){
                1-> {binding.plat1.text = text1
                    binding.qty1.text = "(x$text2)"
                    total1 = price * text2.toInt()
                }
                2-> {binding.plat2.text = text1
                    binding.qty2.text = "(x$text2)"
                    total2 = price * text2.toInt()
                }
                3-> {binding.plat3.text = text1
                    binding.qty3.text = "(x$text2)"
                    total3 = price * text2.toInt()
                }
                4-> {binding.plat4.text = text1
                    binding.qty4.text = "(x$text2)"
                    total4 = price * text2.toInt()
                }
                5-> {binding.plat5.text = text1
                    binding.qty5.text = "(x$text2)"
                    total5 = price * text2.toInt()
                }
                6-> { binding.plat6.text = text1
                    binding.qty6.text = "(x$text2)"
                    total6 = price * text2.toInt()

                }
                7-> {binding.plat7.text = text1
                    binding.qty7.text = "(x$text2)"
                    total7 = price * text2.toInt()
                }
                8-> {binding.plat8.text = text1
                    binding.qty8.text = "(x$text2)"
                    total8 = price * text2.toInt()
                }
                9->  {binding.plat9.text = text1
                    binding.qty9.text = "(x$text2)"
                    total9 = price * text2.toInt()
                }
                10-> {binding.plat10.text = text1
                    binding.qty10.text = "(x$text2)"
                    total10 = price * text2.toInt()
                }

                else -> Log.e(tag, "Error")

            }

            total = total1+total2+total3+total4+total5+
                    total6+total7+total8+total9+total10

            binding.buttonPay.text = total.toString()+" €"

        }

        binding.textViewMoins1.setOnClickListener {
            println("clik moins")
            //reWriteLine("NAME=Salade Lyonnaise;PRICE=11.0;QUANTITY=0", "Oli")
        }


    }

    fun traitementTexteLeftSide(string: String?): String{

        val list = string?.split(";")
        var msg1:String = ""
        var msg2:String = ""

        //println("DEBUG")
        //println(list)


        if (list != null) {
            for (i in list) {
                if (i.contains("NAME=")) {
                    msg1 = i.substringAfter("=")

                }
                if (i.contains("PRICE=")) {
                    msg2 = i.substringAfter("=")
                }
            }
        }
        //println(msg1)
        //println(msg2)
        return "$msg1: $msg2€"
    }

    fun traitementTexteRightSide(string: String?): String{

        val list = string?.split(";")
        var msg:String = ""

        if (list != null) {
            for (i in list) {
                if (i.contains("QUANTITY=")) {
                    msg = i.substringAfter("=")

                }

            }
        }
        return msg
    }

    fun returnPrice(string: String?): Double{

        val list = string?.split(";")
        var msg:String = ""

        if (list != null) {
            for (i in list) {

                if (i.contains("PRICE=")) {
                    msg = i.substringAfter("=")
                }
            }
        }

        return msg.toDouble()
    }


}
