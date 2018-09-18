package id.go.patikab.rsud.remun.footballapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.squareup.picasso.Picasso
import id.go.patikab.rsud.remun.footballapps.R
import id.go.patikab.rsud.remun.footballapps.service.api.SportApi
import id.go.patikab.rsud.remun.footballapps.service.api.response.PlayerResponse
import kotlinx.android.synthetic.main.layout_detail_player.*
import kotlinx.android.synthetic.main.layout_main_evene.*

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class PlayersDetailActivity : AppCompatActivity(), PlayersDetailView {

    private val mApi by lazy { SportApi() }
    private val mPresenter by lazy { PlayersDetailPresenter(this, mApi) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_detail_player)

        setSupportActionBar(toolbardetailplayer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        launch(UI) {
            mPresenter.fetchPlayerDetail(intent?.getStringExtra("PLAYER_ID") ?: "")
        }
    }

    override fun showPlayerDetail(player: PlayerResponse.Player) {
        Picasso.get().load(player.bannerUrl).into(iv_banner)
        tv_position.text = player.position
        tv_weight.text = player.weight
        tv_height.text = player.height
        tv_overview.text = player.overview

        supportActionBar?.title = player.name
    }

    override fun showLoading() { }

    override fun hideLoading() { }


}