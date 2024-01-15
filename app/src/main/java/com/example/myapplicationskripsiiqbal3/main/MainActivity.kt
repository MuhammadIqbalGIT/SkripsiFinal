package com.example.myapplicationskripsiiqbal3.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.databinding.ActivityMainBinding
import com.example.myapplicationskripsiiqbal3.ui.addProduct.AddProductFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    val NOTIFICATION_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_CODE
                )
            }
        }

        lifecycleScope.launch {
            val host: NavHostFragment =
                supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
            navController = host.navController

            val navGraph = navController.navInflater.inflate(R.navigation.nav_main)
            navController.graph = navGraph

            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.fragmentA3 ||
                    destination.id == R.id.fragmentB2
                ) {
                    binding.coordinatorLayout.visibility = View.VISIBLE
                } else {
                    binding.coordinatorLayout.visibility = View.GONE
                }
            }
            binding.navView.setupWithNavController(navController)
        }

        initEvent()
    }

    private fun initEvent() {
        binding.fab.setOnClickListener {
            navigateToFragmentA()
        }
    }

    private fun navigateToFragmentA() {
        val fragmentA = AddProductFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        // Membersihkan seluruh back stack sebelum menambahkan Fragment A
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        transaction.replace(R.id.mainNavHostFragment, fragmentA)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            NOTIFICATION_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, perform action that requires it
                    // ...
                } else {
                    // Permission denied, show message or handle error
                }
            }
        }
    }
}
