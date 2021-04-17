package com.aaa  .draw

import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.MutableLiveData
import com.aaa.draw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val RGB = MutableLiveData<rgb>()

    data class rgb(var r:Int, var g:Int, var b:Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater,null,false)


        binding.seekBarB.progress=40
        binding.seekBarR.progress=80
        binding.seekBarG.progress=90



        binding.seekBarB.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                RGB.postValue(rgb(binding.seekBarR.progress,binding.seekBarG.progress,progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        binding.seekBarG.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                RGB.postValue(rgb(binding.seekBarR.progress,progress,binding.seekBarB.progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        binding.seekBarR.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               RGB.postValue(rgb(progress,binding.seekBarG.progress,binding.seekBarB.progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        RGB.observe(this,{
            binding.wu.apply {
                r=it.r
                g=it.g
                b=it.b
                invalidate()
            }
            binding.rt.text=it.r.toString()
            binding.gt.text=it.g.toString()
            binding.bt.text=it.b.toString()

            val cc=it.r*256*256+it.g*256+it.b
            val dd=String.format("HEX: %06X",cc)
            binding.total.text=dd
        })
        RGB.postValue(rgb(binding.seekBarR.progress,binding.seekBarG.progress,binding.seekBarB.progress))

        setContentView(binding.root)
    }
}