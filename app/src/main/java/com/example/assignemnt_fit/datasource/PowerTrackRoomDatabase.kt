package com.example.assignemnt_fit.datasource
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignemnt_fit.model.Exercise
import com.example.assignemnt_fit.model.ExerciseDao
import com.example.assignemnt_fit.model.ExerciseWeekDayJoin
import com.example.assignemnt_fit.model.ExerciseWeekDayJoinDao
import com.example.assignemnt_fit.model.WeekDay
import com.example.assignemnt_fit.model.WeekDayDao
//import com.example.assignemnt_fit.model.WeekdayDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class, WeekDay::class, ExerciseWeekDayJoin::class], version = 2)
abstract class PowerTrackRoomDatabase : RoomDatabase(){

    abstract fun exerciseDao(): ExerciseDao
    abstract fun weekdayDao(): WeekDayDao
    abstract fun exerciseWeekDayJoinDao(): ExerciseWeekDayJoinDao

    companion object{
        @Volatile
        private var instance: PowerTrackRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)
        @Synchronized
        fun getDatabase(context: Context): PowerTrackRoomDatabase {
            return instance ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    PowerTrackRoomDatabase::class.java,
                    "PowerTrackDataBase"
                )
                .addCallback(roomDatabaseCallback(context))
                .fallbackToDestructiveMigration()
                .build()
                this.instance = instance
                instance
            }
        }

//        private fun roomDatabaseCallback(context: Context): Callback {
//            return object : Callback() {
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    coroutineScope.launch {
//                        populateDatabase(context, getDatabase(context)!!)
//                        val daysToInsert  = listOf(
//                            WeekDay(0, "Monday", ""),
//                            WeekDay(0, "Tuesday", ""),
//                            WeekDay(0, "Wednesday", ""),
//                            WeekDay(0, "Thursday", ""),
//                            WeekDay(0, "Friday", ""),
//                            WeekDay(0, "Saturday", ""),
//                            WeekDay(0, "Sunday", ""),)
//                        val weekDayDao = instance!!.weekdayDao()
//                        weekDayDao.insertAll(*daysToInsert.toTypedArray())
//                    }
//                }
//            }
//        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    coroutineScope.launch {
                        val daysToInsert = listOf(
                            WeekDay(0, "Monday", ""),
                            WeekDay(0, "Tuesday", ""),
                            WeekDay(0, "Wednesday", ""),
                            WeekDay(0, "Thursday", ""),
                            WeekDay(0, "Friday", ""),
                            WeekDay(0, "Saturday", ""),
                            WeekDay(0, "Sunday", ""),)
                        
                        val weekDayDao = instance!!.weekdayDao()
                        weekDayDao.insertAll(*daysToInsert.toTypedArray())
                    }
                }
            }
        }

//        val dayDao = PowerTrackRoomDatabase.daysToInsert
//        private fun populateDatabase(context: Context, db: PowerTrackRoomDatabase) {
//            coroutineScope.launch {
//                db.weekdayDao().insertAll(
////                    *daysToInsert.toTypedArray()
//                    )
//            }
//        }
    }
}

