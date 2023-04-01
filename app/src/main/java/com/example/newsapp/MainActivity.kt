package com.example.newsapp


import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Request

class MainActivity : AppCompatActivity(), NewsItemClicked {

     private lateinit var madapter:NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView= findViewById<RecyclerView>(R.id.recycler)

        recyclerView.layoutManager=LinearLayoutManager(this)

      fetchData()
        val adapter:NewsListAdapter=NewsListAdapter(this)
        recyclerView.adapter=madapter

    }

    private fun fetchData() {
        val url = "https://newsapi.org/v2/everything?q=keyword&apiKey=66c581346aaf4a6a9892467efb60dbb9"

        val jsonObjectRequest = JsonObjectRequest(
            com.android.volley.Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                val newsJSONArray= response.getJSONArray("articles")
                val newsArray=ArrayList<News>()
                for(i in 0 until newsJSONArray.length()){
                    val newsJsonObject= newsJSONArray.getJSONObject(i)
                    val news=News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                )
                newsArray.add(news)
                }
             madapter.updatedNews(newsArray)
            },
            Response.ErrorListener { error ->
                // Handle any errors that occur
            }
        )

        // Add the request to the Volley request queue to execute it
        // Volley is a library for making network requests in Android
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    override fun OnItemClicked(item: News) {

    }

}

