package model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FriendDao {

    @Query("SELECT * FROM BEFriend ORDER BY id")
    fun getAll():LiveData<List<BEFriend>>

    @Query("SELECT * FROM BEFriend WHERE id = (:id)")
    fun getById(id:Int):LiveData<BEFriend>

    @Insert
    fun insert(p: BEFriend)
    @Update
    fun update(p: BEFriend)
    @Delete
    fun delete(p: BEFriend)

    @Query("DELETE from BEFriend")
    fun clearAll()
}