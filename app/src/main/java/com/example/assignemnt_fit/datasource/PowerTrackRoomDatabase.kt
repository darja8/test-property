package com.example.assignemnt_fit.datasource
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignemnt_fit.model.Exercise
import com.example.assignemnt_fit.model.ExerciseDao
import com.example.assignemnt_fit.model.WeekdayDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class], version = 1)
abstract class PowerTrackRoomDatabase : RoomDatabase(){

    abstract fun exerciseDao(): ExerciseDao
    abstract fun weekdayDao(): WeekdayDao

    companion object{
        private var instance: PowerTrackRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)
        @Synchronized
        fun getDatabase(context: Context): PowerTrackRoomDatabase?{
            if (instance == null) {
                instance =
                    Room.databaseBuilder<PowerTrackRoomDatabase>(
                        context.applicationContext,
                        PowerTrackRoomDatabase::class.java,
                        "training_app_database"
                    )
                        .allowMainThreadQueries()
                        .addCallback(roomDatabaseCallback(context))
//                        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                        .build()
            }
            return instance
        }
        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    coroutineScope.launch {
                        populateDatabase(context, getDatabase(context)!!)
                    }
                }
            }
        }
     private fun populateDatabase(context: Context, instance: PowerTrackRoomDatabase){

     }
    }
}

