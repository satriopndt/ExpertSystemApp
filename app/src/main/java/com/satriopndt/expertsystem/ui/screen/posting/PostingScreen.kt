package com.satriopndt.expertsystem.ui.screen.posting

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.satriopndt.expertsystem.R
import com.satriopndt.expertsystem.data.utils.getImageUri
import com.satriopndt.expertsystem.ui.component.HeaderPost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: Context = LocalContext.current
) {

    var category by remember {
        mutableStateOf("")
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var isExpandedStatus by remember {
        mutableStateOf(false)
    }
    var status by remember {
        mutableStateOf("")
    }

    var text by remember {
        mutableStateOf("")
    }

    val expandedFloatCategory by animateFloatAsState(targetValue = if (isExpanded) 1f else 0f)

    val expandedFloatStatus by animateFloatAsState(targetValue = if (isExpandedStatus) 1f else 0f)

    var currentImageUri: Uri? = null
    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val launcherCamera =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
            capturedImageUri = currentImageUri!!

        }

    val launcherGallery =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                currentImageUri = uri
                capturedImageUri = currentImageUri!!
            } else {
                Log.d("Photo Picker", "No Media Selected")
            }

        }

    fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }

    fun startCamera() {
        currentImageUri = context.getImageUri(context)
        launcherCamera.launch(currentImageUri!!)
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            if (it) {
                startCamera()
            }
        }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HeaderPost()
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Dropdown Category
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it }
            ) {
                OutlinedTextField(
                    value = category,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    label = { Text(text = "Category") },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = modifier
                        .menuAnchor()
                        .width(200.dp)
                        .graphicsLayer {
                            scaleX = 1f + (expandedFloatCategory * 0.1f)
                            scaleY = 1f + (expandedFloatCategory) * 0.1f
                        }

                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = {
                        isExpanded = false
                    },
                    modifier = modifier.graphicsLayer(alpha = expandedFloatCategory)
                ) {
                    DropdownMenuItem(
                        text = { Text("Research") },
                        onClick = {
                            category = "Research"
                            isExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Seminar") },
                        onClick = {
                            category = "Seminar"
                            isExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Narasumber") },
                        onClick = {
                            category = "Narasumber"
                            isExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Service") },
                        onClick = {
                            category = "Service"
                            isExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Achievement") },
                        onClick = {
                            category = "Achievement"
                            isExpanded = false
                        }
                    )

                }
            }

            Spacer(modifier = modifier.padding(8.dp))

            //Dropdown Status
            ExposedDropdownMenuBox(
                expanded = isExpandedStatus,
                onExpandedChange = { isExpandedStatus = it }
            ) {
                OutlinedTextField(
                    value = status,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedStatus)
                    },
                    label = { Text(text = "Status") },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = modifier
                        .menuAnchor()
                        .width(150.dp)

                )
                ExposedDropdownMenu(
                    expanded = isExpandedStatus,
                    onDismissRequest = {
                        isExpandedStatus = false
                    }
                ) {
                    DropdownMenuItem(
                        text = { Text("Present") },
                        onClick = {
                            status = "Present"
                            isExpandedStatus = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Done") },
                        onClick = {
                            status = "Done"
                            isExpandedStatus = false
                        }
                    )


                }
            }


        }
        Spacer(modifier = modifier.padding(8.dp))


        Column(modifier = modifier.fillMaxWidth()) {

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                        clip = true
                    )
                    .background(Color.White)
            ) {

                //Text Area
                TextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    modifier = modifier
                        .background(Color.White)
                        .height(150.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .shadow(
                            elevation = 20.dp,
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                            clip = true
                        ),
                    placeholder = {
                        Text(
                            text = "Type here..."
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White
                    ),
                    maxLines = 7

                )


            }


            //image place
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(Color.White)
            ) {
                Card(
                    modifier = modifier
                        .fillMaxWidth(1f)
                        .height(500.dp)
                        .background(Color.White)
                        .padding(16.dp),
                    colors = CardColors(
                        containerColor = Color.White,
                        contentColor = Color.White,
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color.White
                    )

                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(1f)
                            .padding(start = 20.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (capturedImageUri.path?.isNotEmpty() == true) {
                            AsyncImage(
                                modifier = modifier.fillMaxSize(),
                                model = capturedImageUri,
                                contentDescription = "Logo App",
                                contentScale = ContentScale.Fit
                            )
                        } else if (capturedImageUri.path?.isEmpty() == true) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_biru),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = modifier.size(200.dp)
                            )

                        }
                    }
                }
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                        clip = true
                    ),
                contentAlignment = Alignment.Center
            ) {


                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(55.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(modifier = modifier.size(24.dp),
                        onClick = {
                            val permissionCheckResult = ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            )
                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                startCamera()
                            } else {
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }

                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.heroicons_camera),
                            contentDescription = "camera",
                            tint = Color.Black,
                            modifier = modifier.size(24.dp)
                        )
                    }
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.gis_location_poi_o),
                        contentDescription = "contoh"
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.basil_add_outline),
                        contentDescription = "contoh"
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.basil_add_outline),
                        contentDescription = "contoh"
                    )
                    IconButton(
                        onClick = { startGallery() }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ion_image_outline),
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = modifier.size(24.dp)
                        )
                    }
                }

            }


        }


    }
}

@Preview(showBackground = true)
@Composable
fun PostingPreview() {
    PostingScreen(
        navController = rememberNavController()
    )
}
