package bhuwan.example.newyorktimes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bhuwan.Result
import bhuwan.example.newyorktimes.R
import bhuwan.example.newyorktimes.WebActivity
import com.bumptech.glide.Glide

class TopStoriesAdapter(val context: Context, private val stories: MutableList<Result>) :
    RecyclerView.Adapter<TopStoriesAdapter.TopStoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesViewHolder {
        val newView = LayoutInflater.from(context).inflate(R.layout.articles_design, parent, false)
        return TopStoriesViewHolder(newView)
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: TopStoriesViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    inner class TopStoriesViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageView)
        private val leadParagraphTv: TextView = itemView.findViewById(R.id.leadParagraphTV)

        fun bind(mainStory: Result) {
            leadParagraphTv.text = mainStory.abstract

            val multimedia = mainStory.multimedia
            if (!multimedia.isNullOrEmpty()) {
                val imageUrl = multimedia[0].url
                if (!imageUrl.isNullOrEmpty()) {
                    Glide.with(itemView.context)
                        .load(imageUrl)
                        .error(R.drawable.newspaper_free_download)
                        .into(image)
                }
            }

            itemView.setOnClickListener {
                val webUrl = mainStory.url
                if (!webUrl.isNullOrEmpty()) {
                    val intent = Intent(context, WebActivity::class.java)
                    intent.putExtra("web_url", webUrl)
                    context.startActivity(intent)
                }
            }
        }
    }
}
