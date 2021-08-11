package co.trance.lib.utility.base

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import co.trance.lib.utility.guide.repository.service.IPersistenceService

abstract class BaseDBService<D: IPersistenceService>: RoomDatabase() {
    abstract fun daoService(): D
}