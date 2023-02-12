package com.example.flixsterproject3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject
import java.io.StringReader

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class BestMovieFragment : Fragment(), ListClickListner{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_fragment_list, container, false)
        val recyclerView = view.findViewById<View>(R.id.MovieList) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(recyclerView)
        return view
    }
    private fun updateAdapter(recyclerView: RecyclerView) {
        // Create and set up an AsyncHTTPClient() here
        val myClient = AsyncHttpClient()
        val myParams = RequestParams()
        myParams["api_key"] = API_KEY

        // Using the client, perform the HTTP request
        myClient["https://api.themoviedb.org/3/movie/now_playing",myParams,
                object: JsonHttpResponseHandler() {

// TESTING CONNECTING!!
//                    override fun onFailure(
//                        statusCode: Int,
//                        headers: Headers?,
//                        response: String?,
//                        throwable: Throwable?
//                    ) {
//                        throwable?.message?.let {
//                            Log.e("BestMovieFragment", response.toString())
//                        }
//                    }
//
//                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
//
//                        Log.d("DEBUG ARRAY", "__" + json.jsonArray.toString())
//                        // Access a JSON object response with `json.jsonObject`
//                        Log.d("DEBUG OBJECT", json.jsonObject.toString())
//
//
//                    }
//                }]

// ACTUAL IMPLEMENTATION BELOW!
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        // The wait for a response is over

                        val gson = Gson()
                        val arrayBookType = object : TypeToken<List<BestMovie>>() {}.type
                        val resultsJSON : JSONArray = json.jsonObject.getJSONArray("results")
                        val booksRawJSON : String = resultsJSON.toString()
                        val models : List<BestMovie> = gson.fromJson(booksRawJSON, arrayBookType)
                        recyclerView.adapter = MovieAdapter(models, this@BestMovieFragment)

                        // Look for this in Logcat:
                        Log.d("BestSellerBooksFragment", "response successful")
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("BestMovieFragment", errorResponse)
                        }
                    }
                }]


    }

    override fun onItemClick(item: BestMovie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }


}

