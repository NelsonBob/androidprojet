package com.example.musicapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.musicapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ClassementFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_classement, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        var tabLayout = view?.findViewById<TabLayout>(R.id.tabs)
        var viewpager = view?.findViewById<ViewPager2>(R.id.viewpager)
        viewpager?.adapter = TabsAdapter(this)
        viewpager?.currentItem = 0
        Log.w("TAG", "tablayout --- $tabLayout viewpager$viewpager")
        if (tabLayout != null ) {
            if (viewpager != null) {
                TabLayoutMediator(tabLayout,viewpager){tab,position ->
                    tab.text = if(position==0) "Titres" else "Albums"
                }.attach()
            }
        }
    }
    class TabsAdapter(fragment: Fragment):FragmentStateAdapter(fragment){
        private  val titresFragment:TitresFragment = TitresFragment()
        private val albumsFragment:AlbumsFragment = AlbumsFragment()
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            var fragment:Fragment? =null
            when(position){
                0 -> fragment =titresFragment
                1 ->fragment = albumsFragment
            }
            return fragment!!
        }


    }
}
