package com.example.nearby_store.Activites.Repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nearby_store.Activites.Domain.BannerModel
import com.example.nearby_store.Activites.Domain.CategoryModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DashboardRepository(private val context: Context) {
    private val TAG = "DashboardRepository"
    private val firebaseDatabase: FirebaseDatabase by lazy {
        Firebase.database.apply {
            // Enable offline persistence
            setPersistenceEnabled(true)
            // Set database URL explicitly
            reference.keepSynced(true)
        }
    }

    init {
        Log.d(TAG, "DashboardRepository initialized")
        checkInternetConnection()
        initializeFirebase()
    }

    private fun checkInternetConnection() {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        
        val isConnected = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        Log.d(TAG, "Internet connection status: $isConnected")
        
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.d(TAG, "Connected to WiFi")
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.d(TAG, "Connected to Cellular")
            }
        }
    }

    private fun initializeFirebase() {
        try {
            // Test database connection
            firebaseDatabase.getReference(".info/connected").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val connected = snapshot.getValue(Boolean::class.java) ?: false
                    Log.d(TAG, "Firebase connection status: $connected")
                    if (!connected) {
                        Log.e(TAG, "Firebase connection failed")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(TAG, "Firebase connection error: ${error.message}")
                    Log.e(TAG, "Firebase error details: ${error.details}")
                    Log.e(TAG, "Firebase error code: ${error.code}")
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, "Firebase initialization error: ${e.message}")
            e.printStackTrace()
        }
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        Log.d(TAG, "Attempting to load categories")
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "Category data received: ${snapshot.exists()}")
                val list = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(CategoryModel::class.java)
                    item?.let {
                        list.add(it)
                        Log.d(TAG, "Category added: ${it.Name}")
                    }
                }
                listData.value = list
                Log.d(TAG, "Categories loaded: ${list.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Error loading categories: ${error.message}")
                Log.e(TAG, "Error details: ${error.details}")
                Log.e(TAG, "Error code: ${error.code}")
                listData.value = mutableListOf()
            }
        })
        return listData
    }

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        Log.d(TAG, "Attempting to load banners")
        val listData = MutableLiveData<MutableList<BannerModel>>()
        val ref = firebaseDatabase.getReference("Banners")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "Banner data received: ${snapshot.exists()}")
                Log.d(TAG, "Banner data: ${snapshot.value}")
                val list = mutableListOf<BannerModel>()
                for (childSnapshot in snapshot.children) {
                    Log.d(TAG, "Banner child data: ${childSnapshot.value}")
                    val item = childSnapshot.getValue(BannerModel::class.java)
                    item?.let {
                        list.add(it)
                        Log.d(TAG, "Banner added: ${it.Image}")
                    }
                }
                listData.value = list
                Log.d(TAG, "Banners loaded: ${list.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Error loading banners: ${error.message}")
                Log.e(TAG, "Error details: ${error.details}")
                Log.e(TAG, "Error code: ${error.code}")
                listData.value = mutableListOf()
            }
        })
        return listData
    }
}