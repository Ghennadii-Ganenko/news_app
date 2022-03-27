package main.app.newspaper.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import main.app.newspaper.R
import main.app.newspaper.databinding.ItemArticleBinding
import main.app.newspaper.domain.models.ArticleEntity

class ArticlesAdapter(
    private val clickListener: ItemClickListener,
    private val context: Context,
    private val view: View
) : ListAdapter<ArticleEntity, RecyclerView.ViewHolder>(ItemsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val packHolder = holder as ItemViewHolder
        packHolder.bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ArticleEntity) {
            binding.apply {
                root.setOnClickListener {
                    it.isClickable = false
                    it.postDelayed({ it.isClickable = true }, 1000)
                    clickListener.onItemClicked(item, view)
                }
                if (item.urlToImage == null) {
                    itemImage.setImageResource(R.drawable.placeholder_news)
                } else {
                    Picasso.get().load(item.urlToImage).into(itemImage)
                }
                itemTitle.text = item.title

                if (item.description == null) {
                    itemDescription.text = context.getString(R.string.placeholder_description)
                } else {
                    itemDescription.text = item.description
                }
            }
        }
    }

    interface ItemClickListener {
        fun onItemClicked(item: ArticleEntity, view: View)
    }
}


class ItemsDiffCallback : DiffUtil.ItemCallback<ArticleEntity>() {
    override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean =
        oldItem == newItem
}