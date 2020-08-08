package com.riztech.sehatq.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.riztech.sehatq.R
import com.riztech.sehatq.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var view: FragmentLoginBinding
    lateinit var callbackManager: CallbackManager
    private lateinit var auth: FirebaseAuth
    lateinit var signInOptions: GoogleSignInOptions
    lateinit var signInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callbackManager = CallbackManager.Factory.create()
        auth = FirebaseAuth.getInstance()

        setupGoogleLogin()

        this.view.facebookLogin.setFragment(this)
        this.view.facebookLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult?>{
            override fun onSuccess(result: LoginResult?) {
                startActivity(Intent(requireContext(), HomeActivity::class.java))
                requireActivity().finish()
                Toast.makeText(requireContext(), "Login Success" , Toast.LENGTH_LONG).show()
            }

            override fun onCancel() {
                Toast.makeText(requireContext(), "Login cancel", Toast.LENGTH_LONG).show()

            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(requireContext(), "Login error", Toast.LENGTH_LONG).show()

            }

        })

        this.view.googleButton.setOnClickListener {
            val loginIntent: Intent = signInClient.signInIntent
            startActivityForResult(loginIntent, 99)
        }

        this.view.etEmail.doAfterTextChanged {
            Log.i("TAG", "after email")
        }

        this.view.etPassword.doAfterTextChanged {
            Log.i("TAG", "after password")
        }
        
        this.view.btLogin.setOnClickListener {
            startActivity(Intent(requireContext(), HomeActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 99) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    googleFirebaseAuth(account)
                }
            } catch (e: ApiException) {
                Toast.makeText(requireContext(), "Google sign in failed1:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupGoogleLogin() {
        signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(requireActivity(), signInOptions)
    }

    private fun googleFirebaseAuth(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(Intent(requireContext(), HomeActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }
}