package com.rohitrj.notesapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rohitrj.notesapp.data.entity.Note
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration



@Database(
    entities = [Note::class],
    version = 2,
    exportSchema = false
)

abstract class NoteDatabase :RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {

//        val MIGRATION_2_3: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE Note " + " ADD COLUMN date STRING")
//            }
//        }

            @Volatile
            private var instance: NoteDatabase? = null
            private val LOCK = Any()

            operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

            private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext, NoteDatabase::class.java, "mynotedatabase"
            ).fallbackToDestructiveMigration()
                .build()

        }

    }


