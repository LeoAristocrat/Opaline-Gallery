package com.leoaristocrat.opalinegallery.data.repo

import com.leoaristocrat.opalinegallery.data.db.AppDatabase
import com.leoaristocrat.opalinegallery.data.db.DetectedObject

class ObjectIndexRepository(database: AppDatabase) {
    private val dao = database.objectIndexDao()

    suspend fun getObjects(imagePath: String): List<DetectedObject> = dao.getObjects(imagePath)

    suspend fun searchImagePaths(query: String): Set<String> = dao.searchByObject(query)
}

class TextIndexRepository(database: AppDatabase) {
    private val dao = database.textIndexDao()

    suspend fun getText(imagePath: String): String = dao.getText(imagePath)

    suspend fun searchImagePaths(query: String): Set<String> = dao.searchByText(query)
}
