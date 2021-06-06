package com.example.mobileapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapp.MainActivity
import com.example.mobileapp.adapters.PlusAdapter
import com.example.mobileapp.databinding.FragmentPlusBinding
import androidx.lifecycle.Observer
import com.example.mobileapp.data.Entities.PlusEntity
import com.example.mobileapp.data.dataBase.PlannerDBViewModel

class PlusFragment : Fragment() {

    private var _binding: FragmentPlusBinding? = null
    private val binding get() = _binding!!
    private lateinit var plusAdapter: PlusAdapter
    private lateinit var context: MainActivity
    private lateinit var plusList: List<PlusEntity>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlusBinding.inflate(inflater, container, false)
        plusAdapter = PlusAdapter(context.viewModelDatabase)

        context.viewModelDatabase.readAllPluses.observe(this.viewLifecycleOwner, Observer {
            plusList = it
            plusAdapter.setPluses(plusList)
        })

        binding.plusRcyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(container?.context)
            adapter = plusAdapter
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}