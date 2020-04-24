package info.kirti.mvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import info.kirti.mvvmsample.R
import info.kirti.mvvmsample.data.db.entities.Quote
import info.kirti.mvvmsample.util.Coroutines
import info.kirti.mvvmsample.util.hide
import info.kirti.mvvmsample.util.show
import info.kirti.mvvmsample.util.toast
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(),KodeinAware {

    private lateinit var viewModel: QuotesViewModel
    override val kodein by kodein()
    private val factory : QuotesViewModelFactory by instance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(QuotesViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = Coroutines.main{
        progress_bar.show()
        val quotes=viewModel.quotes.await()
        progress_bar.hide()
        quotes.observe(this, Observer{
            context?.toast(it.size.toString())
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {
            val mAdapter =GroupAdapter<ViewHolder>().apply {
                addAll(quoteItem)
            }
        recyclerview.apply {
            layoutManager= LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter= mAdapter
        }
    }

    private fun List<Quote>.toQuoteItem() : List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }

}
