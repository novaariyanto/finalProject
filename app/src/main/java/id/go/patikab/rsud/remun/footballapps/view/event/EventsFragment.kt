package id.go.patikab.rsud.remun.footballapps

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.go.patikab.rsud.remun.footballapps.util.openEventsSearch
import kotlinx.android.synthetic.main.layout_main_evene.*

class EventsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_main_evene, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vp_events.adapter = EventsPagerAdapter(childFragmentManager)
        tab_events.setupWithViewPager(vp_events)

        with(toolbar) {
            inflateMenu(R.menu.cari)

            val searchMenu = menu.findItem(R.id.action_cari)
            searchMenu.actionView = null
            searchMenu.setOnMenuItemClickListener {
                activity?.openEventsSearch()
                true
            }
        }
    }

}

internal class EventsPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    private val mPages by lazy { listOf(EventsNextFragment(), EventsPastFragment()) }
    private val mTitles by lazy { listOf("Next", "Past") }

    override fun getItem(position: Int): Fragment {
        return mPages[position]
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

}