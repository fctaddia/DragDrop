package it.kenble.draganddrop.ui

import android.os.Bundle
import android.view.View
import android.view.Gravity
import android.widget.GridLayout
import androidx.core.content.ContextCompat

import com.google.android.material.button.MaterialButton

import it.kenble.draganddrop.R
import it.kenble.draganddrop.base.BaseActivity
import it.kenble.draganddrop.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    // region Variables

    private val childCount = 10
    private lateinit var click: View.OnClickListener
    private lateinit var binding: ActivityMainBinding

    // endregion

    // region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    // endregion

    // Init, Listeners

    private fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listeners() ; refreshGridLayout()
    }

    private fun listeners() {
        click = View.OnClickListener { showToast("View -> ${it.id}") }
    }

    // endregion

    // region GridLayout

    private fun refreshGridLayout() {
        gridLayout()
        for (i in 0 until childCount) addChild(i, i.toString())
        binding.gridLayout.init()
    }

    private fun gridLayout() {
        binding.gridLayout.removeAllViews()
        binding.gridLayout.columnCount = 3
        binding.gridLayout.rowCount = 100
    }

    private fun createChild(col: GridLayout.Spec, row: GridLayout.Spec, id: Int, name: String): MaterialButton {
        val gridLayout = GridLayout.LayoutParams()
        gridLayout.setGravity(Gravity.CENTER)
        gridLayout.rowSpec = row
        gridLayout.columnSpec = col
        gridLayout.width = dpToPx(103f)
        gridLayout.height = dpToPx(108f)
        gridLayout.topMargin = dpToPx(1f)
        gridLayout.leftMargin = dpToPx(13f)
        val child = MaterialButton(this)
        child.text = name
        child.textSize = 12.5f
        child.id = id
        child.tag = name
        child.cornerRadius = dpToPx(10f)
        child.textAlignment = MaterialButton.TEXT_ALIGNMENT_CENTER
        child.setTextColor(ContextCompat.getColor(this,R.color.white))
        child.setBackgroundColor(ContextCompat.getColor(this,R.color.design_default_color_primary))
        child.layoutParams = gridLayout
        child.setOnClickListener(click)
        return child
    }

    private fun addChild(id: Int, name: String) {
        var i = 0 ; var c = 0 ; var r = 0
        while (i < 1) {
            if (c == 3) { c = 0 ; r++ }
            var rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, dpToPx(1f).toFloat())
            var colSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, dpToPx(1f).toFloat())
            if (r == 0 && c == 0) {
                colSpan = GridLayout.spec(GridLayout.UNDEFINED, 1)
                rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 2)
            }
            val child = createChild(colSpan, rowSpan, id, name)
            binding.gridLayout.addView(child)
            i++ ; c++ ; r++
        }
    }

    // endregion

}