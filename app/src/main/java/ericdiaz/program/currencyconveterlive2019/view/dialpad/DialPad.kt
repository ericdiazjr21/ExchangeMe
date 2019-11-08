package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.number_dial_pad.view.*

class DialPad(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private lateinit var onDialPressedListener: OnDialPressedListener

    private val dialPadConductorMap = mutableMapOf<DialPad, DialPadConductor>()

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
        clear_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Clear) }
        zero_text_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Zero) }
        delete_image_view.setOnClickListener { onDialPressedListener.onDialPressed(Dial.Delete) }
    }

    fun observe(connectedView: TextView, dialResponseListener: DialResponseListener) {

        if (!dialPadConductorMap.containsKey(this@DialPad)) {

            DialPadConductor(
                    connectedView,
                    dialResponseListener).let {

                onDialPressedListener = it

                dialPadConductorMap[this@DialPad] = it
            }
        } else {
            Log.i("DialPad",
                    "DialPad ${resources.getResourceEntryName(this.id)} already observing " +
                            resources.getResourceEntryName(connectedView.id))
        }
    }
}

interface DialResponseListener {
    fun onChange(value: String)
}

interface OnDialPressedListener {
    fun onDialPressed(dial: Dial)
}