package sg.mindef.ns.safeguardian.utility.helper

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.trance.lib.utility.helper.view.BaseHolderItem

public abstract class RecyclerViewAdapter<VH: RecyclerView.ViewHolder, HI: BaseHolderItem>(private var items: List<HI>): RecyclerView.Adapter<VH>() {
    fun updateAdapter(items: List<HI>) {
        val diffResult = DiffUtil.calculateDiff(AdapterDiffCallback(items, this.items))
        diffResult.dispatchUpdatesTo(this)
        this.items = items
    }
}