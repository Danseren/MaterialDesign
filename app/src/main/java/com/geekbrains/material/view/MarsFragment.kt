package com.geekbrains.material.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.geekbrains.material.MainActivity
import com.geekbrains.material.R
import com.geekbrains.material.databinding.FragmentMarsBinding
import com.geekbrains.material.viewmodel.AppState
import com.geekbrains.material.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding: FragmentMarsBinding
        get() {
            return _binding!!
        }

    lateinit var mainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainViewModel = (context as MainActivity).mainViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getData().observe(viewLifecycleOwner, { render(it) })
        mainViewModel.getMarsPicture()
    }

    private fun render(appState: AppState) {
        when (appState) {
            is AppState.SuccessMars -> {
                if (appState.serverResponseData.photos.isEmpty()) {
                    Snackbar.make(binding.root, "Снимков нет", Snackbar.LENGTH_SHORT).show()
                } else {
                    val url = appState.serverResponseData.photos.first().imgSrc
                    binding.imageMars.load(url)
                }
            }
            is AppState.Error ->
                Snackbar.make(binding.root, appState.error.toString(), Snackbar.LENGTH_SHORT).show()
            is AppState.Loading -> {
                binding.imageMars.load(R.drawable.progress_animation)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): MarsFragment {
            return MarsFragment()
        }
    }
}