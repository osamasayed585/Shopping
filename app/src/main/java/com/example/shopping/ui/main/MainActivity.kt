package com.example.shopping.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shopping.R
import com.example.shopping.databinding.ActivityMainBinding
import com.example.shopping.databinding.ContentMainBinding
import com.example.shopping.ui.filter_by_category.FilterByCategoryViewModel
import com.hrhera.login.utils.OnLogin
import com.hrhera.login.utils.OnUserLogin
import com.hrhera.login.utils.Static

class MainActivity : AppCompatActivity(), OnUserLogin {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    val sherViewModel: FilterByCategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Static.onUserLogin = this
        initBottomNav()

    }

    private fun initBottomNav() {
        val navView: BottomNavigationView = binding.contentMain.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main2)
        navView.setupWithNavController(navController)
    }

    private fun afterLogin() {
        binding.contentMain.navView.visibility = View.VISIBLE
        navController.setGraph(R.navigation.mobile_navigation)

    }

    private fun afterLogout() {
        binding.contentMain.navView.visibility = View.GONE
        navController.setGraph(R.navigation.main_graph)
    }

    override fun onLogOut() {
        afterLogout()
    }

    override fun onLogin() {
        afterLogin()
    }
}