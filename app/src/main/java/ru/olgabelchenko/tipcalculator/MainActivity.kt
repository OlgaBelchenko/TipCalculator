package ru.olgabelchenko.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.slider.Slider
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var slider: Slider
    private lateinit var textView: TextView

    private var billAmount = 0.0
    private var tipPercent = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_text)
        slider = findViewById(R.id.slider)
        textView = findViewById(R.id.text_view)

        editText.addTextChangedListener {

            val editTextValue = editText.text.toString()
            billAmount = if (editTextValue == "") {
                0.0
            } else {
                editTextValue.toDouble()
            }

            updateTextView()

        }

        slider.addOnChangeListener { slider, _, _ ->

            tipPercent = slider.value.toInt()
            updateTextView()

        }
    }

    private fun updateTextView() {

        val tip = BigDecimal(billAmount * tipPercent / 100).setScale(2, RoundingMode.HALF_UP)
        if (billAmount != 0.0) {
            textView.text = "Tip amount: $tip"
        } else {
            textView.text = ""
        }
    }
}