package co.trance.lib.utility.helper.view

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T: BaseHolderItem>(v: View, ctx: Context): RecyclerView.ViewHolder(v) {
    abstract fun bind(item: T)
}