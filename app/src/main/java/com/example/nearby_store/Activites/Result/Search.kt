package com.example.nearby_store.Activites.Result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearby_store.R

@Composable
@Preview(showBackground = true)
fun Search(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
        var text by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Find stories near you" ,
                fontSize = 16.sp,
                color = Color.Gray)
            },
            shape = RoundedCornerShape(20.dp),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search Icon"
                )
            }
        )
    }

}