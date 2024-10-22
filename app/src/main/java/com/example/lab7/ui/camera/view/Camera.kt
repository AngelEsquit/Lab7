package com.example.lab7.ui.camera.view

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lab7.navigation.NavigationState
import com.example.lab7.navigation.navigateTo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(navController: NavController) {
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Cámara") })
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            when (cameraPermissionState.status) {
                is PermissionStatus.Granted -> {
                    Camera(navController = navController) // Mostrar la cámara si el permiso está concedido
                }
                is PermissionStatus.Denied -> {
                    // Mostrar un mensaje al usuario solicitando el permiso
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Se necesita permiso de cámara para usar esta función.")
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                            Text("Solicitar permiso")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Camera(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    var previewUseCase by remember { mutableStateOf<Preview?>(null) }
    var imageCaptureUseCase by remember { mutableStateOf<ImageCapture?>(null) }

    var photoPath by remember { mutableStateOf<String?>(null) }

    Box {
        AndroidView(
            factory = { ctx ->
                val previewView = PreviewView(ctx).apply {
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    scaleType = PreviewView.ScaleType.FILL_CENTER
                }
                val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()

                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    previewUseCase = preview

                    val imageCapture = ImageCapture.Builder().build()
                    imageCaptureUseCase = imageCapture

                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)
                    } catch (e: Exception) {
                        Log.e("CameraPreview", "Error binding camera", e)
                    }
                }, ContextCompat.getMainExecutor(ctx))
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )

        DisposableEffect(lifecycleOwner) {
            onDispose {
                previewUseCase?.let {
                    ProcessCameraProvider.getInstance(context).get().unbind(it)
                }
            }
        }
        
        Button(onClick = { photoPath = takePhoto(context, imageCaptureUseCase)
            navigateTo(navController, NavigationState.SupermarketCamera.createRoute(photoPath!!))
        },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)) {
            Text("Tomar foto")
        }
    }
}

private fun takePhoto(context: Context, imageCapture: ImageCapture?): String {
    var savedPhotoPath = ""

    val directory = context.getExternalFilesDir(null)
    if (directory != null && !directory.exists()) {
        directory.mkdirs()
    }

    val photoFile = File(
        context.getExternalFilesDir(null), "${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())}.jpg")

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture?.takePicture(outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraX", "Error al capturar la foto: ${exception.message}", exception)
            }

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                savedPhotoPath = photoFile.absolutePath
                Log.d("CameraX", "Foto guardada en: ${photoFile.absolutePath}")
            }
        }
    )

    return savedPhotoPath
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    CameraScreen(navController = navController)
}