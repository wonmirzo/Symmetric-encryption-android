package com.wonmirzo.symmetric.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wonmirzo.symmetric.databinding.ActivityEncryptBinding
import com.wonmirzo.symmetric.model.User

class EncryptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEncryptBinding
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEncryptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        user = intent.getSerializableExtra("EXTRA_USER") as User
        binding.apply {
            tvEmailResult.text = user!!.email
            tvPasswordResult.text = user!!.password

            btnDecrypt.setOnClickListener {
                openDecryptActivity()
            }
        }
    }

    private fun openDecryptActivity() {
        Intent(this@EncryptActivity, DecryptActivity::class.java).also { intent ->
            intent.putExtra("DECRYPT_USER", user)
            startActivity(intent)
        }
    }
}