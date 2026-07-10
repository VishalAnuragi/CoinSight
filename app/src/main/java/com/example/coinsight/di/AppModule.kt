package com.example.coinsight.di

import android.app.Application
import androidx.room.Room
import com.example.coinsight.data.remote.CoinGeckoApi
import com.example.coinsight.data.remote.local.entity.CoinDatabase
import com.example.coinsight.data.remote.repository.CoinRepositoryImpl
import com.example.coinsight.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinGeckoApi(): CoinGeckoApi {
        return Retrofit.Builder()
            .baseUrl(CoinGeckoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinDatabase(app: Application): CoinDatabase {
        return Room.databaseBuilder(
            app,
            CoinDatabase::class.java,
            "coin_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        api: CoinGeckoApi,
        db: CoinDatabase
    ): CoinRepository {
        return CoinRepositoryImpl(
            api = api,
            dao = db.coinDao
        )
    }
}