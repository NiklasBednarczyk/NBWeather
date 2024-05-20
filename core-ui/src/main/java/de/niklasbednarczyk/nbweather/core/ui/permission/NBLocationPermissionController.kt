package de.niklasbednarczyk.nbweather.core.ui.permission

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.context.getActivity
import de.niklasbednarczyk.nbweather.core.ui.context.startIntent
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarModel

data class NBLocationPermissionController(
    private val context: Context,
    private val launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    private val onPermissionGranted: () -> Unit
) {

    companion object {

        private const val LOCATION_PERMISSION_COARSE =
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        private const val LOCATION_PERMISSION_FINE =
            android.Manifest.permission.ACCESS_FINE_LOCATION

        private fun Map<String, Boolean>.isPermissionGranted(
            permission: String
        ): Boolean {
            return getOrDefault(permission, false)
        }

        private fun Map<String, Boolean>.isAtLeastOneLocationPermissionGranted(): Boolean {
            return isPermissionGranted(LOCATION_PERMISSION_COARSE) ||
                    isPermissionGranted(LOCATION_PERMISSION_FINE)
        }

        private fun Activity?.isLocationPermissionDeniedForSecondTime(): Boolean {
            return if (this == null || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                false
            } else {
                !shouldShowRequestPermissionRationale(LOCATION_PERMISSION_COARSE) &&
                        !shouldShowRequestPermissionRationale(LOCATION_PERMISSION_FINE)
            }
        }

        @Composable
        fun rememberNBLocationPermissionController(
            onPermissionGranted: () -> Unit,
            showSnackbar: (snackbar: NBSnackbarModel) -> Unit,
        ): NBLocationPermissionController {
            val context = LocalContext.current
            val activity = context.getActivity()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                if (permissions.isAtLeastOneLocationPermissionGranted()) {
                    onPermissionGranted()
                } else if (activity.isLocationPermissionDeniedForSecondTime()) {
                    val snackbar = NBSnackbarModel(
                        message = NBString.ResString(R.string.snackbar_location_permission_denied_message),
                        action = NBSnackbarActionModel(
                            label = NBString.ResString(R.string.snackbar_location_permission_denied_action_label),
                            onActionPerformed = {
                                val intent =
                                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                        data = Uri.fromParts("package", context.packageName, null)
                                    }
                                context.startIntent(
                                    intent = intent,
                                    showSnackbar = showSnackbar
                                )
                            }
                        )
                    )
                    showSnackbar(snackbar)
                }
            }

            return remember(context, launcher, onPermissionGranted) {
                NBLocationPermissionController(
                    context = context,
                    launcher = launcher,
                    onPermissionGranted = onPermissionGranted
                )
            }
        }

    }

    fun requestPermission() {
        if (isAtLeastOneLocationPermissionGranted()) {
            onPermissionGranted()
        } else {
            launcher.launch(
                arrayOf(LOCATION_PERMISSION_COARSE, LOCATION_PERMISSION_FINE)
            )
        }
    }

    private fun isPermissionGranted(
        permission: String
    ): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isAtLeastOneLocationPermissionGranted(): Boolean {
        return isPermissionGranted(LOCATION_PERMISSION_COARSE) ||
                isPermissionGranted(LOCATION_PERMISSION_FINE)
    }

}