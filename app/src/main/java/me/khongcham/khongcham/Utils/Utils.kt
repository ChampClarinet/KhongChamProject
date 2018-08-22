package me.khongcham.khongcham.Utils

import android.content.Context
import android.graphics.drawable.Drawable
import java.io.IOException
import java.math.RoundingMode
import java.text.DecimalFormat

class Utils {

    companion object {

        fun getDrawableFromAssets(context: Context, pictureFileName: String): Drawable? {
            val am = context.assets
            try {
                val stream = am.open(pictureFileName)
                return Drawable.createFromStream(stream, null)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }

        fun roundTwoDigits(double: Double): Double {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            return df.format(double).toDouble()
        }

        fun roundToInt(double: Double): Int {
            val df = DecimalFormat("#")
            df.roundingMode = RoundingMode.CEILING
            return df.format(double).toInt()
        }

        fun doubleToString(double: Double): String {
            var out = roundTwoDigits(double)
            if (isDouble(out)) return out.toString()
            return roundToInt(out).toString()
        }

        private fun isDouble(double: Double): Boolean{
            return double * 100 % 100 > 0
        }

    }

}