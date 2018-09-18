package id.go.patikab.rsud.remun.footballapps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            loadEvent()
        }
        nav_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_events -> {
                    loadEvent()
                    true
                }
                R.id.nav_teams -> {
                    loadTeams()
                    true
                }
                R.id.nav_favorites -> {
                   loadFavorites()
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFavorites() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_main, FavoritesFragment())
                .commit()
    }

    private fun loadTeams() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_main, TeamsFragment())
                .commit()
    }

    private fun loadEvent() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_main, EventsFragment())
                .commit()
    }

}
