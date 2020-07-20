package com.example.pagination.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.pagination.R
import com.example.pagination.data.Repository
import com.example.pagination.data.RepositoryDetails
import com.example.pagination.databinding.ActivityRepositoryBinding
import com.example.pagination.details.RepositoryDetailsActivity
import com.example.pagination.helper.DataManager
import com.example.pagination.helper.ListDataSource
import com.example.pagination.helper.ListProvider
import com.example.pagination.util.REPOSITORY_LIST_SAVED_STATE
import com.example.pagination.util.openActivity
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
            openActivity(RepositoryDetailsActivity::class.java)
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

        if (!savedState) {
            viewModel.repositoryList.observe(this, Observer { pagedList ->
                repositoryAdapter.submitList(pagedList)
                val mutableRepositoryList = pagedList.parseToList()
                viewModel.saveRepositoryListDueConfChanges(mutableRepositoryList)
            })
        } else {
            viewModel.getRepositoryListAfterConfChanges().observe(this, Observer {
                val pagedList = getRepositoryPagedList(it)
                repositoryAdapter.submitList(null)
                repositoryAdapter.submitList(pagedList)
            })
        }
    }

    private fun getRepositoryPagedList(mutableList: MutableList<Repository>): PagedList<Repository> {
        val listDataSource = ListDataSource(mutableList)
        return ListProvider(listDataSource).getPagedList()
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryAdapter.removeListener()
    }
}