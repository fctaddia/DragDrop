package it.kenble.draganddrop.base

import android.widget.Toast
import android.content.Context
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    internal fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    internal fun Context.dpToPx(dp: Float): Int =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

}