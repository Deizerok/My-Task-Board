package com.example.mytaskboard.taskboard.details.presentation.stopwatch

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.mytaskboard.R
import com.example.mytaskboard.taskboard.details.presentation.TaskDetailsFragment

class StopwatchWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val stopwatch: Stopwatch,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManager
) : CoroutineWorker(appContext, workerParams) {

    // Intents
    private val mainIntent = Intent(applicationContext, TaskDetailsFragment::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }

    private val mainPendingIntent = PendingIntent.getActivity(
        applicationContext,
        0,
        mainIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Use FLAG_IMMUTABLE for Android 12+
    )

    override suspend fun doWork(): Result {
        setForeground(createForegroundInfo())
        // Collect time updates and update notification
        stopwatch.timeFlow.collect { seconds ->
            notificationManager.notify(
                NOTIFICATION_ID, notificationBuilder.setContentText(seconds.toString()).build()
            )
        }
        return Result.success()
    }

    // Creates an instance of ForegroundInfo which can be used to update the ongoing notification.
    private fun createForegroundInfo(): ForegroundInfo {
        val title = applicationContext.getString(R.string.stopwatch)

        // Create a Notification channel
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, title, importance)
        notificationManager.createNotificationChannel(channel)

        // Create Notification builder
        val notification = notificationBuilder
            .setContentTitle(title)
            .setContentText("0")
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
    }
}