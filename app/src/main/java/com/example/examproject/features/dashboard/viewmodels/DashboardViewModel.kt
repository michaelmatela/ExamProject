package com.example.examproject.features.dashboard.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examproject.data.entity.DashboardEntity
import com.example.examproject.features.dashboard.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Calendar

class DashboardViewModel(private val context: Context): ViewModel() {
    private val dashboardRepository = DashboardRepository(context)

    init {
        viewModelScope.launch {
            dashboardRepository.fetchData()
            updateDayOfWeek()
        }
    }

    private fun updateDayOfWeek() {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val dayOfWeekString = when (dayOfWeek) {
            Calendar.SUNDAY -> "Sunday"
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> "Unknown"
        }
        _dayOfWeekLiveData.value = dayOfWeekString
    }

    private val _dayOfWeekLiveData = MutableLiveData<String>()
    val dayOfWeekLiveData: LiveData<String> = _dayOfWeekLiveData
    val uiState: Flow<DashboardViewModelState> = combine(dashboardRepository.allLevelsResponseFlow) { response ->

        DashboardViewModelState.Finished(dashboardEntity = response.first())
    }
}

sealed class DashboardViewModelState {
    class Finished(val dashboardEntity: DashboardEntity?): DashboardViewModelState()
}