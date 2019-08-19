package dao.cacao.feedapptest.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import dao.cacao.feedapptest.extentions.inflate

abstract class BaseVH<I>(parent: ViewGroup, @LayoutRes id: Int) : RecyclerView.ViewHolder(parent.inflate(id)) {

    abstract fun bind(item: I)
}