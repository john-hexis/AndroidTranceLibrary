package co.trance.lib.utility.base

import android.content.Context
import androidx.room.Room
import co.trance.lib.utility.guide.repository.service.IPersistenceService

abstract class BaseDBObject {
    private var INSTANCE: Any? = null

    fun <D: IPersistenceService> getDatabase(context: Context, dbName: String): BaseDBService<D> {
        if (INSTANCE == null) {
            INSTANCE = Room
                .databaseBuilder(context.applicationContext, BaseDBService::class.java, dbName)
                .fallbackToDestructiveMigration().build()

        }
        return INSTANCE as BaseDBService<D>
    }

    fun destroyInstance() {
        INSTANCE = null
    }
}