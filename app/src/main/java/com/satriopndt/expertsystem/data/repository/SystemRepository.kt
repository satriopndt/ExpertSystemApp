package com.satriopndt.expertsystem.data.repository

import androidx.lifecycle.liveData
import com.satriopndt.expertsystem.data.model.FakeDataResearchPost
import com.satriopndt.expertsystem.data.model.ResearchModel
import com.satriopndt.expertsystem.data.model.UploadModel
import com.satriopndt.expertsystem.data.model.UserModel
import com.satriopndt.expertsystem.data.preference.UserPreferences
import com.satriopndt.expertsystem.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class SystemRepository(
    private val userPreferences: UserPreferences
) {
    private val dataResearch = mutableListOf<UploadModel>()

    companion object {
        @Volatile
        private var instance: SystemRepository? = null

        fun getInstance(
            preferences: UserPreferences
        ): SystemRepository =
            instance ?: synchronized(this) {
                instance ?: SystemRepository(preferences)
            }.also { instance = it }
    }

    fun getAllPost(): Flow<List<UploadModel>> {
        return flowOf(dataResearch)
    }

    fun getPosting(): List<ResearchModel> {
        return FakeDataResearchPost.dummyResearch
    }

    suspend fun saveSession(userModel: UserModel) {
        userPreferences.saveSession(userModel)
    }

    fun uploadPhoto(photoFile: File, description: String) = liveData {
        emit(UiState.Loading)
        val requestBody = description.toRequestBody("text/plain".toMediaType())
        val requestImageFile = photoFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody =
            MultipartBody.Part.createFormData("photo", photoFile.name, requestImageFile)
//        try {
//            val user = runBlocking { preferences.getSession().first()}
//
//        }
    }

    fun searchRiset(query: String): List<ResearchModel> {
        return FakeDataResearchPost.dummyResearch.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }


}

//    init {
//        if (dataResearch.isEmpty()) {
//            FakeDataResearchPost.dummyResearch.forEach {
//                dataResearch.add(UploadModel(it, "", ""))
//            }
//        }
//    }




