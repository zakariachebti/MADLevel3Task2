package com.example.madlevel3task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_start.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StartFragment : Fragment(), PortalAdapter.OnItemClickListener {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals, this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddReminderResult()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvPortals.adapter = portalAdapter
        rvPortals.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(REQ_REMINDER_KEY) { key, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_REMINDER_KEY)?.let {
                portals.add(it)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!")

        }
    }

    override fun onItemClick(position: Int) {
        val url: String = portals[position].urlText
        openChromeTab(url)
    }

    private fun openChromeTab(url: String) {

    }
}