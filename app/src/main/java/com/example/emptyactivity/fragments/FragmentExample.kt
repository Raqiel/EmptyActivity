package com.example.emptyactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.example.emptyactivity.R

class FragmentExample : Fragment() {

override fun onCreateView (
    inflater:LayoutInflater,
    container:ViewGroup?,
    savedInstanceState: Bundle?
): View?{
    return super.onCreateView(inflater, container, savedInstanceState)

    return inflater.inflate(R.layout.fragment_example, container, false)
}

}