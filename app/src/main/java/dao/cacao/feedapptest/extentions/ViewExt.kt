package dao.cacao.feedapptest.extentions

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes id: Int): View = LayoutInflater.from(context).inflate(id, this, false)

fun Activity.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}