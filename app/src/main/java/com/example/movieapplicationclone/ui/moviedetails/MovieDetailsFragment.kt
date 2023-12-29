package com.example.movieapp.ui.moviedetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.databinding.MovieListDetailsBinding
import com.example.movieapplicationclone.base.BaseFragment
import com.example.movieapplicationclone.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_item_design.*
import kotlinx.android.synthetic.main.movie_item_design.view.*
import kotlinx.android.synthetic.main.movie_list_details.*
import kotlin.math.ln
import kotlin.math.pow

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<MovieListDetailsBinding>(MovieListDetailsBinding::inflate) {

    private val viewModel : MovieDetailsViewModel by viewModels()
    private val args : MovieDetailsFragmentArgs by navArgs()
    @SuppressLint("SetTextI18n")
    override fun setupUI(savedInstanceState: Bundle?) {
        viewModel.getMovieDetailsUseCaseState(args.movieId.toString())
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.listDetailsLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                with(binding) {
                    tvMovieTitle.text = it.originalTitle.toString()
                    tvMovieTagLine.text = it.tagline.toString()
                    tvMovieDateReleaseAnswer.text = it.releaseDate.toString()
                    tvMovieRuntime.text = it.runtime.toString() + " minutes"
                    tvMovieBudget.text = "$" + it.budget?.let { it1 -> getFormatedNumber(it1.toLong()).toString() }
                    tvMovieRevenue.text = "$" + it.revenue?.let { it1 -> getFormatedNumber(it1.toLong()).toString() }
                    tvMovieOverview.text = it.overview.toString()
                    tvMovieRating.text = it.voteAverage?.format(1).toString()
                }
                Glide.with(this)
                    .apply { RequestOptions().override(200, 300).fitCenter() }
                    .load(Constant.POSTER_BASE_URL + it.backdropPath)
                    .into(imgMovie)
            }
        }
    }

    override fun onClick(p0: View?) {
    }
    fun Double.format(digits: Int) = "%.${digits}f".format(this)

    fun getFormatedNumber(count: Long): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }
}