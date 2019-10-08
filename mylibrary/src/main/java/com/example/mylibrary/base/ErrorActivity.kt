package com.example.mylibrary.base

import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import com.example.mylibrary.R
import kotlinx.android.synthetic.main.activity_error.*

class ErrorActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        val a = obtainStyledAttributes(R.styleable.AppCompatTheme)
        if (!a.hasValue(R.styleable.AppCompatTheme_windowActionBar)) {
            setTheme(R.style.Theme_AppCompat_Light_DarkActionBar)
        }
        a.recycle()

        val config = CustomActivityOnCrash.getConfigFromIntent(intent)

        if (config == null) {
            //This should never happen - Just finish the activity to avoid a recursive crash.
            finish()
            return
        }

        CustomActivityOnCrash.restartApplication(this, config)

//        if (config.isShowRestartButton && config.restartActivityClass != null) {
//            restartButton?.text = getString(R.string.customactivityoncrash_error_activity_restart_app)
//            restartButton?.setOnClickListener { CustomActivityOnCrash.restartApplication(this, config) }
//        } else {
//            restartButton?.setOnClickListener { CustomActivityOnCrash.closeApplication(this, config) }
//        }
//
//        if (config.isShowErrorDetails) {
//            moreInfoButton?.setOnClickListener {
//                //We retrieve all the error data and show it
//
//                val dialog = AlertDialog.Builder(this)
//                        .setTitle(R.string.customactivityoncrash_error_activity_error_details_title)
//                        .setMessage(CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, intent))
//                        .setPositiveButton(R.string.customactivityoncrash_error_activity_error_details_close, null)
//                        .setNeutralButton(R.string.customactivityoncrash_error_activity_error_details_copy
//                        ) { _, _ -> copyErrorToClipboard() }
//                        .show()
//                val textView = dialog.findViewById<TextView>(android.R.id.message)
//
//                if (textView != null) {
//                    textView!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.customactivityoncrash_error_activity_error_details_text_size))
//                }
//            }
//        } else {
//            moreInfoButton?.visibility = View.GONE
//        }
//
//        val defaultErrorActivityDrawableId = config.errorDrawable
//
//        if (defaultErrorActivityDrawableId != null) {
//            errorImageView?.setImageDrawable(ResourcesCompat.getDrawable(resources, defaultErrorActivityDrawableId, theme))
//        }
    }

    private fun copyErrorToClipboard() {
        val errorInformation = CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, intent)

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        //Are there any devices without clipboard...?
        if (clipboard != null) {
            val clip = ClipData.newPlainText(getString(R.string.customactivityoncrash_error_activity_error_details_clipboard_label), errorInformation)
            clipboard.primaryClip = clip
            Toast.makeText(this, R.string.customactivityoncrash_error_activity_error_details_copied, Toast.LENGTH_SHORT).show()
        }
    }

}