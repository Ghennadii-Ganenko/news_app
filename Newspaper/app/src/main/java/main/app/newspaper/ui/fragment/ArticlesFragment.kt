package main.app.newspaper.ui.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import main.app.newspaper.R
import main.app.newspaper.databinding.FragmentArticlesBinding
import main.app.newspaper.domain.models.ArticleEntity
import main.app.newspaper.ui.adapter.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class ArticlesFragment : Fragment(R.layout.fragment_articles), ArticlesAdapter.ItemClickListener {

    private val vm by viewModel<ArticlesViewModel>()
    private var binding: FragmentArticlesBinding? = null
    private lateinit var adapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentArticlesBinding.inflate(inflater, container, false)
        this.binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ArticlesAdapter(this, requireContext(), view)

        Timber.d("Fragment created")

        binding?.recyclerView?.setHasFixedSize(true)

        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = adapter

        vm.articlesList.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
            binding?.recyclerView?.smoothSnapToPosition(vm.position)
        }

        vm.getAllArticlesList()
    }

    override fun onDestroy() {
        vm.position = getCurrentPosition()
        super.onDestroy()
        Timber.d("Fragment destroyed")
    }

    override fun onItemClicked(item: ArticleEntity, view: View) {
        val action = ArticlesFragmentDirections.navigateToDetailFragment(item.url)
        findNavController().navigate(action)

    }

    private fun getCurrentPosition(): Int {
        return (binding?.recyclerView?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }

    private fun RecyclerView.smoothSnapToPosition(
        position: Int,
        snapMode: Int = LinearSmoothScroller.SNAP_TO_START
    ) {
        val scrollDuration = 1000f
        val smoothScroller = object : LinearSmoothScroller(this.context) {
            override fun getVerticalSnapPreference(): Int = snapMode
            override fun getHorizontalSnapPreference(): Int = snapMode
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                return scrollDuration / computeVerticalScrollRange()
            }
        }
        smoothScroller.targetPosition = position
        layoutManager?.startSmoothScroll(smoothScroller)
    }
}