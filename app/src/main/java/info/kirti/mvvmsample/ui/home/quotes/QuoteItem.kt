package info.kirti.mvvmsample.ui.home.quotes

import com.xwray.groupie.databinding.BindableItem
import info.kirti.mvvmsample.R
import info.kirti.mvvmsample.data.db.entities.Quote
import info.kirti.mvvmsample.databinding.QuotesItemBinding

class QuoteItem(
    private val quote :Quote) : BindableItem<QuotesItemBinding>(){
    override fun getLayout()= R.layout.quotes_item

    override fun bind(viewBinding: QuotesItemBinding, position: Int) {
        viewBinding.setQuote(quote)
    }

}