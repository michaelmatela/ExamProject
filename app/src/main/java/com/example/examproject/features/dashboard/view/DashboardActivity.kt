package com.example.examproject.features.dashboard.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examproject.R
import com.example.examproject.data.entity.LevelEntity
import com.example.examproject.databinding.ActivityDashboardBinding
import com.example.examproject.databinding.BottomHeaderBinding
import com.example.examproject.features.dashboard.view.adapter.LevelsAdapter
import com.example.examproject.features.dashboard.viewmodels.DashboardViewModel
import com.example.examproject.features.dashboard.viewmodels.DashboardViewModelState

class DashboardActivity : AppCompatActivity() {
    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var bottomHeaderBinding: BottomHeaderBinding


    private fun updateRecyclerView(levels: List<LevelEntity>?) {
        binding.recyclerViewLevels.adapter = LevelsAdapter(baseContext, levels)
        binding.recyclerViewLevels.layoutManager = LinearLayoutManager(this@DashboardActivity)
        (binding.recyclerViewLevels.adapter as LevelsAdapter).notifyDataSetChanged()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = DashboardViewModel(baseContext)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.dayOfWeekLiveData.observe(this, Observer { dayOfWeek ->
            if (dayOfWeek == "Monday"){
                turnAllDaysOff()
                binding.root.findViewById<ImageView>(R.id.monday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.monday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.monday_text).setTextColor( resources.getColor(R.color.day_selected))
            } else if (dayOfWeek == "Tuesday"){
                turnAllDaysOff()
                binding.root.findViewById<ImageView>(R.id.monday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.monday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.monday_text).setTextColor( resources.getColor(R.color.day_selected))

                binding.root.findViewById<ImageView>(R.id.tuesday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.tuesday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.tuesday_text).setTextColor( resources.getColor(R.color.day_selected))
            } else if (dayOfWeek == "Wednesday"){

                turnAllDaysOff()
                binding.root.findViewById<ImageView>(R.id.monday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.monday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.monday_text).setTextColor( resources.getColor(R.color.day_selected))

                binding.root.findViewById<ImageView>(R.id.tuesday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.tuesday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.tuesday_text).setTextColor( resources.getColor(R.color.day_selected))

                binding.root.findViewById<ImageView>(R.id.wednesday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.wednesday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.wednesday_text).setTextColor( resources.getColor(R.color.day_selected))
            } else if (dayOfWeek == "Thursday"){

                turnAllDaysOff()
                binding.root.findViewById<ImageView>(R.id.monday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.monday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.monday_text).setTextColor( resources.getColor(R.color.day_selected))

                binding.root.findViewById<ImageView>(R.id.tuesday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.tuesday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.tuesday_text).setTextColor( resources.getColor(R.color.day_selected))

                binding.root.findViewById<ImageView>(R.id.wednesday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.wednesday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.wednesday_text).setTextColor( resources.getColor(R.color.day_selected))

                binding.root.findViewById<ImageView>(R.id.thursday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
                binding.root.findViewById<ImageView>(R.id.thursday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
                binding.root.findViewById<TextView>(R.id.thursday_text).setTextColor( resources.getColor(R.color.day_selected))
            } else if (dayOfWeek == "Friday"){
                turnAllDaysOn()

                binding.root.findViewById<ImageView>(R.id.saturday_circle).setBackgroundResource(R.drawable.shape_circle_not_selected)
                binding.root.findViewById<ImageView>(R.id.saturday_border).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
                binding.root.findViewById<TextView>(R.id.saturday_text).setTextColor( resources.getColor(R.color.day_not_selected))

                binding.root.findViewById<ImageView>(R.id.sunday_circle).setBackgroundResource(R.drawable.shape_circle_not_selected)
                binding.root.findViewById<ImageView>(R.id.sunday_border).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
                binding.root.findViewById<TextView>(R.id.sunday_text).setTextColor( resources.getColor(R.color.day_not_selected))

            } else if (dayOfWeek == "Saturday"){
                turnAllDaysOn()
                binding.root.findViewById<ImageView>(R.id.sunday_circle).setBackgroundResource(R.drawable.shape_circle_not_selected)
                binding.root.findViewById<ImageView>(R.id.sunday_border).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
                binding.root.findViewById<TextView>(R.id.sunday_text).setTextColor( resources.getColor(R.color.day_not_selected))

            } else if (dayOfWeek == "Sunday"){
                turnAllDaysOn()
            }
        })


        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is DashboardViewModelState.Finished -> {
                        val dashboardEntity = uiState.dashboardEntity
                        val levels = dashboardEntity?.levels
                        if (dashboardEntity != null) {
                            updateRecyclerView(levels)
                        }
                    }
                }
            }
        }
    }

    private fun turnAllDaysOn() {
        binding.root.findViewById<ImageView>(R.id.monday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
        binding.root.findViewById<ImageView>(R.id.monday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
        binding.root.findViewById<TextView>(R.id.monday_text).setTextColor( resources.getColor(R.color.day_selected))

        binding.root.findViewById<ImageView>(R.id.tuesday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
        binding.root.findViewById<ImageView>(R.id.tuesday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
        binding.root.findViewById<TextView>(R.id.tuesday_text).setTextColor( resources.getColor(R.color.day_selected))

        binding.root.findViewById<ImageView>(R.id.wednesday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
        binding.root.findViewById<ImageView>(R.id.wednesday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
        binding.root.findViewById<TextView>(R.id.wednesday_text).setTextColor( resources.getColor(R.color.day_selected))

        binding.root.findViewById<ImageView>(R.id.thursday_border).setBackgroundResource(R.drawable.shape_circle_selected)
        binding.root.findViewById<ImageView>(R.id.thursday_circle).setBackgroundResource(R.drawable.shape_date_divider_selected)
        binding.root.findViewById<TextView>(R.id.thursday_text).setTextColor( resources.getColor(R.color.day_selected))

        binding.root.findViewById<ImageView>(R.id.saturday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
        binding.root.findViewById<ImageView>(R.id.saturday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
        binding.root.findViewById<TextView>(R.id.saturday_text).setTextColor( resources.getColor(R.color.day_selected))

        binding.root.findViewById<ImageView>(R.id.friday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
        binding.root.findViewById<ImageView>(R.id.friday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
        binding.root.findViewById<TextView>(R.id.friday_text).setTextColor( resources.getColor(R.color.day_selected))

        binding.root.findViewById<ImageView>(R.id.sunday_circle).setBackgroundResource(R.drawable.shape_circle_selected)
        binding.root.findViewById<ImageView>(R.id.sunday_border).setBackgroundResource(R.drawable.shape_date_divider_selected)
        binding.root.findViewById<TextView>(R.id.sunday_text).setTextColor( resources.getColor(R.color.day_selected))
    }

    private fun turnAllDaysOff() {
        binding.root.findViewById<ImageView>(R.id.monday_circle).setBackgroundResource(R.drawable.shape_circle_not_selected)
        binding.root.findViewById<ImageView>(R.id.monday_border).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
        binding.root.findViewById<TextView>(R.id.monday_text).setTextColor( resources.getColor(R.color.day_not_selected))

        binding.root.findViewById<ImageView>(R.id.tuesday_circle).setBackgroundResource(R.drawable.shape_circle_not_selected)
        binding.root.findViewById<ImageView>(R.id.tuesday_border).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
        binding.root.findViewById<TextView>(R.id.tuesday_text).setTextColor( resources.getColor(R.color.day_not_selected))

        binding.root.findViewById<ImageView>(R.id.wednesday_circle).setBackgroundResource(R.drawable.shape_circle_not_selected)
        binding.root.findViewById<ImageView>(R.id.wednesday_border).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
        binding.root.findViewById<TextView>(R.id.wednesday_text).setTextColor( resources.getColor(R.color.day_not_selected))

        binding.root.findViewById<ImageView>(R.id.thursday_border).setBackgroundResource(R.drawable.shape_circle_not_selected)
        binding.root.findViewById<ImageView>(R.id.thursday_circle).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
        binding.root.findViewById<TextView>(R.id.thursday_text).setTextColor( resources.getColor(R.color.day_not_selected))

        binding.root.findViewById<ImageView>(R.id.friday_circle).setBackgroundResource(R.drawable.shape_circle_not_selected)
        binding.root.findViewById<ImageView>(R.id.friday_border).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
        binding.root.findViewById<TextView>(R.id.friday_text).setTextColor( resources.getColor(R.color.day_not_selected))

        binding.root.findViewById<ImageView>(R.id.saturday_circle).setBackgroundResource(R.drawable.shape_circle_not_selected)
        binding.root.findViewById<ImageView>(R.id.saturday_border).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
        binding.root.findViewById<TextView>(R.id.saturday_text).setTextColor( resources.getColor(R.color.day_not_selected))

        binding.root.findViewById<ImageView>(R.id.sunday_circle).setBackgroundResource(R.drawable.shape_circle_not_selected)
        binding.root.findViewById<ImageView>(R.id.sunday_border).setBackgroundResource(R.drawable.shape_date_divider_not_selected)
        binding.root.findViewById<TextView>(R.id.sunday_text).setTextColor( resources.getColor(R.color.day_not_selected))
    }

}