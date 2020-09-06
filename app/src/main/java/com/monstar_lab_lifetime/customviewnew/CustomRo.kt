package com.monstar_lab_lifetime.customviewnew

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CustomRo : View {
    private var path= Path()
    private var p= Paint()
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
    fun extractAtt(attributeSet: AttributeSet){

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.moveTo(width/2f,0f)
        path.cubicTo(
            (( width / 4)+30).toFloat(), (height/4f)+30f,
            0f, height / 2f,
            0f,  height / 2f)
        path.cubicTo(
           0f, (height/2f),
            (height/4f)+30f, (3*height / 4f)-30f,
            width/2f,  height.toFloat())
        path.cubicTo(
            width/2f, (height.toFloat()),
            (3*width/4f)-30f, (3*height / 4f)-30f,
            width.toFloat(),  height/2f)

        path.cubicTo(
            width.toFloat(),  height/2f,
            (3*width/4f)-30f, (height / 4f)+30f,
            width/2.toFloat(),  0f)

        p.setColor(Color.RED)
        p.setStyle(Paint.Style.FILL)
        canvas?.drawPath(path, p)
    }
}