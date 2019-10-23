package ericdiaz.program.currencyconveterlive2019.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ericdiaz.program.currencyconveterlive2019.R
import ericdiaz.program.data.model.CurrencyProfile

class CurrencyAdapter(private val layoutInflater: LayoutInflater?,
                      private val currencyList: Array<String>,
                      private val currencyProfileMap: Map<String, CurrencyProfile>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return Any()
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return currencyList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return layoutInflater?.inflate(R.layout.currency_spinner_layout, null)?.apply {
            this.findViewById<ImageView>(R.id.currency_spinner_image_view)?.apply {
                Picasso.get().load(currencyProfileMap[currencyList[position]]?.flag).into(this)
            }
            this.findViewById<TextView>(R.id.currency_spinner_text_view)?.apply {
                text = currencyProfileMap[currencyList[position]]?.currencyName
            }
        } ?: convertView!!
    }
}