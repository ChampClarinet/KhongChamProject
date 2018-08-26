package me.khongcham.khongcham

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import kotlinx.android.synthetic.main.activity_scanner.*
import me.khongcham.khongcham.Fragments.HomeFragment.Companion.scanRequestCode
import java.util.*


class ScannerActivity : AppCompatActivity() {

    private var goodsId: Int? = null
    private var goodsName: String? = null
    private var goodsQuantity = 0
    private var lastText: String? = null

    private var barcodeView: DecoratedBarcodeView? = null
    private var beepManager: BeepManager? = null

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            if (result.text == null || result.text == lastText) return

            lastText = result.text
            barcodeView?.setStatusText(result.text)

            beepManager?.playBeepSoundAndVibrate()

            goodsLabel.text = result.text
            goodsQuantity = 1
            syncQuantity()

        }

        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        barcodeView = findViewById(R.id.barcode_scanner)
        val formats = Arrays.asList(BarcodeFormat.CODABAR,
                BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.UPC_EAN_EXTENSION,
                BarcodeFormat.CODE_39, BarcodeFormat.CODE_128, BarcodeFormat.CODE_93,
                BarcodeFormat.ITF, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8)
        barcodeView!!.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        barcodeView!!.decodeContinuous(callback)
        barcodeView?.barcodeView?.cameraSettings?.isAutoFocusEnabled = true

        beepManager = BeepManager(this)

        button_cancel.setOnClickListener {
            finish()
        }

        button_ok.setOnClickListener {
            if (goodsId != null) returnResult()
            else finish()
        }

        card_stock_quantity_add.setOnClickListener {
            goodsQuantity++
            syncQuantity()
        }

        card_stock_quantity_remove.setOnClickListener {
            goodsQuantity--
            syncQuantity()
        }

        syncQuantity()

    }

    override fun onResume() {
        super.onResume()
        barcodeView?.resume()
    }

    override fun onPause() {
        super.onPause()

        barcodeView?.pause()
    }


    private fun syncQuantity() {
        card_stock_quantity.text = goodsQuantity.toString()
    }

    fun onScanned() {
        if (goodsId == null) return
        //retrieve data from scanner
        //goodsId =
        //goodsName =
        //goodsLabel.text = goodsName
    }

    private fun returnResult() {
        val intent = Intent()
        //set intent data
        intent.putExtra(getString(R.string.scanner_goods_id), goodsId)
        intent.putExtra(getString(R.string.scanner_goods_quantity), goodsQuantity)
        setResult(scanRequestCode, intent)
        finish()
    }

}
