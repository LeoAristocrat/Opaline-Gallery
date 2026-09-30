package com.leoaristocrat.opalinegallery

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.decode.VideoFrameDecoder
import coil.memory.MemoryCache
import com.leoaristocrat.opalinegallery.data.db.AppDatabase
import com.leoaristocrat.opalinegallery.data.repo.AlbumRepository
import com.leoaristocrat.opalinegallery.data.repo.FaceIndexRepository
import com.leoaristocrat.opalinegallery.data.repo.FavoritesRepository
import com.leoaristocrat.opalinegallery.data.repo.ImageLabelRepository
import com.leoaristocrat.opalinegallery.data.repo.MediaStoreRepository
import com.leoaristocrat.opalinegallery.data.repo.ObjectIndexRepository
import com.leoaristocrat.opalinegallery.data.repo.PeopleRepository
import com.leoaristocrat.opalinegallery.data.repo.PhotoEditorRepository
import com.leoaristocrat.opalinegallery.data.repo.SecureFolderRepository
import com.leoaristocrat.opalinegallery.data.repo.TextIndexRepository
import com.leoaristocrat.opalinegallery.data.repo.ThumbnailRepository
import com.leoaristocrat.opalinegallery.data.repo.TrashRepository
import com.leoaristocrat.opalinegallery.data.repo.VideoEditorRepository
import com.leoaristocrat.opalinegallery.data.settings.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

/**
 * Application-scoped dependency container (manual DI — no Hilt for this size). Exposed
 * through [container] and consumed by ViewModel factories via CreationExtras.
 */
class GalleryApp : Application() {

    lateinit var container: AppContainer
        private set

    val itemBounds = androidx.compose.runtime.mutableStateMapOf<android.net.Uri, androidx.compose.ui.geometry.Rect>()

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}

class AppContainer(app: GalleryApp) {
    val context: Context = app.applicationContext
    private val database = AppDatabase.get(app)
    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val imageDecodeDispatcher = Executors.newFixedThreadPool(2) { runnable ->
        Thread(runnable, "coil-low-priority-decode").apply {
            priority = Thread.MIN_PRIORITY
        }
    }.asCoroutineDispatcher()

    /** Single Coil loader that also renders video first-frames as thumbnails. */
    val imageLoader: ImageLoader = ImageLoader.Builder(context)
        .components { add(VideoFrameDecoder.Factory()) }
        .fetcherDispatcher(imageDecodeDispatcher)
        .decoderDispatcher(imageDecodeDispatcher)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.35)
                .build()
        }
        .crossfade(true)
        .build()
        .also { coil.Coil.setImageLoader(it) }

    val settingsRepository = SettingsRepository(context)
    val mediaRepository = MediaStoreRepository(context, database)
    val albumRepository = AlbumRepository(context, mediaRepository)
    val imageLabelRepository = ImageLabelRepository(context, database)
    val faceIndexRepository = FaceIndexRepository(database, appScope)
    val objectIndexRepository = ObjectIndexRepository(database)
    val textIndexRepository = TextIndexRepository(database)
    val peopleRepository = PeopleRepository(database, context)
    val favoritesRepository = FavoritesRepository(database, mediaRepository)
    val trashRepository = TrashRepository(context, database, mediaRepository)
    val secureFolderRepository = SecureFolderRepository(context, database, mediaRepository)
    val photoEditorRepository = PhotoEditorRepository(context)
    val videoEditorRepository = VideoEditorRepository(context)
    val thumbnailRepository = ThumbnailRepository(context)
}
