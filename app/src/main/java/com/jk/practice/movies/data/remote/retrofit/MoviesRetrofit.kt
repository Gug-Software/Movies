package gug.co.com.networkmoviemarketlib.data.source.remote.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jk.practice.movies.data.remote.retrofit.MovieDetailAPI
import com.jk.practice.movies.data.remote.retrofit.MoviesAPI
import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(20, TimeUnit.SECONDS)
    .writeTimeout(20, TimeUnit.SECONDS)
    .readTimeout(20, TimeUnit.SECONDS)
    .build()

/**
 * Main entry point for network access. Call like `Network.devPosts.getPlaylist()`
 */
object MoviesRetrofit {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val moviesApi = retrofit.create(MoviesAPI::class.java)
    val movieDetailApi = retrofit.create(MovieDetailAPI::class.java)

}