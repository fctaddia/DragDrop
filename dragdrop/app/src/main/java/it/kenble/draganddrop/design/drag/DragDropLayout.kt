package it.kenble.draganddrop.design.drag

import android.os.Build
import android.view.View
import android.view.DragEvent
import android.view.ViewGroup
import android.content.Context
import android.content.ClipData
import android.util.AttributeSet
import android.widget.GridLayout
import android.content.ClipDescription
import android.view.View.OnDragListener
import android.view.View.OnLongClickListener
import android.view.animation.AnimationUtils

import com.google.android.material.button.MaterialButton

import it.kenble.draganddrop.R

class DragDropLayout @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0):
    GridLayout(context, attrs, defStyleAttr), OnLongClickListener, OnDragListener {

    private var childs: MutableList<View>? = null

    override fun onLongClick(v: View): Boolean {
        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val item = ClipData.Item(v.tag as CharSequence)
        val dragData = ClipData(v.tag.toString(), mimeTypes, item)
        val shadowBuilder = DragShadowBuilder(v)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            v.startDragAndDrop(dragData, shadowBuilder, v, 0)
        } else v.startDrag(dragData, shadowBuilder, v, 0)
        v.visibility = INVISIBLE
        return true
    }

    override fun onDrag(dropView: View, event: DragEvent): Boolean {
        val dragView = event.localState as View
        return when (event.action) {
            DragEvent.ACTION_DROP -> { clearShake(dropView) ; swapView(dragView, dropView) ; true }
            DragEvent.ACTION_DRAG_STARTED -> { setVisibility(dragView, false) ; true }
            DragEvent.ACTION_DRAG_ENDED -> { setVisibility(dragView, true) ; true }
            DragEvent.ACTION_DRAG_ENTERED -> { shakeView(dropView) ; true }
            DragEvent.ACTION_DRAG_EXITED -> { clearShake(dropView) ; true }
            DragEvent.ACTION_DRAG_LOCATION -> true
            else -> false
        }
    }

    internal fun init() {
        childs = ArrayList()
        for (child in getAllChild(this)) {
            if (child is MaterialButton) {
                child.setOnLongClickListener(this)
                child.setOnDragListener(this)
                (childs as ArrayList<View>).add(child)
            }
        }
    }

    private fun setVisibility(v: View, isVisible: Boolean) {
        v.post { v.visibility = if (isVisible) VISIBLE else INVISIBLE }
    }

    private fun swapView(dragView: View, dropView: View) {
        val dragParams = dragView.layoutParams as LayoutParams
        val dropParams = dropView.layoutParams as LayoutParams
        val dragIndex = indexOfChild(dragView)
        val dropIndex = indexOfChild(dropView)
        dragView.layoutParams = dropParams
        dropView.layoutParams = dragParams
        childs!!.removeAt(dragIndex)
        childs!!.add(dragIndex, dropView)
        childs!!.removeAt(dropIndex)
        childs!!.add(dropIndex, dragView)
        removeAllViews()
        for (child in childs!!) addView(child)
    }

    private fun shakeView(v: View) {
        v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake))
    }

    private fun clearShake(v: View) {
        v.clearAnimation()
    }

    private fun getAllChild(v: View): List<View> {
        val visited: MutableList<View> = ArrayList()
        val unvisited: MutableList<View> = ArrayList()
        unvisited.add(v)
        while (unvisited.isNotEmpty()) {
            val child = unvisited.removeAt(0)
            visited.add(child)
            if (child !is ViewGroup) continue
            for (i in 0 until child.childCount) unvisited.add(child.getChildAt(i))
        }
        return visited
    }

}