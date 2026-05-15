package com.example.jalsanchaytracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var totalWater = 0.0
    var historyText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etArea =
            findViewById<EditText>(R.id.etArea)

        val etRainfall =
            findViewById<EditText>(R.id.etRainfall)

        val btnCalculate =
            findViewById<Button>(R.id.btnCalculate)

        val btnReset =
            findViewById<Button>(R.id.btnReset)

        val txtResult =
            findViewById<TextView>(R.id.txtResult)

        val txtTotal =
            findViewById<TextView>(R.id.txtTotal)

        val txtHistory =
            findViewById<TextView>(R.id.txtHistory)
        val txtMonthly =
            findViewById<TextView>(R.id.txtMonthly)

        val progressTank =
            findViewById<ProgressBar>(R.id.progressTank)
        val progressEfficiency =
            findViewById<ProgressBar>(
                R.id.progressEfficiency
            )

        btnCalculate.setOnClickListener {

            val areaText =
                etArea.text.toString()

            val rainfallText =
                etRainfall.text.toString()

            if(areaText.isEmpty() ||
                rainfallText.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val area =
                areaText.toDouble()

            val rainfall =
                rainfallText.toDouble()

            val result =
                area * rainfall * 0.0929 * 0.8

            totalWater += result

            txtResult.text =
                "Collected Water:\n$result Liters"

            txtTotal.text =
                "Total Savings: $totalWater Liters"

            historyText +=
                "\nArea: $area" +
                        "\nRainfall: $rainfall" +
                        "\nSaved: $result Liters\n"

            txtHistory.text = historyText

            txtMonthly.text =
                "Monthly Report: $totalWater Liters Saved"

            val percentage =
                (result / 500 * 100).toInt()

            progressTank.progress =
                percentage

            progressEfficiency.progress =
                (result / 10).toInt()
        }

        btnReset.setOnClickListener {

            etArea.text.clear()

            etRainfall.text.clear()

            txtResult.text = "Result"

            txtTotal.text =
                "Total Savings: 0 Liters"

            progressTank.progress = 0
            progressEfficiency.progress = 0

            totalWater = 0.0
        }
    }
}