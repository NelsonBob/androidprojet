package com.example.musicapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.TitresDetailsActivity
import com.example.musicapp.bean.Titres

class TitresFragment : Fragment() {
    private val data = ArrayList<Titres>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_titres, container, false)
        initView(view)
        return view;
    }
    fun initView(view:View){
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        for (i in 1..10){
            data.add(Titres(i,"","lizhi","rehe"))
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TitresAdapter(data)
    }

    class TitresAdapter(val list:List<Titres>):RecyclerView.Adapter<TitresAdapter.ViewHolder>(){
        lateinit var context:Context
        class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            var tvId: TextView = view.findViewById(R.id.tv_id)
            var image: ImageView = view.findViewById(R.id.image)
            var tvSong: TextView = view.findViewById(R.id.tv_song)
            var tvSinger: TextView = view.findViewById(R.id.tv_singer)

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            context = parent.context
           val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_titres_item,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvId.text = list[position].id.toString()
           // holder.image.
            holder.tvSong.text = list[position].song
            holder.tvSinger.text = list[position].singer
            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context,TitresDetailsActivity::class.java))

//                var intent = Intent()
//                intent.setClass(context,TitresDetailsActivity::class.java)
//                context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
           return list.size
        }

    }

}