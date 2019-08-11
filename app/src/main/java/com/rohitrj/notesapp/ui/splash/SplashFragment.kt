package com.rohitrj.notesapp.ui.splash

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.ui.basefragment.BaseFragment
import com.rohitrj.notesapp.ui.notifications.MyJobService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        val componentName = ComponentName(context!!,MyJobService::class.java)
        val jobInfo = JobInfo.Builder(10, componentName)
            .setPeriodic(12*60*60*1000)
            .setPersisted(true)
            .build()
        val schedular = context!!.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        schedular.schedule(jobInfo)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.materialToolbar.visibility = View.GONE

        launch {
            delay(800)
            Navigation.findNavController(view).navigate(SplashFragmentDirections.nextAction())
        }
    }


}


