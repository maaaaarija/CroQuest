package hr.ferit.marija.project1.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import hr.ferit.marija.project1.data.Lokacija

class LocationsViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    val allLocations = mutableStateListOf<Lokacija>()

    init {
        fetchLocations()
    }

    private fun fetchLocations() {
        db.collection("locations")
            .get()
            .addOnSuccessListener { result ->
                allLocations.clear()
                for (document in result) {
                    val location = document.toObject(Lokacija::class.java)
                    if (location != null) {
                        location.id = document.id
                        allLocations.add(location)
                    }
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    private val _myList = mutableStateListOf<Lokacija>()
    val myList: List<Lokacija> get() = _myList

    fun addToMyList(location: Lokacija) {
        if (_myList.none { it.id == location.id }) {
            _myList.add(location)
        }
    }

    fun removeFromMyList(location: Lokacija) {
        _myList.removeAll { it.id == location.id }
    }
}