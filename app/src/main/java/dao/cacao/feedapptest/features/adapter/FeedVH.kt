package dao.cacao.feedapptest.features.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import dao.cacao.feedapptest.R
import dao.cacao.feedapptest.base.BaseVH
import dao.cacao.feedapptest.model.entities.Feed
import kotlinx.android.synthetic.main.item_feed.view.imageAvatar
import kotlinx.android.synthetic.main.item_feed.view.imagePost
import kotlinx.android.synthetic.main.item_feed.view.textNameDate
import kotlinx.android.synthetic.main.item_feed.view.textTitle

class FeedVH(parent: ViewGroup) : BaseVH<Feed>(parent, R.layout.item_feed) {

    override fun bind(item: Feed) {
        loadImage(itemView.imagePost, item.avatar)
        loadImage(itemView.imageAvatar, item.image)
        itemView.textTitle.text = item.title
        itemView.textNameDate.text = "${item.user}, ${item.created}"
    }

    private fun loadImage(view: ImageView, uri: String) {
        Glide.with(itemView)
            .load(uri)
            .into(view)
    }
}