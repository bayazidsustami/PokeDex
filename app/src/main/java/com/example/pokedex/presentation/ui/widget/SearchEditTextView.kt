package com.example.pokedex.presentation.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import com.example.pokedex.databinding.ViewEdittextSearchBinding
import com.example.pokedex.presentation.gone
import com.example.pokedex.presentation.visible

class SearchEditTextView: FrameLayout {

    private val binding: ViewEdittextSearchBinding = ViewEdittextSearchBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var searchTextListener: SearchTextListener? = null

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    fun setOnSearchTextListener(searchTextListener: SearchTextListener) {
        this.searchTextListener = searchTextListener
    }

    private fun init() {
        binding.etSearch.setOnFocusChangeListener { _, isFocus ->
            if (isFocus) {
                hideHint()
            } else {
                showHint()
            }
        }

        binding.btnClose.setOnClickListener {
            binding.etSearch.clearFocus()
            binding.etSearch.setText("")
            searchTextListener?.onCloseButton()
        }

        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            searchTextListener?.onQueryString(text.toString())
        }

    }

    private fun showHint() {
        with(binding) {
            tvHint.visible()
            ivLeftIcon.gone()
            btnClose.gone()
        }
    }

    private fun hideHint() {
        with(binding) {
            tvHint.gone()
            ivLeftIcon.visible()
            btnClose.visible()
        }
    }

    interface SearchTextListener {
        fun onQueryString(query: String)
        fun onCloseButton()
    }
}