package com.rohitrj.notesapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.internals.utlity.basefragment.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class SplashFragment : BaseFragment() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.materialToolbar.visibility = View.GONE

        launch {
            delay(500)
            Navigation.findNavController(view).navigate(SplashFragmentDirections.nextAction())
        }
    }


}


