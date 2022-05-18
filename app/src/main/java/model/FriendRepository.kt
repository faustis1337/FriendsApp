package model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors

class FriendRepository private constructor(private val context: Context) {
    private val database: FriendDatabase = Room.databaseBuilder(context.applicationContext,
    FriendDatabase::class.java,
    "friend-database").build();

    private val friendDAO = database.friendDao()

    fun getAll(): LiveData<List<BEFriend>> = friendDAO.getAll()

    fun getById(id:Int) = friendDAO.getById(id)

    private val executor = Executors.newSingleThreadExecutor()

    fun insert(friend: BEFriend){
        executor.execute{friendDAO.insert(friend)}
    }
    fun update(friend: BEFriend){
        executor.execute{friendDAO.update(friend)}
    }
    fun delete(friend:BEFriend){
        executor.execute{friendDAO.delete(friend)}
    }
    fun clear(){
        executor.execute{friendDAO.clearAll()}
    }

    companion object {
        private var Instance: FriendRepository? = null

        fun initialize(context: Context) {
            if (Instance == null)
                Instance = FriendRepository(context)
        }

        fun get(): FriendRepository {
            if (Instance != null) return Instance!!
            throw IllegalStateException("Person repo not initialized")
        }
    }
}