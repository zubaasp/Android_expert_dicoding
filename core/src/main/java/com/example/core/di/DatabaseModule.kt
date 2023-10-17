package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.SekolahDao
import com.example.core.data.source.local.room.SekolahDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SekolahDatabase {
        val builder = Room.databaseBuilder(
            context,
            SekolahDatabase::class.java, "Sekolah.db"
        )
        val passphrase: ByteArray = SQLiteDatabase.getBytes("expert".toCharArray())
        val factory = SupportFactory(passphrase)
        return builder.fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    fun provideSekolahDao(database: SekolahDatabase): SekolahDao = database.sekolahDao()
}