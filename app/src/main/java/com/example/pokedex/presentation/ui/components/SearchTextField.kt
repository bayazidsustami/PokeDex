package com.example.pokedex.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.R
import com.example.pokedex.presentation.ui.theme.DarkGray
import com.example.pokedex.presentation.ui.theme.LightGray
import com.example.pokedex.presentation.ui.theme.PokeDexTheme
import com.example.pokedex.presentation.ui.theme.Poppins
import com.example.pokedex.presentation.ui.theme.Shapes

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    focusRequester: FocusRequester,
    isFocus: Boolean,
    onFocusChanged: (FocusState) -> Unit,
    onValueChange: (String) -> Unit,
    onCleared: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged { onFocusChanged(it) },
        shape = Shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            focusedBorderColor = DarkGray,
            unfocusedBorderColor = DarkGray,
            disabledBorderColor = DarkGray,
            cursorColor = LightGray
        ),
        maxLines = 1,
        textStyle = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp,
        ),
        leadingIcon = if (isFocus) {
            {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSizeIn(minHeight = 16.dp)
                )
            }
        } else null,
        trailingIcon = if (isFocus) {
            {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSizeIn(minHeight = 16.dp)
                        .clickable { onCleared() })
            }
        } else null,
        placeholder = if (!isFocus) {
            {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .requiredSizeIn(minHeight = 16.dp)
                    )
                    Spacer(Modifier.size(4.dp))
                    Text(
                        text = stringResource(R.string.et_search_hint),
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontFamily = Poppins,
                            lineHeight = 16.sp,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        } else {
            null
        }
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchFieldPreview() {
    PokeDexTheme {
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            isFocus = false,
            onFocusChanged = {},
            focusRequester = FocusRequester(),
            onCleared = {}
        )
    }
}