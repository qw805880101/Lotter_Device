package com.tc.lottery_device.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.tc.lottery_device.R

class LocalImageViewBanner(context: Context, view: View) : Holder<String>(view) {

    private var imageView: ImageView? = null

    private val context = context

    override fun initView(itemView: View?) {
        imageView = itemView!!.findViewById(R.id.ivBanner)
    }

    override fun updateUI(data: String?) {
        Glide.with(context).load(data).into(imageView!!)
    }
}