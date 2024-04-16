package de.syntax.androidabschluss.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import de.syntax.androidabschluss.data.models.TopReviewsData
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.databinding.FragmentTopReviewsDetailBinding

class TopReviewsDetailFragment : Fragment() {

    private var binding: FragmentTopReviewsDetailBinding? = null
    private val args: TopReviewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopReviewsDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(args.list)
        binding?.backBtn?.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }


        }
    private fun setupView(reviewData: TopReviewsData) {
        binding?.titleTv?.text = reviewData.entry?.title
        binding?.ratingTv?.text = "${getString(R.string.score)} ${reviewData.score}${getString(R.string.ten)}"
        reviewData.entry?.images?.jpg?.image_url?.let { imageUrl ->
            binding?.animImage?.glideImageSet(imageUrl)
        }
        binding?.typeTv?.text = "${getString(R.string.type)} ${reviewData.type}"
        binding?.descriptionTv?.text = reviewData.review
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null // Verhindern von Memory Leaks
    }

}
