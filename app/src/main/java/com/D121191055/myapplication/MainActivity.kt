@file:Suppress("DEPRECATION")

package com.D121191055.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.D121191055.myapplication.Fragment.SelesaiFragment
import com.D121191055.myapplication.Fragment.TugasFragment
import com.D121191055.myapplication.database.appDatabase
import com.D121191055.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val tugasFragment: Fragment = TugasFragment()
    val selesaiFragment: Fragment = SelesaiFragment()
    val fn: FragmentManager = supportFragmentManager
    var active: Fragment = tugasFragment

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonNavigation()
        setupListener()
    }

    private fun setupListener() {
        binding.buttonTambah.setOnClickListener {
            startActivity(Intent(this,TambahActivity::class.java))
        }
    }

    private fun buttonNavigation() {
        fn.beginTransaction().add(R.id.fragmentContainerView, tugasFragment).show(tugasFragment).commit()
        fn.beginTransaction().add(R.id.fragmentContainerView, selesaiFragment).hide(selesaiFragment).commit()

        bottomNavigationView = binding.buttonNevigationMenu
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId){
                R.id.tugasFragment ->{
                    callFragment(0, tugasFragment)
                }
                R.id.selesaiFragment ->{
                    callFragment(1, selesaiFragment)
                }
            }
            false
        }
    }

    private fun callFragment(index: Int, fragment: Fragment) {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fn.beginTransaction().hide(active).show(fragment).commit()
        active = fragment

    }
}