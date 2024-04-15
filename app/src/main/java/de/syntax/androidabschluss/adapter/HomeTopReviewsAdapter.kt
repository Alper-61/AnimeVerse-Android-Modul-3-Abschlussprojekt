package de.syntax.androidabschluss.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.data.models.TopReviewsData
import de.syntax.androidabschluss.ui.HomeFragmentDirections

class HomeTopReviewsAdapter(private val dataset: List<TopReviewsData>) :
    RecyclerView.Adapter<HomeTopReviewsAdapter.d>() {
    inner class d(v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.findViewById(R.id.imageView2)
        val textTypeTitle: TextView = v.findViewById(R.id.textView2)
        val textReview: TextView = v.findViewById(R.id.textView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): d {
        val l = LayoutInflater.from(parent.context)
            .inflate(R.layout.design_home_topreviews, parent, false)
        return d(l)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: d, position: Int) {
        val item = dataset[position]
        holder.apply {
            item.entry?.images?.jpg?.let { image.glideImageSet(it.image_url) }
            textTypeTitle.text =
                itemView.context.getString(R.string.type) + item.type + "\n" + itemView.context.getString(
                    R.string.title
                ) + (item.entry?.title ?: itemView.context.getString(R.string.unknown))
            textReview.text = item.review

            itemView.setOnClickListener {view->
                val action = HomeFragmentDirections.actionHomeFragmentToTopReviewsDetailFragment(item)
                view.findNavController().navigate(action)
            }
        }
    }
}