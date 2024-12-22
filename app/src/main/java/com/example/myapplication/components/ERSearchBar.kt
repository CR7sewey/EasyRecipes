package com.example.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ERSearchBar(placeholder: String, query: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit, onSearchClicked: () -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    OutlinedTextField(
            modifier = modifier.fillMaxWidth().padding(16.dp).focusRequester(focusRequester),
            value = query,
            onValueChange = onValueChange,
            /*colors = CardDefaults.cardColors(
                backgroundColor = Color(0XFF101921),
                placeholderColor = Color(0XFF888D91),
                leadingIconColor = Color(0XFF888D91),
                trailingIconColor = Color(0XFF888D91),
                textColor = Color.White,
                focusedIndicatorColor = Color.Transparent, cursorColor = Color(0XFF070E14)
            ),*/
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    focusRequester.freeFocus()
                    focusManager.clearFocus()
                    onSearchClicked.invoke()
                }
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
            placeholder = { Text(text = placeholder, fontSize = 16.sp) },
            shape = RoundedCornerShape(8.dp)
        )
    }

    
