package com.example.myapplicationskripsiiqbal3.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplicationskripsiiqbal3.R
import com.example.myapplicationskripsiiqbal3.databinding.ActivityMainBinding

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    val NOTIFICATION_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
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

        lifecycleScope.launchWhenCreated {
            val host: NavHostFragment =
                supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
            navController = host.navController

            val navGraph =
                navController.navInflater.inflate(R.navigation.nav_main)
            navController.graph = navGraph

            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.fragmentA3 ||
//                    destination.id == R.id.listOpFragment ||
//                    destination.id == R.id.invoiceProjectFragment ||
//                    destination.id == R.id.billingProjectFragment ||
                    destination.id == R.id.fragmentB2
                ) {
                    binding.navView.visibility = View.VISIBLE
                } else {
                    binding.navView.visibility = View.GONE
                }
            }
            binding.navView.setupWithNavController(navController)
        }
        //getUserCredential()
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
            // Add more cases for handling other permission requests if needed
        }
    }
}