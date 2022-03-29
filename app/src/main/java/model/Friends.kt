package model

import android.util.Log
import android.widget.Toast

object Friends {
    private var friendList:MutableList<FriendEntity> = arrayListOf(
        FriendEntity(1,"Jamie Jones","+3706790643",null),
        FriendEntity(2,"Charles Jones","+370632453245",null),
    FriendEntity(3,"Apalie Jones","+370623452345",null),
    FriendEntity(4,"Jamie Jones","+37061241412",null),
    FriendEntity(5,"Rockie Jones","+370679061124",null)
    )

    fun getAll() = friendList

    fun update(id:Number,name:String, phone:String){
        for(friend in friendList){
            if(friend.id == id){
                friend.name = name
                friend.phone = phone
            }
        }
    }

    fun add(name:String, phone:String,image:String?){
        friendList.add(FriendEntity(friendList.size+1,name,phone,image))
    }

}