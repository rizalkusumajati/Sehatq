package com.riztech.sehatq.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.riztech.sehatq.R

class SehatqTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {


    init {
        attrs?.let {
            val style = context.obtainStyledAttributes(attrs, R.styleable.SehatqTextView)

            val textType = style.getInt(R.styleable.SehatqTextView_tntType, 0)
            val textSize = style.getInt(R.styleable.SehatqTextView_tntSize, -1)

            try {
                setTypeface(Typeface.createFromAsset(context.assets, "fonts/capriola_regular.ttf"))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            when (textType) {
                0 -> {
                    this.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorPrimary
                        )
                    )

                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)

                }
                1 -> {

                    this.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.secondaryFontDescription
                        )
                    )

                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                }
                2 -> {
                    setTextColor(ContextCompat.getColor(context, R.color.secondaryFontHeadline))

                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
                }
                3 -> {
                    setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                }

                4 -> {
                    try {
                        setTypeface(Typeface.createFromAsset(context.assets, "fonts/opensans_bold.ttf"))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    this.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.secondaryFontHeadline
                        )
                    )

                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                }

                5 -> {
                    try {
                        setTypeface(Typeface.createFromAsset(context.assets, "fonts/opensans_regular.ttf"))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    this.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.secondaryFontHeadline
                        )
                    )

                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                }

                6 -> {
                    try {
                        setTypeface(Typeface.createFromAsset(context.assets, "fonts/opensans_regular.ttf"))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    this.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.secondaryFontDescription
                        )
                    )

                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                }

                else -> {

                }
            }

            if (textSize > 0) {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
            }
        }
    }
}