package com.koi.testdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.graphics.toColorInt
import com.drake.tooltip.toast
import com.google.android.material.button.MaterialButton
import com.hyy.highlightpro.HighlightPro
import com.hyy.highlightpro.parameter.Constraints
import com.hyy.highlightpro.parameter.HighlightParameter
import com.hyy.highlightpro.parameter.MarginOffset
import com.hyy.highlightpro.shape.CircleShape
import com.hyy.highlightpro.shape.OvalShape
import com.hyy.highlightpro.shape.RectShape
import com.hyy.highlightpro.util.dp
import com.hyy.highlightpro.util.AnimUtil
import com.hyy.highlightpro.util.PaintUtils

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        findViewById<MaterialButton>(R.id.btn_step_first).postDelayed({
            showHighlightSteps()
        }, 500)
    }

    private fun showHighlightSteps() {
        HighlightPro.with(this)
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_step_first)
                    .setTipsViewId(R.layout.guide_step_first)
                    .setNextTipsViewId(R.id.highlight_next)
                    .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighlightTopPadding(8f.dp)
                    .setHighlightRightPadding(20f.dp)
                    .setHighlightLeftPadding(8f.dp)
//                    .setHighlightBottomPadding(10f.dp)
//                    .setHighlightHorizontalPadding(8f.dp)
                    .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
                    .setTipViewDisplayAnimation(AnimUtil.getScaleAnimation())
                    .setOnlyButtonClick(false)
                    .build()
            }
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_step_second)
                    .setTipsViewId(R.layout.guide_step_second)
                    .setNextTipsViewId(R.id.highlight_next)
                    .setHighlightShape(CircleShape().apply { setPaint(PaintUtils.getDashPaint()) })
                    .setHighlightHorizontalPadding(20f.dp)
                    .setHighlightVerticalPadding(20f.dp)
                    .setConstraints(Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(top = 8.dp))
                    .setTipViewDisplayAnimation(AnimUtil.getScaleAnimation())
                    .setOnlyButtonClick(true)
                    .build()
            }
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_step_third)
                    .setTipsViewId(R.layout.guide_step_third)
                    .setNextTipsViewId(R.id.highlight_next)
                    .setHighlightShape(OvalShape())
                    .setHighlightHorizontalPadding(12f.dp)
                    .setHighlightVerticalPadding(12f.dp)
                    .setConstraints(Constraints.BottomToTopOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(bottom = 6.dp))
                    .setTipViewDisplayAnimation(AnimUtil.getScaleAnimation())
                    .setOnlyButtonClick(true)
                    .build()
            }
            .setBackgroundColor("#80000000".toColorInt())
            .setOnMaskViewClickCallback {
                Log.e("koio","当前点击的view == ${it.id}")
//                toast("当前点击的view == ${it.id}")
            }
            .setOnShowCallback { index ->
                //do something
                toast("第 ${index + 1} 步显示了")
            }
            .setOnDismissCallback {
                //do something
                toast("完成引导步骤")
            }
            .interceptBackPressed(false)
            .show()
    }
}