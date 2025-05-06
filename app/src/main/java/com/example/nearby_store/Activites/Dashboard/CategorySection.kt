package com.example.nearby_store.Activites.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nearby_store.Activites.Domain.CategoryModel
import com.example.nearby_store.R

@Composable
fun CategorySection(
    categories: SnapshotStateList<CategoryModel>,
    showCategoryLoading: Boolean,
) {
    Text(
        text = "Explore Categories",
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
    if (showCategoryLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(color = colorResource(R.color.white))
        ) {
            CircularProgressIndicator()
        }
    } else {
        val rows = categories.chunked(3)
        val content = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    row.forEachIndexed { index, categoryModel ->
                        CategoryItem(
                            category = categoryModel,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            onItemClick = {

                            }
                        )


                    }

                }

            }


        }
    }


}

@Composable
fun CategoryItem(
    category: CategoryModel,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onItemClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = category.ImagePath,
            contentDescription = "",
            modifier = Modifier.size(65.dp)
        )
        Text(
            text = category.Name,
            modifier = Modifier.padding(top = 10.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,


            )

    }

}
