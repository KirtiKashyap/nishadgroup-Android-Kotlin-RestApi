package info.kirti.mvvmsample.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import info.kirti.mvvmsample.data.repositries.UserRepository
import info.kirti.mvvmsample.util.ApiException
import info.kirti.mvvmsample.util.Coroutines
import info.kirti.mvvmsample.util.NoInternetException

// constructor injection private val repository: UserRepository
class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var email: String?= null
    var password : String?=null
    var name : String?=null
    var phone : String?=null
    var passwordconfirm : String?= null
    var authListener: AuthListener?=null

    fun getLoggedInUser()=repository.getUser()

        fun onLoginButtonClick(view :View){
        authListener?.onStarts()
                if(email.isNullOrEmpty() || password.isNullOrEmpty()){
        // not success
        authListener?.onFailure("Email or Password is invalid")
        return
    }
            Coroutines.main() {
                try {
                    val authResponse = repository.userLogin(email!!, password!!)
                    authResponse.user?.let {
                        authListener?.onSuccess(it)
                        repository.saveUser(it)
                        return@main
                    }
                    authListener?.onFailure(authResponse.message!!)
                }catch (e: ApiException){
                    authListener?.onFailure(e.message!!)
                }catch (e : NoInternetException){
                    authListener?.onFailure(e.message!!)
                }
            }

}
    fun onSignup(view: View){
    Intent(view.context,SignupActivity::class.java).also {
        view.context.startActivity(it)
    }

}
    fun onLogin(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view :View){
        authListener?.onStarts()
        if(name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }
        if(email.isNullOrEmpty() ){
            authListener?.onFailure("Email is required")
            return
        }
        if(phone.isNullOrEmpty()){
            authListener?.onFailure("Phone is required")
            return
        }
        if(password.isNullOrEmpty()){
            // not success
            authListener?.onFailure("Please enter the password")
            return
        }
        if(password!= passwordconfirm){
            authListener?.onFailure("Passwordconfirm is not matched")
            return
        }
        Coroutines.main() {
            try {
                val authResponse = repository.userSignup(name!!,email!!, password!!,phone!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e : NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }

    }

}