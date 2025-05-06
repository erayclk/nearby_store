package com.example.nearby_store.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.nearby_store.Activites.Domain.BannerModel
import com.example.nearby_store.Activites.Domain.CategoryModel
import com.example.nearby_store.Activites.Repository.DashboardRepository

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DashboardRepository(application)
    
    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }
    
    fun loadBanners(): LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }
}