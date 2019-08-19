package dao.cacao.feedapptest.model.entities

import com.google.gson.Gson
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import social.Social

class Feed(
    @PrimaryKey val id: Long,
    val title: String,
    val user: String,
    val created: Double,
    val avatar: String,
    val image: String
) : RealmObject() {
    constructor(post: Social.Post) : this(
        post.id.postId,
        post.title,
        post.user.fullName,
        post.created,
        post.user.avatarUrl,
        post.poster.imageUrl
    )

    override fun toString(): String = Gson().toJson(this)
}