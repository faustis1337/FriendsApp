package model

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class BEFriend(@PrimaryKey(autoGenerate = true) var id:Int,
               var name:String, var phone:String, @Nullable var image: String?
){

    public override fun toString(): String {
        return "$id: Name: $name, Number: $phone"
    }
}