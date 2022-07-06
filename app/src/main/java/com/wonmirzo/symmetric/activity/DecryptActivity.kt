package com.wonmirzo.symmetric.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wonmirzo.symmetric.databinding.ActivityDecryptBinding
import com.wonmirzo.symmetric.model.User
import com.wonmirzo.symmetric.utils.Symmetric.decrypt

class DecryptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDecryptBinding
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDecryptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            user = intent.getSerializableExtra("DECRYPT_USER") as User

            tvEmailResult.text = user!!.email
            tvPasswordResult.text = decrypt(user!!.password)
        }
    }
}