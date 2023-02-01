package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class PageActivity : AppCompatActivity() {

    private lateinit var question: EditText
    private lateinit var name: EditText
    private lateinit var surname: EditText
    private lateinit var hometown: EditText
    private lateinit var sender: Button
    private lateinit var faq: ImageButton
    private lateinit var shop: LinearLayout

    private var letters = arrayOf("kqwyx", "huv", "ers", "mt", "lo", "ag", "ijn", "cf", "dz", "pb");
    private var numbers = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private var firstLetter = 0
    private var lastLetter = 0
    private var answerStarts = mutableListOf(0)
    private var sum1 = 0
    private var sum2 = 0
    private var sum3 = 0
    private var t1 = mutableListOf(0, 0, 0)
    private var t2 = mutableListOf(0, 0, 0)
    private var t3 = mutableListOf(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        question = findViewById(R.id.answer)
        name = findViewById(R.id.firstName)
        surname = findViewById(R.id.lastName)
        hometown = findViewById(R.id.bornPlace)
        sender = findViewById(R.id.sender)
        faq = findViewById(R.id.faq)
        shop = findViewById(R.id.shop)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        faq.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
            finish()
        }

        shop.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
            finish()
        }

        sender.setOnClickListener {
            if (question.text.isNotEmpty() &&
                name.text.isNotEmpty() &&
                surname.text.isNotEmpty() &&
                hometown.text.isNotEmpty()
            ) {
                Log.d("domandaStart", question.text.toString())
                var last = question.text.toString().last()
                Log.d("last", last.toString())
                var test : CharSequence
                var questionText : List<String>

                if (last.toString() == " ") {
                    test = question.text.subSequence(0, question.length()-1)
                    Log.d("domandaSpazioMeno", test.toString())
                    questionText = test.toString().split(" ")
                } else {
                    questionText = question.text.toString().split(" ")
                }

                Log.d("domandaDefinitiva", questionText.toString())

                for (element in questionText)
                {
                    var singleLetter= element.first().lowercase();
                    var j = 0;
                    do {
                        var k = 0;
                        var finded = false;
                        do{
                            if(letters[j].get(k).toString() == singleLetter.toString()){
                                sum1 += numbers[j];
                                answerStarts.add(numbers[j])
                                Log.d("numbers", numbers[j].toString());
                                finded = true;
                            }
                            else{
                                k++;
                            }
                        }while(k < letters[j].length && !finded);
                        j++;
                    }while(j<letters.size && !finded);
                }
                if (sum1 >= 10){
                    sum1 = sum1 % 10;
                }
                Log.d("numbers", answerStarts.toString())
                firstLetter = answerStarts[1]
                Log.d("first", firstLetter.toString())
                lastLetter = answerStarts.last()
                Log.d("last", lastLetter.toString())

                var singleLetter = name.text.toString().first().lowercase();
                var nameSum = cicleLetters(singleLetter);
                Log.d("nameSum", nameSum.toString())
                singleLetter= surname.text.toString().first().lowercase();
                var surnameSum = cicleLetters(singleLetter);
                Log.d("surnameSum", surnameSum.toString())
                singleLetter= hometown.text.toString().first().lowercase();
                var hometownSum = cicleLetters(singleLetter);
                Log.d("hometownSum", hometownSum.toString())

                Log.d("somma 1", sum1.toString())

                sum2 = nameSum + surnameSum + hometownSum + lastLetter
                if (sum2 >= 10){
                    sum2 = sum2 % 10;
                }
                Log.d("somma 2", sum2.toString())

                sum3 = nameSum + surnameSum + firstLetter + lastLetter
                if (sum3 >= 10){
                    sum3 = sum3 % 10;
                }
                Log.d("somma 3", sum3.toString())

                sum1.let {
                    t1[0] = it
                    t2[2] = it
                    t3[1] = it
                }
                sum2.let {
                    t1[1] = it
                    t2[0] = it
                    t3[2] = it
                }
                sum3.let {
                    t1[2] = it
                    t2[1] = it
                    t3[0] = it
                }
                Log.d("tripletta 1", t1.toString())
                Log.d("tripletta 2", t2.toString())
                Log.d("tripletta 3", t3.toString())

                val gson = GsonBuilder().create()
                var jsonString = assets.open("sibilla.json").bufferedReader().use {
                    it.readText()
                }
                Log.i("data", jsonString)
                val sType = object : TypeToken<List<Sibilla>>() {}.type
                val otherList: List<Sibilla> = gson.fromJson(jsonString, sType)

                positionTaker(t1, otherList)
                val finalString = positionTaker(t1, otherList);
                Log.d("finalString", finalString)


                val intent = Intent(this, LoadingActivity::class.java)
                intent.putExtra("finalString",finalString);
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Check empty fields", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
        }
    }

    fun cicleLetters(singleLetter:String): Int{
        var numberSum = 0;
        var j = 0;
        do {
            var k = 0;
            var finded = false;
            do{
                if(letters[j].get(k).toString() == singleLetter.toString()){
                    numberSum += numbers[j];
                    finded = true;
                }
                else{
                    k++;
                }
            }while(k < letters[j].length && !finded);
            j++;
        }while(j<letters.size && !finded);
        if (numberSum >= 10){
            numberSum = numberSum % 10;
        }
        return numberSum
    }

    fun positionTaker(numberSum: MutableList<Int>, otherList: List<Sibilla>): String{

        var j = 0;
        var firstRow1: String? = "";
        var firstRow2: String? = "";
        var secondRow1: String? = "";
        var secondRow2: String? = "";
        var thirdRow1: String? = "";
        var thirdRow2: String? = "";
        var response: String? = "";
        do{
            if(otherList[j].pos_tripla == "1"){
                if(otherList[j].pos1 == numberSum[0].toString()
                    && otherList[j].pos2 == numberSum[1].toString()
                    && otherList[j].pos3 == numberSum[2].toString()){
                    firstRow1 = otherList[j].stringa1;
                    firstRow2 = otherList[j].stringa2;
                }
            }

            if(otherList[j].pos_tripla == "2"){
                if(otherList[j].pos1 == numberSum[1].toString()
                    && otherList[j].pos2 == numberSum[2].toString()
                    && otherList[j].pos3 == numberSum[0].toString()){
                    secondRow1 = otherList[j].stringa1;
                    secondRow2 = otherList[j].stringa2;
                }
            }

            if(otherList[j].pos_tripla == "3"){
                if(otherList[j].pos1 == numberSum[2].toString()
                    && otherList[j].pos2 == numberSum[0].toString()
                    && otherList[j].pos3 == numberSum[1].toString()){
                    thirdRow1 = otherList[j].stringa1;
                    thirdRow2 = otherList[j].stringa2;
                }
            }

            response = firstRow1 + secondRow1 + thirdRow1 + firstRow2 + secondRow2 + thirdRow2;
            j++;

        }while(j<otherList.size);
        return response.toString();
    }
}