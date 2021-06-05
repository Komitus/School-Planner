package com.example.mobileapp.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobileapp.data.Entities.CourseEntity
import com.example.mobileapp.data.Entities.GradeEntity
import com.example.mobileapp.data.Entities.LessonEntity
import com.example.mobileapp.data.Entities.PlanDay

@Database(entities = [GradeEntity::class, CourseEntity::class, LessonEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlannerDatabase : RoomDatabase() {

    abstract fun databaseDAO() : DatabseDAO

    companion object{
        @Volatile
        private var INSTANCE: PlannerDatabase? = null

        fun getDatabase(context: Context) : PlannerDatabase{
            val tmpInstance = INSTANCE
            if(tmpInstance != null){
                return tmpInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    PlannerDatabase::class.java,
                    "planner_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}