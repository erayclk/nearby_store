package com.example.nearby_store.Activites.Dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearby_store.R

@Composable
@Preview(showBackground = true)
fun MyBottomBar() {
    val bottomMenuItemList = prepareBottomMenu()
    val content = LocalContext.current
    var selected by remember { mutableStateOf("Home") }

    BottomAppBar(
        containerColor = colorResource(R.color.white),
        tonalElevation = 3.dp
    ) {
        bottomMenuItemList.forEach { bottomMenuItemList ->
            BottomNavigationItem(
                selected = (selected == bottomMenuItemList.label),
                onClick = {
                    selected = bottomMenuItemList.label
                    Toast.makeText(content, bottomMenuItemList.label, Toast.LENGTH_SHORT).show()
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = bottomMenuItemList.icon,
                            contentDescription = "",
                            tint = colorResource(R.color.dark_brown),
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(20.dp)

                        )
                        Text(
                            text = bottomMenuItemList.label,
                            fontSize = 12.sp,
                            color = colorResource(R.color.dark_brown),
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }
                }
            )

        }


    }
}


data class BottomMenuItem(
    val label: String,
    val icon: Painter,

    )

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {

    val bottomMenuItemList = arrayListOf<BottomMenuItem>()

    bottomMenuItemList.add(BottomMenuItem("Home", icon = painterResource(R.drawable.btn_1)))
    bottomMenuItemList.add(BottomMenuItem("Support", icon = painterResource(R.drawable.btn_2)))
    bottomMenuItemList.add(BottomMenuItem("Wishlist", icon = painterResource(R.drawable.btn_3)))
    bottomMenuItemList.add(BottomMenuItem("Profile", icon = painterResource(R.drawable.btn_4)))

    return bottomMenuItemList
}
