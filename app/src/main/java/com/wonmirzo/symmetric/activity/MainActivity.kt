package com.wonmirzo.symmetric.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wonmirzo.symmetric.databinding.ActivityMainBinding
import com.wonmirzo.symmetric.model.User
import com.wonmirzo.symmetric.utils.Symmetric.decrypt
import com.wonmirzo.symmetric.utils.Symmetric.encrypt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.apply {
            btnEncrypt.setOnClickListener {
                openEncryptActivity()
            }
            mainUI.setOnClickListener {
                hideKeyboard(etEmail)
                hideKeyboard(etPassword)
            }
        }
    }

    private fun openEncryptActivity() {
        binding.apply {
            if (etEmail.text.toString().isEmpty() && etPassword.text.toString().isEmpty()) {
                Toast.makeText(this@MainActivity, "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Intent(this@MainActivity, EncryptActivity::class.java).also { intent ->
                    val email = etEmail.text.toString()
                    val password = encrypt(etPassword.text.toString())
                    val user = User(email, password!!)
                    intent.putExtra("EXTRA_USER", user)
                    startActivity(intent)
                }
            }
        }
    }

    private fun testSymmetric() {
        // secret text
        val originalString = "Mirzohid Dilshodov"
        // Encryption
        val encryptedString = encrypt(originalString)
        // Decryption
        val decryptedString = decrypt(encryptedString)
        // Printing originalString,encryptedString,decryptedString
        Log.d("@@@", "Original String:$originalString")
        Log.d("@@@", "Encrypted value:$encryptedString")
        Log.d("@@@", "Decrypted value:$decryptedString")
    }

    private fun hideKeyboard(view: View) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}