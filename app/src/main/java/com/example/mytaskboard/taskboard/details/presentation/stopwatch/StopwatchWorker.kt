package com.example.mytaskboard.taskboard.details.presentation.stopwatch

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.mytaskboard.R
import com.example.mytaskboard.main.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class StopwatchWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val stopwatch: Stopwatch
) : CoroutineWorker(appContext, workerParams) {

    private val notificationBuilder =
        NotificationCompat.Builder(appContext, NOTIFICATION_CHANNEL_ID)
    private val notificationManager =
        appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val mainIntent = Intent(applicationContext, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }

    private val mainPendingIntent = PendingIntent.getActivity(
        applicationContext,
        0,
        mainIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Use FLAG_IMMUTABLE for Android 12+
    )

    @SuppressLint("DefaultLocale")
    override suspend fun doWork(): Result {
        val taskTitle = inputData.getString(KEY_TASK_TITLE)!!
        setForeground(createForegroundInfo(taskTitle))
        // Collect time updates and update notification
        stopwatch.timeFlow.collect { seconds ->
            val hours = seconds / 3600
            val minutes = (seconds % 3600) / 60
            val secs = seconds % 60
            notificationManager.notify(
                NOTIFICATION_ID,
                notificationBuilder.setContentText(
                    String.format("%02d:%02d:%02d", hours, minutes, secs)
                ).build()
            )
        }
        return Result.success()
    }

    // Creates an instance of ForegroundInfo which can be used to update the ongoing notification.
    private fun createForegroundInfo(taskTitle: String): ForegroundInfo {

        // Create a Notification channel
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, taskTitle, importance)
        notificationManager.createNotificationChannel(channel)

        // Create Notification builder
        val notification = notificationBuilder
            .setContentTitle(taskTitle)
            .setContentText("00:00:00")
            .setSmallIcon(R.drawable.timer_icon)
            .setOngoing(true)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setContentIntent(mainPendingIntent)
            .build()

        // Return ForegroundInfo
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            ForegroundInfo(NOTIFICATION_ID, notification, FOREGROUND_SERVICE_TYPE_SPECIAL_USE)
        } else {
            ForegroundInfo(NOTIFICATION_ID, notification)
        }
    }

    companion object {
        const val NOTIFICATION_ID = 1_000_000
        const val NOTIFICATION_CHANNEL_ID = "STOPWATCH_NOTIFICATION"
        const val KEY_TASK_TITLE = "KEY_TASK_TITLE"
    }
}