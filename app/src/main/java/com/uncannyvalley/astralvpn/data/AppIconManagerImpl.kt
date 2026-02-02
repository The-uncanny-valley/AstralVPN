package com.uncannyvalley.astralvpn.data

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.uncannyvalley.astralvpn.domain.model.AppIcon
import com.uncannyvalley.astralvpn.domain.model.AppIconManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppIconManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AppIconManager {

    override fun setIcon(icon: AppIcon) {
        val pm = context.packageManager
        val packageName = context.packageName

        val mapping = mapOf(
            AppIcon.DEFAULT to "$packageName.LauncherDefault",
            AppIcon.LIGHT to "$packageName.LauncherLight",
            AppIcon.CLOCK to "$packageName.LauncherClock",
            AppIcon.CALC to "$packageName.LauncherCalc"
        )

        mapping.forEach { (type, alias) ->
            val newState = if (type == icon) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }

            pm.setComponentEnabledSetting(
                ComponentName(packageName, alias),
                newState,
                PackageManager.DONT_KILL_APP
            )

            Log.d("AppIconManager", "Alias $alias set to $newState")
        }
    }
}