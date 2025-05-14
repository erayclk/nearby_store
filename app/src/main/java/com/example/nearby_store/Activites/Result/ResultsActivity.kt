package com.example.nearby_store.Activites.Result

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nearby_store.Activites.Domain.CategoryModel
import com.example.nearby_store.Activites.Domain.StoreModel
import com.example.nearby_store.R
import com.example.nearby_store.ViewModel.ResultsViewModel

class ResultsActivity : AppCompatActivity() {
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        id = intent.getStringExtra("id") ?: ""
        title = intent.getStringExtra("title") ?: ""

        setContent {
            ResultList(id, title, onBackClick = { finish() })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ResultList(id: String = "1", title: String = "result", onBackClick: () -> Unit = {}) {

    val viewModel = ResultsViewModel()

    val subCategoryList = remember { mutableStateListOf<CategoryModel>() }
    val popular = remember { mutableStateListOf<StoreModel>() }


    var showsubCategoryLoading by remember { mutableStateOf(true) }
    var showsPopularLoading by remember { mutableStateOf(true) }

    val nearest = remember { mutableStateListOf<StoreModel>() }
    var showNearestLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadSubCategory(id).observeForever {
            subCategoryList.clear()
            subCategoryList.addAll(it)
            showsubCategoryLoading = false
        }

    }
    LaunchedEffect(Unit) {
        viewModel.loadPopular(id).observeForever {
            popular.clear()
            popular.addAll(it)
            showsPopularLoading = false
        }

    }
    LaunchedEffect(Unit) {
        viewModel.loadNearest(id).observeForever {
            nearest.clear()
            nearest.addAll(it)
            showNearestLoading = false
        }

    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.light_blue)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopTitle(title, onBackClick = onBackClick)
        }
        item {
            Search()
        }
        item {
            SubCategorySection(subCategoryList, showsubCategoryLoading)
        }
        item {
            PopularSection(popular, showsPopularLoading)
        }
        item {
            NearestSection(nearest, showNearestLoading)
        }
    }

}


