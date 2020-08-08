package com.riztech.sehatq.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.riztech.sehatq.R
import kotlinx.android.synthetic.main.button_layout.view.*

class SehatqButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {


    init {
        LayoutInflater.from(context)
            .inflate(R.layout.button_layout, this, true)

        attrs?.let {
            val style = context.obtainStyledAttributes(attrs, R.styleable.SehatqButtonView)

            val leftIcon = style.getDrawable(R.styleable.SehatqButtonView_leftIcon)
            val rightIcon = style.getDrawable(R.styleable.SehatqButtonView_rightIcon)
            val text = style.getString(R.styleable.SehatqButtonView_textAction)

            val tntType = style.getInt(R.styleable.SehatqButtonView_buttonType, 0)
            val textSize = style.getInt(R.styleable.SehatqButtonView_buttonTextSize, -1)

            leftIcon?.let {
                ivLeftIcon.setImageDrawable(leftIcon)
            }

            rightIcon?.let {
                ivRightIcon.setImageDrawable(rightIcon)
            }

            textSize?.let {
                tvAction.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
            }

            tvAction.setText(text)

            when(tntType){
                0 -> {
                    backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.button_background)
                    tvAction.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))

                    setOnTouchListener { view, motionEvent ->
                        when(motionEvent.action){
                            MotionEvent.ACTION_DOWN -> {
                                backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.button_primary_selected)
                                true
                            }
                            MotionEvent.ACTION_UP ->{
                                backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.choices_primary_selected)
                            }
                            MotionEvent.ACTION_OUTSIDE ->{
                                backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.choices_primary_selected)
                            }
                            else -> {
                                backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.choices_primary_selected)
                            }

                        }

                        false
                    }
                }
                1 -> {
                    backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.button_background_secondary)
                    tvAction.setTextColor(ContextCompat.getColor(context, R.color.secondaryFontHeadline))

                    setOnTouchListener { view, motionEvent ->
                        when(motionEvent.action){
                            MotionEvent.ACTION_DOWN -> {
                                backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.choices_secondary_selected)
                                tvAction.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                                true
                            }
                            MotionEvent.ACTION_UP ->{
                                backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.choices_secondary_default)
                                tvAction.setTextColor(ContextCompat.getColor(context, R.color.secondaryFontHeadline))

                            }
                            MotionEvent.ACTION_OUTSIDE ->{
                                backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.choices_secondary_default)
                                tvAction.setTextColor(ContextCompat.getColor(context, R.color.secondaryFontHeadline))
                            }
                            else ->{
                                backgroundButton.background = ContextCompat.getDrawable(context, R.drawable.choices_secondary_default)
                                tvAction.setTextColor(ContextCompat.getColor(context, R.color.secondaryFontHeadline))
                            }
                        }

                        false
                    }
                }
            }

        }
    }
}