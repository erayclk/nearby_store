package com.example.nearby_store.Activites.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nearby_store.Activites.Domain.CategoryModel
import com.example.nearby_store.Activites.Domain.StoreModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ResultsRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadSubCategory(id: String): LiveData<MutableList<CategoryModel>> {

        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("SubCategory")
        val query:Query= ref.orderByChild("CategoryId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<CategoryModel>()
                for (data in snapshot.children) {
                    val model = data.getValue(CategoryModel::class.java)
                    model?.let { list.add(it) }
                }
                listData.value = list

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        return listData

    }
    fun loadPopular(id: String): LiveData<MutableList<StoreModel>> {

        val listData = MutableLiveData<MutableList<StoreModel>>()
        val ref = firebaseDatabase.getReference("Stores")
        val query:Query= ref.orderByChild("CategoryId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<StoreModel>()
                for (data in snapshot.children) {
                    val model = data.getValue(StoreModel::class.java)
                    model?.let { list.add(it) }
                }
                listData.value = list

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        return listData

    }
fun loadNearest(id: String): LiveData<MutableList<StoreModel>> {

        val listData = MutableLiveData<MutableList<StoreModel>>()
        val ref = firebaseDatabase.getReference("Nearest")
        val query:Query= ref.orderByChild("CategoryId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<StoreModel>()
                for (data in snapshot.children) {
                    val model = data.getValue(StoreModel::class.java)
                    model?.let { list.add(it) }
                }
                listData.value = list

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        return listData

    }

}