package com.monstar_lab_lifetime.customviewnew

import android.content.Context
import android.graphics.*
import android.os.SystemClock
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.Dimension
import java.text.SimpleDateFormat
import java.util.*

class CustomView : View {
    private var mColorSmile = 0
    private var mColorStroke = 0
    private var mColorCenter = 0
    private var mAngle = 0
    private var isRunTime = false
    private var mSizeText = 0
    private var mNumberHour = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

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

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
       // createThread()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        isRunTime = false
    }

    companion object {
        var p = Paint()
        var bound = Rect()
    }

    private fun extractAtt(attributeSet: AttributeSet) {
        val customView = context.obtainStyledAttributes(attributeSet, R.styleable.CustomView)
        mColorSmile =
            customView.getColor(R.styleable.CustomView_colorSmile, Color.parseColor("#FF6E40"))
        mSizeText = customView.getDimensionPixelSize(R.styleable.CustomView_sizeText, Dimension.PX)
        mColorCenter =
            customView.getColor(R.styleable.CustomView_colorCenter, Color.parseColor("#FFFF00"))
        mColorStroke =
            customView.getColor(R.styleable.CustomView_colorStroke, Color.parseColor("#FFFF00"))
        customView.recycle()// Lý do là để bỏ những rằng buộc không cần thiết đến với
        // các dữ liệu không được sử dụng lại nữa (Vì mục đích của ta chỉ cần lấy được các giá trị của attribute).

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val wi = MeasureSpec.EXACTLY
        val widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // Toast.makeText(context,widthMode.toString(),Toast.LENGTH_SHORT).show()
        val widthSize = MeasureSpec.getSize(widthMeasureSpec);
        val heightMode = MeasureSpec.getMode(heightMeasureSpec);
        val heightSize = MeasureSpec.getSize(heightMeasureSpec);
        var with: Int = 0
        if (widthMode == MeasureSpec.EXACTLY) {
            with = widthSize
            //  Toast.makeText(context,with.toString(),Toast.LENGTH_SHORT).show()
        }
        var he: Int = 0
        if (heightMode == MeasureSpec.EXACTLY) {
            he = heightSize
            // Toast.makeText(context,he.toString(),Toast.LENGTH_SHORT).show()
        }
        setMeasuredDimension(with, he)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //val p=Paint()
        drawCircle(canvas)
       p.reset()
        p.isAntiAlias=true
        drawTextTitle(canvas)
        drawImage(canvas)
        drawTime(canvas)
        drawNumber(canvas)
        drawTimeH(canvas)
        drawCenter(canvas)
        postInvalidateDelayed(1000)
    }

    private fun drawCircle(canvas: Canvas?) {
        p.color = mColorStroke
        p.style = Paint.Style.STROKE
        p.isAntiAlias = true
        //p.color=Color.parseColor("#40C4FF")
        p.strokeWidth = 10f
        canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, width.toFloat() / 2 - 5f, p)
    }

    private fun drawCenter(canvas: Canvas?) {
        p.style = Paint.Style.FILL
        p.color = mColorCenter
        canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, 8.0f, p)
    }

    private fun drawTextTitle(canvas: Canvas?) {
        p.textSize = mSizeText.toFloat()
        p.color = mColorSmile
        p.getTextBounds("default", 0, "default".length, bound)//set các giá trị cho text
        canvas?.drawText("Smile", 250.0f, 140.0f, p)
    }

    private fun drawImage(canvas: Canvas?) {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.clock)
       // val retRec = Rect(0, 0, bitmap.width, bitmap.height)
        val retDes = Rect(250, 430, 450, 630)
        canvas?.drawBitmap(bitmap, null, retDes, null)
        //Toast.makeText(context,bitmap.width.toString(),Toast.LENGTH_SHORT).show()
    }

    private fun drawTime(canvas: Canvas?) {
        // p.color=Color.parseColor("#000000")
        val currentTime = Date()//
//        val cu = Calendar.getInstance()
//        val d = cu.get(Calendar.HOUR_OF_DAY)
//        val t = cu.get(Calendar.MINUTE)
//        val n = cu.get(Calendar.SECOND)
      //  val momo = d.toString() + ":" + t.toString() + ":" + n.toString()
        val formatTime = SimpleDateFormat("hh:mm:ss")
        val textTime = formatTime.format(currentTime)
        canvas?.drawText(textTime, 186.0f, 230.0f, p)
       // canvas?.drawText(momo, 186.0f, 230.0f, p)

        val formatDate = SimpleDateFormat("dd/MM/yyyy")
        canvas?.drawText(formatDate.format(currentTime), 140.0f, 320.0f, p)
    }

    private fun drawNumber(canvas: Canvas?) {
        p.textSize = 40f
        var radius = width / 2 - 50
        //vẽ số giờ lên vòng tròn
        for (number in 1..mNumberHour.size) {
            var numberToString = number.toString()
            p.getTextBounds(numberToString, 0, numberToString.length, bound)
            val angle = Math.PI / 6 * (number - 3)
            Log.d("ok", number.toString())
            //Toast.makeText(context,bound.toString(),Toast.LENGTH_SHORT).show()
            val x = (width / 2 + Math.cos(angle) * (radius) - bound.width() / 2).toFloat()
            val y = (height / 2 + Math.sin(angle) * (radius) + bound.height() / 2).toFloat()
            canvas?.drawText(numberToString, x, y, p)
            //Toast.makeText(context,(bound.width()).toString(),Toast.LENGTH_SHORT).show()

        }
    }

    private fun drawH(canvas: Canvas?, location: Float, isHours: Boolean) {

        val anglee = Math.PI * location / 30 - Math.PI / 2
        var hands: Float = 0.0f
        if (isHours) {
            hands = width.toFloat() / 2 - 140
        } else {
            hands = width.toFloat() / 2 - 80
        }

        canvas?.drawLine(
            width.toFloat() / 2, height.toFloat() / 2,
            (width.toFloat() / 2 + Math.cos(anglee) * hands).toFloat(),
            (height.toFloat() / 2 + Math.sin(anglee) * hands-7).toFloat(), p
        )
    }

    private fun drawTimeH(canvas: Canvas?) {
        var c = Calendar.getInstance()
        var hours = c.get(Calendar.HOUR_OF_DAY)
        if (hours > 12) {
            hours -= 12
        }
        p.strokeWidth = 5f
        p.color = Color.parseColor("#FF5252")
        drawH(canvas, (hours + c.get(Calendar.MINUTE) / 60)*5f, true)
        p.color = Color.parseColor("#000000")
        drawH(canvas, (  c.get(Calendar.MINUTE)).toFloat(), false)
        p.color = Color.parseColor("#448AFF")
        drawH(canvas, ( c.get(Calendar.SECOND)).toFloat(), false)
    }

    private fun createThread() {
        isRunTime = true
        val run = Runnable {
            while (true) {
                postInvalidate()
                SystemClock.sleep(1000)
            }
        }
        val th = Thread(run)
        th.start()
    }


}