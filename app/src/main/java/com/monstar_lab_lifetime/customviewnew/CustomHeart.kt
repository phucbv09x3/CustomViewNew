package com.monstar_lab_lifetime.customviewnew

import android.app.Notification
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CustomHeart : View {
    private var path=Path()
    private var p=Paint()
    constructor(c: Context, attributeSet: AttributeSet) : super(c, attributeSet) {
        extractAtt(attributeSet)
    }
    constructor(c: Context, attributeSet: AttributeSet, defstyle: Int) : super(
        c,
        attributeSet,
        defstyle
    ) {
        extractAtt(attributeSet)
    }
    private fun extractAtt(attributeSet: AttributeSet){
        val customView=context.obtainStyledAttributes(attributeSet,R.styleable.CustomView)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.moveTo(width/2f,height/5f)
        path.cubicTo(
            (5 * width / 14).toFloat(), 0F,
            0f, height / 15f,
            width / 28f, 2 * height / 5f);



        path.cubicTo(width / 14f, 2 * height / 3f,
            3 * width / 7f, 5 * height / 6f,
            width / 2f, height.toFloat())


        path.cubicTo(4 * width / 7f, 5 * height / 6f,
            13 * width / 14f, 2 * height / 3f,
            27 * width / 28f, 2 * height / 5f)


        path.cubicTo(width.toFloat(), height / 15f,
            9 * width / 14f, 0f,
            width / 2f, height / 5f)

        p.setColor(Color.RED)
        p.setStyle(Paint.Style.FILL_AND_STROKE)
        canvas?.drawPath(path, p)
    }
}