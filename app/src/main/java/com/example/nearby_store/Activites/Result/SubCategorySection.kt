package com.example.nearby_store.Activites.Result

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nearby_store.Activites.Domain.CategoryModel


@Composable
fun SubCategorySection(
    subCategoryList: SnapshotStateList<CategoryModel>,
    showsubCategoryLoading: Boolean
) {
    if (showsubCategoryLoading) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyRow {
            items(subCategoryList.size) { index ->

                Category(item = subCategoryList[index],
                    onItemClick = {

                    }

                )

            }
        }
    }
}


@Composable

fun Category(item: CategoryModel, onItemClick: (CategoryModel) -> Unit) {
    Column(
        modifier = Modifier
            .size(85.dp, 95.dp)
            .background(
                color = Color.White, shape = RoundedCornerShape(10.dp)

            )
            .clickable { onItemClick(item) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = item.ImagePath,
            contentDescription = null,
            modifier = Modifier
                .size(45.dp, 40.dp)
        )
        Text(
            text = item.Name,
            color = Color.Black
        )
    }
}