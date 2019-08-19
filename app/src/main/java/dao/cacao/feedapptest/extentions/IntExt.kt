package dao.cacao.feedapptest.extentions

import android.content.res.Resources

fun Int.pxToDp() = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()