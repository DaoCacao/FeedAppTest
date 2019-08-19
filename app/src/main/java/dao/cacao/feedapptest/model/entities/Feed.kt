package dao.cacao.feedapptest.model.entities

import com.google.gson.Gson
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import social.Social

open class Feed() : RealmObject() {

    @PrimaryKey var id = 0L
    var title = ""
    var user = ""
    var created = 0.0
    var avatar = ""
    var image = ""

    constructor(post: Social.Post) : this() {
        id = post.id.postId
        title = post.title
        user = post.user.fullName
        created = post.created
        avatar = post.user.avatarUrl
        image = post.poster.imageUrl
    }

    override fun toString(): String = Gson().toJson(this)
}