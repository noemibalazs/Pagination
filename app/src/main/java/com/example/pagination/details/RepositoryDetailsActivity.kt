package com.example.pagination.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.pagination.R
import com.example.pagination.data.LastYearStats
import com.example.pagination.data.RepositoryDetails
import com.example.pagination.databinding.ActivityRepositoryDetailsBinding
import com.example.pagination.util.*
import kotlinx.android.synthetic.main.repository_week_commit.view.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import java.text.SimpleDateFormat
import java.util.*

class RepositoryDetailsActivity : AppCompatActivity() {

    private val repositoryDetailsViewModel: RepositoryDetailsViewModel by stateViewModel()

    private lateinit var binding: ActivityRepositoryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository_details)

        initBinding()
        setObservers()
    }

    private fun initBinding() {
        binding.viewModel = repositoryDetailsViewModel
    }

    private fun setObservers() {

        val repositoryDetailsState = repositoryDetailsViewModel.handle.contains(
            REPOSITORY_DETAILS_SAVED_STATE
        )

        if (!repositoryDetailsState) {
            repositoryDetailsViewModel.getRepositoryDetails().observe(this, Observer {
                populateUI(it)
                repositoryDetailsViewModel.saveRepositoryDetailsDueConfChanges(it)
            })
        } else {
            repositoryDetailsViewModel.getRepositoryDetailsAfterConfChanges()
                .observe(this, Observer {
                    populateUI(it)
                })
        }

        val repositoryIssuesState = repositoryDetailsViewModel.handle.contains(
            REPOSITORY_ISSUES_COUNTER_SAVED_STATE
        )

        if (!repositoryIssuesState) {
            repositoryDetailsViewModel.getRepositoryIssuesCount().observe(this, Observer {
                binding.tvRepoIssuesPastYear.setSpannableText(
                    getString(R.string.txt_repo_issues_count, it.total_count)
                )
                repositoryDetailsViewModel.saveRepositoryIssuesDueConfChanges(it)
            })
        } else {
            repositoryDetailsViewModel.getRepositoryIssuesAfterConfChanges()
                .observe(this, Observer {
                    binding.tvRepoIssuesPastYear.setSpannableText(
                        getString(R.string.txt_repo_issues_count, it.total_count)
                    )
                })
        }

        val repositoryStatsState = repositoryDetailsViewModel.handle.contains(
            REPOSITORY_LAST_YEAR_STATS_SAVED_STATE
        )

        if (!repositoryStatsState) {
            repositoryDetailsViewModel.getRepositoryStats().observe(this, Observer {
                populateFieldsWithStats(it)
                repositoryDetailsViewModel.saveRepositoryLastYearStatsDueConfChanges(it)
            })
        } else {
            repositoryDetailsViewModel.getRepositoryStatsAfterConfChanges().observe(this, Observer {
                populateFieldsWithStats(it)
            })
        }

        repositoryDetailsViewModel.mutableFailureError.observe(this, Observer {
            showErrorToastToUser()
        })
    }

    private fun populateUI(repositoryDetails: RepositoryDetails) {
        binding.tvRepoName.setSpannableText(
            getString(
                R.string.txt_repo_name,
                repositoryDetails.name
            )
        )
        binding.tvRepoDescription.setSpannableText(
            getString(
                R.string.txt_repo_description,
                repositoryDetails.description
            )
        )
        binding.tvRepoSize.setSpannableText(
            getString(
                R.string.txt_repo_size,
                repositoryDetails.size
            )
        )
        binding.tvRepoWatchers.setSpannableText(
            getString(
                R.string.txt_repo_watchers,
                repositoryDetails.watchers_count
            )
        )
        binding.tvRepoStargazers.setSpannableText(
            getString(
                R.string.txt_repo_stargazers,
                repositoryDetails.stargazers_count
            )
        )
        binding.tvRepoForks.setSpannableText(
            getString(
                R.string.txt_repo_forks,
                repositoryDetails.forks_count
            )
        )
        binding.tvRepoOpenIssuesCount.setSpannableText(
            getString(
                R.string.txt_repo_open_issues_count,
                repositoryDetails.open_issues_count
            )
        )
    }

    private fun populateFieldsWithStats(lastYearStats: MutableList<LastYearStats>) {
        val firstWeekStats = lastYearStats[0]
        binding.rwcFirstWeek.tv_week.text =
            getString(R.string.txt_first_week, convertLongToDate(firstWeekStats.week))
        binding.rwcFirstWeek.tv_sunday.text = getString(R.string.txt_sunday, firstWeekStats.days[0])
        binding.rwcFirstWeek.tv_monday.text = getString(R.string.txt_monday, firstWeekStats.days[1])
        binding.rwcFirstWeek.tv_tuesday.text =
            getString(R.string.txt_tuesday, firstWeekStats.days[2])
        binding.rwcFirstWeek.tv_wednesday.text =
            getString(R.string.txt_wednesday, firstWeekStats.days[3])
        binding.rwcFirstWeek.tv_thursday.text =
            getString(R.string.txt_thursday, firstWeekStats.days[4])
        binding.rwcFirstWeek.tv_friday.text = getString(R.string.txt_friday, firstWeekStats.days[5])
        binding.rwcFirstWeek.tv_saturday.text =
            getString(R.string.txt_saturday, firstWeekStats.days[6])

        val lastWeekStats = lastYearStats[51]
        binding.rwcLastWeek.tv_week.text =
            getString(R.string.txt_last_week, convertLongToDate(lastWeekStats.week))
        binding.rwcLastWeek.tv_sunday.text = getString(R.string.txt_sunday, lastWeekStats.days[0])
        binding.rwcLastWeek.tv_monday.text = getString(R.string.txt_monday, lastWeekStats.days[1])
        binding.rwcLastWeek.tv_tuesday.text = getString(R.string.txt_tuesday, lastWeekStats.days[2])
        binding.rwcLastWeek.tv_wednesday.text =
            getString(R.string.txt_wednesday, lastWeekStats.days[3])
        binding.rwcLastWeek.tv_thursday.text =
            getString(R.string.txt_thursday, lastWeekStats.days[4])
        binding.rwcLastWeek.tv_friday.text = getString(R.string.txt_friday, lastWeekStats.days[5])
        binding.rwcLastWeek.tv_saturday.text =
            getString(R.string.txt_saturday, lastWeekStats.days[6])
    }

    private fun convertLongToDate(timeStamp: Long): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val date = Date(timeStamp * 1000)
        return dateFormat.format(date)
    }

    private fun showErrorToastToUser() {
        Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_LONG).show()
    }
}