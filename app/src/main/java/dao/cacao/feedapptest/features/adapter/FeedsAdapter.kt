package dao.cacao.feedapptest.features.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dao.cacao.feedapptest.model.entities.Feed

class FeedsAdapter : RecyclerView.Adapter<FeedVH>() {

    var list = emptyList<Feed>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClick: (Feed) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedVH {
        return FeedVH(parent).apply {
            itemView.setOnClickListener { onClick(list[adapterPosition]) }
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: FeedVH, position: Int) = holder.bind(list[position])

}

