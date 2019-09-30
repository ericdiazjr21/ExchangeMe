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

        findViewById<TextView>(R.id.number_1_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.One) }
        findViewById<TextView>(R.id.number_2_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Two) }
        findViewById<TextView>(R.id.number_3_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Three) }

        findViewById<TextView>(R.id.number_4_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Four) }
        findViewById<TextView>(R.id.number_5_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Five) }
        findViewById<TextView>(R.id.number_6_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Six) }

        findViewById<TextView>(R.id.number_7_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Seven) }
        findViewById<TextView>(R.id.number_8_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Eight) }
        findViewById<TextView>(R.id.number_9_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Nine) }

        findViewById<TextView>(R.id.dot_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Dot) }
        findViewById<TextView>(R.id.zero_text_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Zero) }
        findViewById<ImageView>(R.id.delete_image_view).setOnClickListener { onDialPressedListener.onDialPressed(Dial.Delete) }
    }

    fun observe(textView: TextView) {
        DialPadConductor(this, textView)
    }
}

interface OnDialPressedListener {
    fun onDialPressed(dial: Dial)
}