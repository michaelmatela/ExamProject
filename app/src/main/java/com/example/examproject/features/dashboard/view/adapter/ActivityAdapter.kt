package com.example.examproject.features.dashboard.view.adapter

import android.content.Context
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examproject.R
import com.example.examproject.data.entity.ActivityEntity
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.StrictMode
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ActivityAdapter(private val context: Context, private val dataList: List<ActivityEntity>?,private val state: String) :
    RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {

    // ViewHolder class to hold the views for each item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityTitle: TextView = itemView.findViewById(R.id.activity_title)
        val activityImage: ImageView = itemView.findViewById(R.id.activity_icon)
        val activityContainer: ConstraintLayout = itemView.findViewById(R.id.activity_container)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity, parent, false)
        return ViewHolder(view)
    }

    fun isEven(number: Int): Boolean {
        return number % 2 == 0
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataList?.get(position)
        holder.activityTitle.text = item?.title
        var pdfUrl = "https:" + item?.lockedIcon?.file?.url
        if (state == "AVAILABLE") {
            pdfUrl = "https:" + item?.icon?.file?.url
        }
        item?.title?.replace(" ", "")
            ?.let { loadPdfPageFromUrlIntoImageView(context, pdfUrl, 0, holder.activityImage, it) }

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
        if (!isEven(dataList?.size!!)) {
            return dataList?.size!! - 1
        }
        return dataList?.size!!
    }





}