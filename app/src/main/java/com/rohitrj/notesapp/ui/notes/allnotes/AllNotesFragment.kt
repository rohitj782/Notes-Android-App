package com.rohitrj.notesapp.ui.notes.allnotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.work.Data
import androidx.work.WorkManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.data.db.NoteDatabase
import com.rohitrj.notesapp.internals.adapters.NoteAdapter
import com.rohitrj.notesapp.internals.utlity.toast
import com.rohitrj.notesapp.ui.basefragment.BaseFragment
import com.rohitrj.notesapp.ui.notifications.MyNotificationManager
import kotlinx.android.synthetic.main.all_notes_fragment.*
import kotlinx.coroutines.launch


class AllNotesFragment : BaseFragment() {

    companion object {
        fun newInstance() = AllNotesFragment()
    }

    private lateinit var viewModel: AllNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.rohitrj.notesapp.R.layout.all_notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AllNotesViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.materialToolbar.visibility = View.GONE

        lateinit var mAdView : AdView

        MobileAds.initialize(activity!!,
            getString(R.string.add_mob_id))

        mAdView = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder()
            .build()
        mAdView.loadAd(adRequest)

        floatingActionButtonAdd.setOnClickListener {
            Navigation.findNavController(view).navigate(AllNotesFragmentDirections.addNote())
        }
        mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
//                activity!!.toast("success Loading")
            }

            override fun onAdFailedToLoad(errorCode : Int) {
                // Code to be executed when an ad request fails.

//                activity!!.toast("failed"+ errorCode.toString())
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.

//                activity!!.toast("opened")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.

                activity!!.toast("Thanks for clicking Ads")
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.setHasFixedSize(true)
        val layout = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layout
        launch {
            val notes = NoteDatabase(context!!).getNoteDao().getAllNotes()
            recyclerView.adapter = NoteAdapter(notes)
        }

    }

}
