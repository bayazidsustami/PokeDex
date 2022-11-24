package com.example.pokedex.presentation.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.pokedex.R
import com.example.pokedex.common.PokeSort
import com.example.pokedex.databinding.ViewButtonSortingBinding
import com.example.pokedex.presentation.gone
import com.example.pokedex.presentation.visible

class ButtonSortingView: LinearLayout {

    private val binding: ViewButtonSortingBinding = ViewButtonSortingBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var onShortingListener: OnSortingChangeListener? = null

    private var state: Int = 0

    constructor(context: Context?) : super(context) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {

        binding.root.setOnClickListener {
            setState()
        }
    }

    private fun setState() {
        if (state > 2) {
            state = 0
        }
        state += 1
        when (state) {
            0 -> {
                showNumber()
                onShortingListener?.onSorting(PokeSort.NUMBER)
            }
            1 -> {
                showAsc()
                onShortingListener?.onSorting(PokeSort.ASC)
            }
            2 -> {
                showDesc()
                onShortingListener?.onSorting(PokeSort.DESC)
            }
            else -> {
                showNumber()
                onShortingListener?.onSorting(PokeSort.NUMBER)
            }
        }
    }

    private fun showAsc() {
        with(binding) {
            icHashtag.gone()
            containerText.visible()
            topText.text = context.getText(R.string.top_text)
            bottomText.text = context.getText(R.string.bottom_text)
        }
    }

    private fun showDesc() {
        with(binding) {
            icHashtag.gone()
            containerText.visible()
            topText.text = context.getText(R.string.bottom_text)
            bottomText.text = context.getText(R.string.top_text)
        }
    }

    private fun showNumber() {
        with(binding) {
            icHashtag.visible()
            containerText.gone()
        }
    }

    fun onShortingChangeListener(onSortingListener: OnSortingChangeListener) {
        this.onShortingListener = onSortingListener
    }

    fun interface OnSortingChangeListener {
        fun onSorting(sort : PokeSort)
    }

}