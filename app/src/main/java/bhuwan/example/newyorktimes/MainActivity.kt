package bhuwan.example.newyorktimes

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import bhuwan.example.newyorktimes.databinding.ActivityMainBinding
import bhuwan.example.newyorktimes.viewmodel.ArticlesViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ArticlesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        viewModel = ViewModelProvider(this)[ArticlesViewModel::class.java]


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        val bottomNavigationView: BottomNavigationView = binding.bottomNavigationView

        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

}
