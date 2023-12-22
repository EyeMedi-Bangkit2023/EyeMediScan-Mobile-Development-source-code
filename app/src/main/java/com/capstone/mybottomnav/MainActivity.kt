package com.capstone.mybottomnav

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.mybottomnav.VM.MainViewModel
import com.capstone.mybottomnav.data.CardListAdapter
import com.capstone.mybottomnav.data.UserPreferences
import com.capstone.mybottomnav.data.Uservar
import com.capstone.mybottomnav.databinding.ActivityMainBinding
import com.capstone.mybottomnav.db.LoadingState
import com.capstone.mybottomnav.ui.OnBoardingActivity
import com.capstone.mybottomnav.ui.TestiActivity
import com.capstone.mybottomnav.ui.TutorialActivity
import com.capstone.mybottomnav.ui.UploadActivity

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAuth: Uservar

    private val mainViewModel: MainViewModel by viewModels {
        MainVMFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        userAuth()
        TOKEN = "Bearer " + userAuth.token.toString()

        val layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.layoutManager = layoutManager
        val adapter = CardListAdapter()
        binding?.recyclerView?.adapter = adapter.withLoadStateFooter(
            footer = LoadingState {
                adapter.retry()
            }
        )
        mainViewModel.stories.observe(this) {
            adapter.submitData(lifecycle, it)
        }
        binding.rightCornerButton.setOnClickListener{
            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
        }

        binding.icon1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.fabIcon2.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }

        binding.icon3.setOnClickListener {
            val userPref = UserPreferences(this@MainActivity)
            userPref.setLogout()
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.apply {
            fab.setOnClickListener{
                val intent = Intent(this@MainActivity, TestiActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun userAuth() {
        val userPref = UserPreferences(this)
        userAuth = userPref.getUser()
    }
    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }
        supportActionBar?.hide()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (userAuth.token?.isNotEmpty() == true) {
            finishAffinity()
        }
    }
    companion object {
        var TOKEN: String? = null
        var IS_FROM_ADDSTORY: Boolean = false
    }
}
class MainVMFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(Injection.provideRepository(context)) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }

}