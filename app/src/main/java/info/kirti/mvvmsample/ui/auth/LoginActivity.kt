package info.kirti.mvvmsample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import info.kirti.mvvmsample.R
import info.kirti.mvvmsample.data.db.entities.User
import info.kirti.mvvmsample.databinding.ActivityLoginBinding
import info.kirti.mvvmsample.ui.home.HomeActivity
import info.kirti.mvvmsample.util.hide
import info.kirti.mvvmsample.util.show
import info.kirti.mvvmsample.util.snekBar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(),AuthListener,KodeinAware{


    override val kodein by  kodein()
    private val factory : AuthViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityLoginBinding =DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel=ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel=viewModel
        viewModel.authListener=this
        viewModel.getLoggedInUser().observe(this, Observer { user->
            if(user !=null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onSuccess(user: User) {
        //toast("${user.name} is Loged in")
        //root_layout.snekBar("${user.name} is Loged in")
        progress_bar.hide()
    }

    override fun onStarts() {
        //toast("Login started")
        progress_bar.show()
    }

    override fun onFailure(message: String) {
        //toast(message)
        progress_bar.hide()
        root_layout.snekBar(message)
    }
}
