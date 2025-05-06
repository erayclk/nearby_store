package com.example.nearby_store.Activites.Dashboard

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.nearby_store.Activites.Domain.BannerModel

private const val TAG = "BannerComposable"

@Composable
fun Banner(
    banners: SnapshotStateList<BannerModel>,
    showBannerLoading: Boolean
) {
    Log.d(TAG, "Banner composable called with ${banners.size} banners")
    if (showBannerLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Sliding(banners = banners)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Sliding(
    banners: List<BannerModel>,
    pagerState: PagerState = rememberPagerState(pageCount = { banners.size })
) {
    Log.d(TAG, "Sliding composable called with ${banners.size} banners")
    if (banners.isNotEmpty()) {
        HorizontalPager(
            state = pagerState
        ) { page ->
            val banner = banners[page]
            Log.d(TAG, "Loading banner image: ${banner.Image}")
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banner.Image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Banner image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(150.dp)
            )
        }
    } else {
        Log.d(TAG, "No banners to display")
    }
}