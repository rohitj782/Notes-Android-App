package com.rohitrj.notesapp.ui.notes.allnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.internals.adapters.NoteAdapter
import com.rohitrj.notesapp.internals.utlity.basefragment.BaseFragment
import com.rohitrj.notesapp.internals.utlity.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.all_notes_fragment.*


class AllNotesFragment : BaseFragment() {

    private lateinit var viewModel: AllNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AllNotesViewModel::class.java)

        recyclerView.setHasFixedSize(true)
        val layout = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layout
        val adapter = NoteAdapter()
        recyclerView.adapter = adapter

        viewModel.allNotes.observe(this, Observer { notes ->
            // Update the cached copy of the words in the adapter.
            notes?.let { adapter.setNotes(it) }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.materialToolbar.visibility = View.GONE

        val mAdView: AdView = view.findViewById(R.id.adView)

        MobileAds.initialize(
            activity!!,
            getString(R.string.add_mob_id)
        )

        val adRequest = AdRequest.Builder()
            .build()
        mAdView.loadAd(adRequest)

        floatingActionButtonAdd.setOnClickListener {
            Navigation.findNavController(view).navigate(AllNotesFragmentDirections.addNote())
        }
        mAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
//                activity!!.toast("success Loading")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
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


    }

}
