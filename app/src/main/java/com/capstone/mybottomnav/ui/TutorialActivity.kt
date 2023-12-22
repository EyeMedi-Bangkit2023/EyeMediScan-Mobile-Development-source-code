package com.capstone.mybottomnav.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.mybottomnav.MainActivity
import com.capstone.mybottomnav.R
import com.capstone.mybottomnav.databinding.ActivityTestiBinding
import com.capstone.mybottomnav.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {
    private var _activityTutorialbinding: ActivityTutorialBinding? = null
    private val binding get() = _activityTutorialbinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityTutorialbinding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnBack?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }

    }
}