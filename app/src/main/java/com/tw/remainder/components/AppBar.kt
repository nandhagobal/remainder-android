package com.tw.remainder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tw.remainder.R
import com.tw.remainder.theme.RemainderTheme

@Composable
fun AppBar(
    enableBack: Boolean = false,
    enableSearch: Boolean = false,
    onBack: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onDone : () -> Unit = {},
    title: String,
    doneIcon: Boolean = false
) {
    RemainderTheme {
        TopAppBar() {
            if (enableBack) BackNavigationButton(onBack)
            if (enableSearch) SearchField()
            else
                Text(
                    text = title,
                    fontSize = 25.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            AppBarIcon(onSearchClick, doneIcon, onDone)

        }
    }
}

@Composable
private fun BackNavigationButton(onBack: () -> Unit) {
    IconButton(
        onClick = onBack, modifier = Modifier
            .height(30.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = "back"
        )
    }
}

@Composable
private fun AppBarIcon(onSearchClick: () -> Unit, doneIcon: Boolean, onDone: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.End, modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        if (!doneIcon) {
            IconButton(onClick = onSearchClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = "sorting"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "menu"
                )
            }
        }
        else {
            IconButton(onClick = onDone) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_done),
                    contentDescription = "done"
                )
            }
        }
    }
}

@Composable
private fun SearchField() {
    val searchValue = rememberSaveable { mutableStateOf("") }
    TextField(
        value = searchValue.value,
        onValueChange = { value -> searchValue.value = value },
        modifier = Modifier
            .width(200.dp)
            .background(Color.Transparent),
        placeholder = {
            Text(
                text = "Search..."
            )
        }, colors = TextFieldDefaults.textFieldColors(Color.White)
    )
}
