package co.trance.lib.utility.helper.view

import androidx.recyclerview.widget.DiffUtil

class AdapterDiffCallback<T: BaseHolderItem>(private val newItems: List<T>, private val oldItems: List<T>): DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].hashCode() == newItems[newItemPosition].hashCode()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}