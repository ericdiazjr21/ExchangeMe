package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.number_dial_pad.view.*

class DialPad(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    lateinit var onDialPressedListener: OnDialPressedListener

    override fun onFinishInflate() {
        super.onFinishInflate()
        
        number_1_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.One) }
        number_2_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Two) }
        number_3_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Three) }
        number_4_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Four) }
        number_5_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Five) }
        number_6_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Six) }
        number_7_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Seven) }
        number_8_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Eight) }
        number_9_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Nine) }
        dot_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Dot) }
        zero_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Zero) }
        delete_image_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Delete) }
    }

    fun observe(textView: TextView) {
        DialPadConductor(this, textView)
    }
}

interface OnDialPressedListener {
    fun onDialPressed(dial: Dial)
}