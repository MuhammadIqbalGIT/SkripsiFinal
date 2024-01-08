package com.example.myapplicationskripsiiqbal3.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.core.data.remote.request.LoginRequest
import com.example.core.di.RetrofitClient
import com.example.core.domain.model.login.LoginModel
import com.example.myapplicationskripsiiqbal3.databinding.ActivityLogin2Binding
import com.example.myapplicationskripsiiqbal3.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityLogin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initEvent()

    }

    private fun initEvent(){
        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val loginRequest = LoginRequest(username, password)

        RetrofitClient.instance.login(loginRequest).enqueue(object : Callback<LoginModel> {
            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    // Handle the response as needed
                    if (loginResponse?.success == true) {
                        val userData = loginResponse.data
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity2, "Berhasil Login", Toast.LENGTH_SHORT).show()
                        }
                        // Jika berhasil login, pindahkan pengguna ke MainActivity
                        val intent = Intent(this@LoginActivity2, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val errorMessage = loginResponse?.message ?: "Unknown error"
                        Toast.makeText(this@LoginActivity2, "${errorMessage}", Toast.LENGTH_SHORT).show()
                        Log.d("error111",errorMessage)

                    }
                } else {
                    Toast.makeText(this@LoginActivity2, "${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                Toast.makeText(this@LoginActivity2, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
