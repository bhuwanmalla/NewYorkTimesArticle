package bhuwan.example.newyorktimes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bhuwan.example.newyorktimes.R
import bhuwan.example.newyorktimes.WebActivity
import bhuwan.example.newyorktimes.models.Doc
import com.bumptech.glide.Glide

class ArticlesAdapter(
    private val context: Context,
    private var articles: MutableList<Doc>
) : RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val newView = LayoutInflater.from(context).inflate(R.layout.articles_design, parent, false)
        return ArticlesViewHolder(newView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    inner class ArticlesViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageView)
        private val leadParagraphTv: TextView = itemView.findViewById(R.id.leadParagraphTV)

        fun bind(article: Doc) {
            leadParagraphTv.text = article.abstract

            val multimedia = article.multimedia
            if (!multimedia.isNullOrEmpty()) {
                val imageUrl = multimedia[0].url
                if (!imageUrl.isNullOrEmpty()) {
                    val fullImage = "https://www.nytimes.com/$imageUrl"
                    Glide.with(itemView.context)
                        .load(fullImage)
                        .placeholder(R.drawable.newspaper_free_download)
                        .error(R.drawable.newspaper_free_download)
                        .into(image)
                } else {
                    image.setImageURI(null)
                }
            } else {
                image.setImageURI(null)
            }

            itemView.setOnClickListener {
                val webUrl = article.web_url
                if (!webUrl.isNullOrEmpty()) {
                    val intent = Intent(context, WebActivity::class.java)
                    intent.putExtra("web_url", webUrl)
                    context.startActivity(intent)
                }
            }
        }
    }
}
