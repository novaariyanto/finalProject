package id.go.patikab.rsud.remun.footballapps.service.lokal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.go.patikab.rsud.remun.footballapps.service.lokal.table.FavoriteEvent
import id.go.patikab.rsud.remun.footballapps.service.lokal.table.FavoriteTeam
import org.jetbrains.anko.db.*

class DatabaseOpenHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorites.db", null, 1) {

    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteTeam.TABLE, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT)

        db.createTable(FavoriteEvent.TABLE, true,
                FavoriteEvent.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteEvent.EVENT_ID to TEXT + UNIQUE,
                FavoriteEvent.HOME_NAME to TEXT,
                FavoriteEvent.HOME_SCORE to TEXT,
                FavoriteEvent.AWAY_NAME to TEXT,
                FavoriteEvent.AWAY_SCORE to TEXT,
                FavoriteEvent.DATE to TEXT,
                FavoriteEvent.TIME to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteTeam.TABLE, true)
        db.dropTable(FavoriteEvent.TABLE, true)
    }

}

// access property for Context
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)