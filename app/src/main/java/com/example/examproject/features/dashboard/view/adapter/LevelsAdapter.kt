package com.example.examproject.features.dashboard.view.adapter

import CenteredGridLayoutManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.examproject.R
import com.example.examproject.data.entity.LevelEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class LevelsAdapter(private val context: Context, private val dataList: List<LevelEntity>?) :
    RecyclerView.Adapter<LevelsAdapter.ViewHolder>() {

    // ViewHolder class to hold the views for each item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val levelNumber: TextView = itemView.findViewById(R.id.level_number)
        val levelTitle: TextView = itemView.findViewById(R.id.level_title)
        val levelDescription: TextView = itemView.findViewById(R.id.level_description)
        val centerActivityContainer: ConstraintLayout = itemView.findViewById(R.id.center_activity)
        val activityIcon: ImageView = itemView.findViewById(R.id.activity_icon)
        val activityTitle: TextView = itemView.findViewById(R.id.activity_title)

        val activityRecyclerView: RecyclerView = itemView.findViewById(R.id.activity_recycler_view)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_level, parent, false)
        return ViewHolder(view)
    }

    fun isEven(number: Int): Boolean {
        return number % 2 == 0
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList?.get(position)
        holder.levelNumber.text = "Level" + item?.level
        holder.levelTitle.text = item?.title
        holder.levelDescription.text = item?.description
        val layoutManager = GridLayoutManager(context, 2)
        val adapter = item?.state?.let { ActivityAdapter(context, item?.activities, it) }
        holder.activityRecyclerView.addItemDecoration(CenteredGridLayoutManager(2))
        holder.activityRecyclerView.layoutManager = layoutManager
        holder.activityRecyclerView.adapter = adapter

        if (item?.activities?.size?.let { !isEven(it) } == true) {
            holder.centerActivityContainer.visibility = View.VISIBLE
            val itemActivity = item.activities[item.activities.size -1]
            holder.activityTitle.text = itemActivity?.title
            var pdfUrl = "https:" + itemActivity?.lockedIcon?.file?.url
            if (item.state == "AVAILABLE") {
                pdfUrl = "https:" + itemActivity?.icon?.file?.url
            }
            itemActivity?.title?.replace(" ", "")
                ?.let { loadPdfPageFromUrlIntoImageView(context, pdfUrl, 0, holder.activityIcon, it) }

        }

    }

    fun loadPdfPageFromUrlIntoImageView(context: Context, pdfUrl: String, pageNumber: Int, imageView: ImageView, fileName: String) {
        val requestOptions = RequestOptions().fitCenter()
        GlobalScope.launch(Dispatchers.IO) {
            // Download the PDF file from the URL
            val pdfFile = File(context.cacheDir, fileName + ".pdf")
            val url = URL(pdfUrl)
            val urlConnection = url.openConnection()
            val inputStream = urlConnection.getInputStream()
            val outputStream = FileOutputStream(pdfFile)
            inputStream.copyTo(outputStream)
            try {
                // Use PdfiumCore to render the PDF page as a Bitmap
                val parcelFileDescriptor: ParcelFileDescriptor =
                    ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY)
                val pdfRenderer = PdfRenderer(parcelFileDescriptor)
                val page = pdfRenderer.openPage(pageNumber)
                val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

                // Display the Bitmap in the ImageView using Glide on the main thread
                GlobalScope.launch(Dispatchers.Main) {
                    Glide.with(context)
                        .load(bitmap)
                        .apply(requestOptions)
                        .into(imageView)
                }
                // Close resources
                page.close()
                pdfRenderer.close()
            }catch (e: Exception){}
            outputStream.close()
            inputStream.close()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataList?.size!!
    }




}