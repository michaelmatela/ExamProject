import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examproject.R

class CenteredGridLayoutManager(private val spanCount: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        // Calculate the total width of all items in the row
        val totalItemWidth = view.width * spanCount
        // Calculate the total width of spacing between items in the row
        val totalSpacingWidth = view.resources.getDimensionPixelSize(R.dimen.item_spacing) * (spanCount - 1)
        // Calculate the left offset to center the items
        val leftOffset = (parent.width - totalItemWidth - totalSpacingWidth) / 2

        // If it's not the first item in the row, add left offset
        if (position % spanCount != 0) {
            outRect.left = leftOffset
        }
    }
}