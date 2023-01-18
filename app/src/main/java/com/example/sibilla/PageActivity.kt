package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PageActivity : AppCompatActivity() {

    private lateinit var question: EditText
    private lateinit var name: EditText
    private lateinit var surname: EditText
    private lateinit var hometown: EditText
    private lateinit var sender: Button

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

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        sender.setOnClickListener {
            if (question.text.isNotEmpty() &&
                name.text.isNotEmpty() &&
                surname.text.isNotEmpty() &&
                hometown.text.isNotEmpty()
            ) {
                var questionText = question.text.toString().split(" ")

                for (i in 0..questionText.size-1)
                {
                    var singleLetter= questionText[i].first().lowercase();
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

                var singleLetter= name.text.toString().first().lowercase();
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


                val intent = Intent(this, LoadingActivity::class.java)
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

    /*fun positionTaker(numberSum: IntArray, otherList: List<HomeFragment.Sibilla>): String{

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
                    Log.d("result1", firstRow1 +firstRow2)
                }
            }

            if(otherList[j].pos_tripla == "2"){
                if(otherList[j].pos1 == numberSum[1].toString()
                    && otherList[j].pos2 == numberSum[2].toString()
                    && otherList[j].pos3 == numberSum[0].toString()){
                    secondRow1 = otherList[j].stringa1;
                    secondRow2 = otherList[j].stringa2;

                    Log.d("result2", secondRow1 +secondRow2)


                }
            }

            if(otherList[j].pos_tripla == "3"){
                if(otherList[j].pos1 == numberSum[2].toString()
                    && otherList[j].pos2 == numberSum[0].toString()
                    && otherList[j].pos3 == numberSum[1].toString()){
                    thirdRow1 = otherList[j].stringa1;
                    thirdRow2 = otherList[j].stringa2;

                    Log.d("result3", thirdRow1 +thirdRow2)
                }
            }

            response = firstRow1 + secondRow1 + thirdRow1 + firstRow2 + secondRow2 + thirdRow2;
            Log.d("Response", response)
            j++;

        }while(j<otherList.size);
        return response.toString();
    }*/
}