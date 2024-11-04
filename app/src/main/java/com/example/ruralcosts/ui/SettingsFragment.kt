package com.example.ruralcosts.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.ruralcosts.R
import com.example.ruralcosts.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: SettingsViewModel by  viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                uiState ->
                    when(uiState) {
                        is SettingsUiState.Error -> {}
                        SettingsUiState.Loading -> {}
                        is SettingsUiState.Success -> {
                            binding.switchSettingOne.isChecked = uiState.settings.settingOne
                            binding.switchSettingTwo.isChecked = uiState.settings.settingTwo
                        }
                    }
            }
        }
        binding.switchSettingOne.setOnCheckedChangeListener { _, checked ->
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.writeSettingOne(checked)
            }
        }
        binding.switchSettingTwo.setOnCheckedChangeListener { _, checked ->
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.writeSettingTwo(checked)
            }
        }
    }



}