package com.example.assignemnt_fit.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignemnt_fit.model.DropSet
import com.example.assignemnt_fit.model.DropSetDao
import com.example.assignemnt_fit.model.Exercise
import com.example.assignemnt_fit.model.ExerciseDao
import com.example.assignemnt_fit.model.ExerciseWeekDayJoin
import com.example.assignemnt_fit.model.ExerciseWeekDayJoinDao
import com.example.assignemnt_fit.model.WeekDay
import com.example.assignemnt_fit.model.WeekDayDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class, WeekDay::class, ExerciseWeekDayJoin::class, DropSet::class], version = 5)
abstract class PowerTrackRoomDatabase : RoomDatabase(){

    abstract fun exerciseDao(): ExerciseDao
    abstract fun weekdayDao(): WeekDayDao
    abstract fun exerciseWeekDayJoinDao(): ExerciseWeekDayJoinDao
    abstract fun dropSetDao(): DropSetDao

    companion object{

        @Volatile
        private var instance: PowerTrackRoomDatabase? = null

        private val coroutineScope = CoroutineScope(Dispatchers.IO)
        private val roomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    val daysToInsert = listOf(
                        WeekDay(0, "Monday", ""),
                            WeekDay(0, "Tuesday", ""),
                            WeekDay(0, "Wednesday", ""),
                            WeekDay(0, "Thursday", ""),
                            WeekDay(0, "Friday", ""),
                            WeekDay(0, "Saturday", ""),
                            WeekDay(0, "Sunday", ""),
                    )
                    instance?.weekdayDao()?.insertAll(daysToInsert)
                }
            }
        }

        @Synchronized
        fun getDatabase(context: Context): PowerTrackRoomDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PowerTrackRoomDatabase::class.java,
                    "PowerTrackDataBase"
                )
                    .addMigrations(MIGRATION_2_3) // Add this line
                    .addCallback(roomDatabaseCallback) // Add the callback here
                    .fallbackToDestructiveMigration()
                    .build()
                this.instance = instance
                instance
            }
        }
    }
}

val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE drop_sets (
                dropSetId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                exerciseId INTEGER NOT NULL,
                weight INTEGER NOT NULL,
                repetitions INTEGER NOT NULL,
                FOREIGN KEY(exerciseId) REFERENCES exercises(exerciseId) ON DELETE CASCADE
            )
        """.trimIndent())
    }
}


