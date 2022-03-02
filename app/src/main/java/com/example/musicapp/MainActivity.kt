package com.example.musicapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.musicapp.fragment.ClassementFragment
import com.example.musicapp.fragment.FavorisFragment
import com.example.musicapp.fragment.RechercheFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var mFragments = mutableListOf<Fragment>()
    private var lastIndex=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.classments ->setFragmentPosition(0)
                R.id.recherche ->setFragmentPosition(1)
                R.id.favoris ->setFragmentPosition(2)

            }
            true
        }
        initData()
    }
    fun initData(){
        mFragments = ArrayList()
        mFragments.add(ClassementFragment())
        mFragments.add(RechercheFragment())
        mFragments.add(FavorisFragment())
        setFragmentPosition(0)
    }
    fun setFragmentPosition( position:Int){
        val ft = supportFragmentManager.beginTransaction()
        val currentFragment = mFragments[position]
        val lastFragment = mFragments[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if(!currentFragment.isAdded){
            supportFragmentManager.beginTransaction().remove(currentFragment)
            ft.add(R.id.fragment_layout,currentFragment)
        }
        ft.show(currentFragment)
        ft.commitAllowingStateLoss()
    }
}