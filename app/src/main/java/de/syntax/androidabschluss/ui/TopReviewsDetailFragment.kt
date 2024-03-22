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

    private var _binding: FragmentTopReviewsDetailBinding? = null
    private val binding get() = _binding!!
    private val args: TopReviewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopReviewsDetailBinding.inflate(inflater, container, false)
        setupView(args.list)
        return binding.root
    }

    private fun setupView(reviewData: TopReviewsData) {
        binding.backBtn.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.titleTv.text = reviewData.entry?.title
        binding.ratingTv.text = "${getString(R.string.score)} ${reviewData.score}${getString(R.string.ten)}"
        reviewData.entry?.images?.jpg?.image_url?.let { imageUrl ->
            binding.animImage.glideImageSet(imageUrl)
        }
        binding.typeTv.text = "${getString(R.string.type)} ${reviewData.type}"
        binding.descriptionTv.text = reviewData.review
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
