package com.example.realtime_obstacle_detection.presentation.tensorflow

import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.realtime_obstacle_detection.data.ObstacleDetector

class TensorFlowLiteFrameAnalyzer (
    private val obstacleDetector: ObstacleDetector,
    private val onFpsCalculated: ((Int) -> Unit)? = null,
    private val onInferenceTime: ((Long) -> Unit)? = null
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0
    private var lastTimestamp = 0L
    private var frameCount = 0

    override fun analyze(image: ImageProxy) {
        val currentTimestamp = System.currentTimeMillis()
        if (lastTimestamp == 0L) {
            lastTimestamp = currentTimestamp
        }
        frameCount++
        // Update FPS every second.
        if (currentTimestamp - lastTimestamp >= 1000) {
            val fps = frameCount
            onFpsCalculated?.invoke(fps)
            Log.d("FPS", "FPS: $fps")
            frameCount = 0
            lastTimestamp = currentTimestamp
        }

        if(frameSkipCounter % 6 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees

            val startTime = System.currentTimeMillis()
            val rawBitmap = image.toBitmap()
            // Rotate the raw sensor frame to its upright orientation so the
            // detected scene (and the boxes drawn on it) match the live camera view.
            val bitmap = if (rotationDegrees != 0) {
                val matrix = Matrix().apply { postRotate(rotationDegrees.toFloat()) }
                Bitmap.createBitmap(rawBitmap, 0, 0, rawBitmap.width, rawBitmap.height, matrix, true)
            } else {
                rawBitmap
            }

            val endTime = System.currentTimeMillis()

            val duration = (endTime - startTime) / 1000.0

            Log.d("processing time", "Preprocessing and rotation took $duration seconds")

            // 1) Measure inference:
            val t0 = System.currentTimeMillis()
            obstacleDetector.detect(bitmap)
            val inferenceMs = System.currentTimeMillis() - t0
            onInferenceTime?.invoke(inferenceMs)

        }
        frameSkipCounter++

        image.close()

    }
}