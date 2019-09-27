package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import ericdiaz.program.currencyconveterlive2019.R

class DialPad(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    lateinit var onDialPressedListener: OnDialPressedListener

    override fun onFinishInflate() {
        super.onFinishInflate()
        val number1TextView: TextView = findViewById(R.id.number_1_text_view)
        val number2TextView: TextView = findViewById(R.id.number_2_text_view)
        val number3TextView: TextView = findViewById(R.id.number_3_text_view)
        val number4TextView: TextView = findViewById(R.id.number_4_text_view)
        val number5TextView: TextView = findViewById(R.id.number_5_text_view)
        val number6TextView: TextView = findViewById(R.id.number_6_text_view)
        val number7TextView: TextView = findViewById(R.id.number_7_text_view)
        val number8TextView: TextView = findViewById(R.id.number_8_text_view)
        val number9TextView: TextView = findViewById(R.id.number_9_text_view)
        val dotTextView: TextView = findViewById(R.id.dot_text_view)
        val zeroTextView: TextView = findViewById(R.id.zero_text_view)
        val deleteImageView: ImageView = findViewById(R.id.delete_image_view)

        number1TextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.One) }
        number2TextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Two) }
        number3TextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Three) }
        number4TextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Four) }
        number5TextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Five) }
        number6TextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Six) }
        number7TextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Seven) }
        number8TextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Eight) }
        number9TextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Nine) }
        dotTextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Dot) }
        zeroTextView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Zero) }
        deleteImageView.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Delete) }
    }
}

interface OnDialPressedListener {
    fun onDialPressed(dial: Dial)
}