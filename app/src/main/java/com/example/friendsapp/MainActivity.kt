package com.example.friendsapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.*
import android.widget.AdapterView.AdapterContextMenuInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_friends_vew_layout.view.*
import model.FriendEntity
import model.Friends


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lvFriends.adapter = FriendsAdapter(this,Friends.getAll())
        registerForContextMenu(lvFriends)
        btnCreate.setOnClickListener{
            val intent = Intent(this,CreateFriendActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View,
                                     menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
    }

    fun resetAdapter(){
        lvFriends.adapter = FriendsAdapter(this,Friends.getAll())
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.update -> {
                val contact = lvFriends.adapter.getItem(menuInfo.position) as FriendEntity
                val intent = Intent(this,EditFriendActivity::class.java)
                intent.putExtra("contact_id",contact.id)
                intent.putExtra("contact_image",contact.image)
                intent.putExtra("contact_name",contact.name)
                intent.putExtra("contact_phone",contact.phone)
                startActivity(intent)
                true
            }
            R.id.delete -> {
                Friends.getAll().removeAt(menuInfo.position)
                resetAdapter()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    internal class FriendsAdapter(context: Context, private val friends:MutableList<FriendEntity>) : ArrayAdapter<FriendEntity>(context,0,friends){

        private val colours = intArrayOf(
            Color.parseColor("#8693ab"),
            Color.parseColor("#bdd4e7")
        )

        override fun getView(position: Int, v: View?, parent: ViewGroup): View {
            var v1: View? = v
            if (v1 == null) {
                val mInflater = LayoutInflater.from(context)
                v1 = mInflater.inflate(R.layout.adapter_friends_vew_layout, null)

            }
            val resView: View = v1!!
            resView.setBackgroundColor(colours[position % colours.size])
            val f = friends[position]
            val iv_photo = resView.findViewById<ImageView>(R.id.iv_photo)

            if(f.image == null){
                iv_photo.setImageResource(R.drawable.default_friend)
            }
            val tv_name = resView.findViewById<TextView>(R.id.tv_name)
            val tv_phone = resView.findViewById<TextView>(R.id.tv_phone)
            tv_name.text = f.name
            tv_phone.text = f.phone
            return resView
        }
    }
}