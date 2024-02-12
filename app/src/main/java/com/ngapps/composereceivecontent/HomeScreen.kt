package com.ngapps.composereceivecontent

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType
import androidx.compose.foundation.content.receiveContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("home:screen"),
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var text by remember { mutableStateOf("") }
                var imageUri by remember { mutableStateOf(Uri.EMPTY) }

                Text(
                    text = "Variant 1",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier.wrapContentHeight(),
                    contentScale = ContentScale.FillHeight
                )
                BasicTextField2(
                    value = text,
                    onValueChange = { text = it },
                    textStyle = TextStyle(fontSize = 14.sp),
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.LightGray)
                        .padding(16.dp)
                        .receiveContent(setOf(MediaType.Image)) { content ->
                            val clipData = content.clipEntry.clipData
                            for (index in 0 until clipData.itemCount) {
                                val data = clipData.getItemAt(index) ?: continue
                                imageUri = data.uri ?: continue
                            }
                            content
                        }
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var text by remember { mutableStateOf("") }
                var imageUri by remember { mutableStateOf(Uri.EMPTY) }

                Text(
                    text = "Variant 2",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                BasicTextField2(
                    value = text,
                    onValueChange = { text = it },
                    textStyle = TextStyle(fontSize = 14.sp),
                    decorator = { innerTextField ->
                        TextFieldDefaults.DecorationBox(
                            value = text,
                            innerTextField = { innerTextField() },
                            enabled = true,
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            interactionSource = remember { MutableInteractionSource() },
                            trailingIcon = {
                                AsyncImage(
                                    model = imageUri,
                                    contentDescription = null,
                                    modifier = Modifier.wrapContentHeight(),
                                    contentScale = ContentScale.FillHeight
                                )
                            },
                            contentPadding = PaddingValues(0.dp),
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.LightGray)
                        .receiveContent(setOf(MediaType.Image)) { content ->
                            val clipData = content.clipEntry.clipData
                            for (index in 0 until clipData.itemCount) {
                                val data = clipData.getItemAt(index) ?: continue
                                imageUri = data.uri ?: continue
                            }
                            content
                        }
                )
            }
        }
    }
}