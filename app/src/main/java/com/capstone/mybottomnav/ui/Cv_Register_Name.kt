package com.capstone.mybottomnav.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.capstone.mybottomnav.R

class Cv_Register_Name : AppCompatEditText {


    private lateinit var ikon: Drawable
    private lateinit var bg: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ){
        init()
    }

    private fun init(){
      //  ikon = ContextCompat.getDrawable(context, R.drawable.ic_person) as Drawable
        bg = ContextCompat.getDrawable(context, R.drawable.ic_blank) as Drawable

        showLogo()

    }

    private fun showLogo() = setButtonDrawables(startOfTheText = ikon)

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }
    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)
        background = bg
        hint = "Input Your Name"
        textSize = 14f
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

}