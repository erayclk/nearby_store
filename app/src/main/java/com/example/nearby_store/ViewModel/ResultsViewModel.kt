package com.example.nearby_store.ViewModel

import androidx.lifecycle.LiveData
import com.example.nearby_store.Activites.Domain.CategoryModel
import com.example.nearby_store.Activites.Domain.StoreModel
import com.example.nearby_store.Activites.Repository.ResultsRepository

class ResultsViewModel {
    private val repo = ResultsRepository()

    fun loadSubCategory(id: String) : LiveData<MutableList<CategoryModel>> {

        return repo.loadSubCategory(id)
    }
    fun loadPopulary(id: String) : LiveData<MutableList<StoreModel>> {

        return repo.loadPopular(id)
    }

}