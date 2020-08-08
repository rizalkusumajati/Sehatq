package com.riztech.sehatq.widget

import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.riztech.sehatq.R
import com.riztech.sehatq.util.applyPixelToDp

class SehatqEditTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        attrs?.let {
            val style = context.obtainStyledAttributes(attrs, R.styleable.SehatqEditTextView)

            val textSize = style.getInt(R.styleable.SehatqEditTextView_editSize, -1)
            val editType = style.getInt(R.styleable.SehatqEditTextView_editType, 0)

            try {
                setTypeface(Typeface.createFromAsset(context.assets, "fonts/capriola_regular.ttf"))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            setPadding(applyPixelToDp(context, 16))
            isCursorVisible = true

            when (editType) {
                0 -> {
                    this.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.secondaryFontHeadline
                        )
                    )

                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                    background = ContextCompat.getDrawable(context, R.drawable.choices_primary_background)
                }

                1 -> {
                    this.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.secondaryFontHeadline
                        )
                    )

                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                    background = ContextCompat.getDrawable(context, R.drawable.choices_secondary_background)
                }
            }

            if (textSize > 0) {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
            }

        }
    }

//    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
//        super.onFocusChanged(focused, direction, previouslyFocusedRect)
//    }
}