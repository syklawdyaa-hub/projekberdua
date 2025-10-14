package com.example.projekberdua

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan Password harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                // Logika login sederhana (bisa kamu ganti dengan validasi sebenarnya)
                if (username == "admin" && password == "1234") {
                    Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                    // Lanjut ke halaman lain, misal MainActivity
                } else {
                    Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
