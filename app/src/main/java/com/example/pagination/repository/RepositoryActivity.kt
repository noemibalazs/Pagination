package com.example.pagination.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.pagination.R
import com.example.pagination.databinding.ActivityRepositoryBinding
import com.example.pagination.helper.DataManager
import com.example.pagination.util.REPOSITORY_LIST_SAVED_STATE
import com.example.pagination.util.parseToList
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class RepositoryActivity : AppCompatActivity() {

    private val viewModel: RepositoryViewModel by stateViewModel()
    private val dataManager: DataManager by inject()
    private lateinit var binding: ActivityRepositoryBinding
    private lateinit var repositoryAdapter: RepositoryAdapter

    private val repositoryClickListener = object : RepositoryClickListener {
        override fun onRepositoryClicked(id: Int, fullName: String) {
            dataManager.saveRepositoryID(id)
            dataManager.saveRepositoryFullName(fullName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository)

        initBinding()
        setObserver()
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        repositoryAdapter = RepositoryAdapter(viewModel).apply {
            this.clickListener = repositoryClickListener
        }
        binding.rvRepository.adapter = repositoryAdapter
    }

    private fun setObserver() {
        val savedState = viewModel.handle.contains(REPOSITORY_LIST_SAVED_STATE)
        viewModel.repositoryList.observe(this, Observer {
            repositoryAdapter.submitList(it)
            val mutableRepositoryList = it.parseToList()
            viewModel.saveRepositoryListDueConfChanges(mutableRepositoryList)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryAdapter.removeListener()
    }
}