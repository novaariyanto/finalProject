package id.go.patikab.rsud.remun.footballapps

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.go.patikab.rsud.remun.footballapps.R
import kotlinx.android.synthetic.main.layout_base_overview.*

class TeamsDetailOverviewFragment : Fragment() {

    private lateinit var mOverview: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_base_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_overview.text = mOverview
    }

    fun setOverview(text: String) {
        mOverview = text
    }

}