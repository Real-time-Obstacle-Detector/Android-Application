package com.example.realtime_obstacle_detection.ui.screens.settings

import android.content.Context
import android.content.SharedPreferences
import com.example.realtime_obstacle_detection.ui.screens.initialConfigurations.ModelConfig
import com.example.realtime_obstacle_detection.ui.screens.initialConfigurations.Models

/**
 * Persists the detection configuration chosen on the Settings page so that every
 * detection action (Blind-Protector, Walk-Around, Bounding Box) reuses it.
 *
 * Defaults match the previous configuration dialog defaults.
 */
object ConfigPreferences {

    private const val PREFS_NAME = "rod_settings"

    private const val KEY_MODEL = "selected_model"
    private const val KEY_CONFIG_THRESHOLD = "config_threshold"
    private const val KEY_IOU_THRESHOLD = "iou_threshold"
    private const val KEY_THREAD_COUNT = "thread_count"
    private const val KEY_USE_NNAPI = "use_nnapi"
    private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"

    private fun prefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun loadModelConfig(context: Context): ModelConfig {
        val prefs = prefs(context)
        val model = prefs.getString(KEY_MODEL, null)
            ?.let { saved -> Models.entries.firstOrNull { it.name == saved } }
            ?: Models.DEFAULT
        return ModelConfig(
            selectedModel = model,
            configThreshold = prefs.getFloat(KEY_CONFIG_THRESHOLD, 0.2f),
            iouThreshold = prefs.getFloat(KEY_IOU_THRESHOLD, 0.2f),
            threadCount = prefs.getInt(KEY_THREAD_COUNT, 4),
            useNNAPI = prefs.getBoolean(KEY_USE_NNAPI, true)
        )
    }

    fun saveModelConfig(context: Context, config: ModelConfig) {
        prefs(context).edit()
            .putString(KEY_MODEL, config.selectedModel.name)
            .putFloat(KEY_CONFIG_THRESHOLD, config.configThreshold)
            .putFloat(KEY_IOU_THRESHOLD, config.iouThreshold)
            .putInt(KEY_THREAD_COUNT, config.threadCount)
            .putBoolean(KEY_USE_NNAPI, config.useNNAPI)
            .apply()
    }

    fun isOnboardingCompleted(context: Context): Boolean =
        prefs(context).getBoolean(KEY_ONBOARDING_COMPLETED, false)

    fun setOnboardingCompleted(context: Context, completed: Boolean) {
        prefs(context).edit()
            .putBoolean(KEY_ONBOARDING_COMPLETED, completed)
            .apply()
    }
}
