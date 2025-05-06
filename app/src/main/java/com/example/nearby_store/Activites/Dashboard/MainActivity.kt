package com.example.nearby_store.Activites.Dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nearby_store.Activites.Domain.BannerModel
import com.example.nearby_store.Activites.Domain.CategoryModel
import com.example.nearby_store.R
import com.example.nearby_store.ViewModel.DashboardViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardScreen()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DashboardScreen() {
    val viewModel: DashboardViewModel = viewModel()

    val categories = remember { mutableStateListOf<CategoryModel>() }
    val banners = remember { mutableStateListOf<BannerModel>() }

    var showCategotyLoading by remember { mutableStateOf<Boolean>(true) }
    var showBannerLoading by remember { mutableStateOf<Boolean>(true) }

    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever { categoryList ->
            categories.clear()
            categories.addAll(categoryList)
            showCategotyLoading = false
        }
    }
    LaunchedEffect(Unit) {
        viewModel.loadBanners().observeForever { loadBanners ->
            banners.clear()
            banners.addAll(loadBanners)
            showBannerLoading = false
        }
    }

    Scaffold(
        bottomBar = { MyBottomBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = colorResource(R.color.light_blue))
        ) {
            item {
                TopBar()
            }
            item {
                CategorySection(categories, showCategotyLoading)
            }
            item {
                Banner(banners, showBannerLoading)
            }
        }
    }
}