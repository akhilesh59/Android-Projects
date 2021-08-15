package com.example.news_feed

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


class MainActivity : AppCompatActivity(), NewsItemClicked {

    lateinit var recyclerView : RecyclerView

    private lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recview)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter(this)
        recyclerView.adapter = mAdapter
    }

    private fun fetchData() {
        val url = "https://newsdata.io/api/1/news?apikey=pub_843219b653e22a27ff093d078610edebb33&country=in&category=business,entertainment&language=en&page=2"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsArray = ArrayList<News>()
                val newsJsonArray = it.getJSONArray("results")
                for(i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("source_id"),
                        newsJsonObject.getString("link"),
                        newsJsonObject.getString("image_url")
                    )
                    newsArray.add(news)
                }

                mAdapter.updateNews(newsArray)

            },
            Response.ErrorListener {

            }


        )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {

        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))

    }
}