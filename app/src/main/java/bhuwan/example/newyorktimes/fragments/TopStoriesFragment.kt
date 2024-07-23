package bhuwan.example.newyorktimes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bhuwan.Result
import bhuwan.example.newyorktimes.adapter.TopStoriesAdapter
import bhuwan.example.newyorktimes.databinding.FragmentTopStoriesBinding.inflate
import bhuwan.example.newyorktimes.databinding.FragmentTopStoriesBinding
import bhuwan.example.newyorktimes.viewmodel.TopStoriesViewModel

class TopStoriesFragment : Fragment() {

    private lateinit var binding: FragmentTopStoriesBinding
    private lateinit var viewModel: TopStoriesViewModel
    private val stories = mutableListOf<Result>()
    private lateinit var storiesAdapter: TopStoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TopStoriesViewModel::class.java]

        setUpRecyclerView()

        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel.topStories.observe(viewLifecycleOwner, Observer { story ->
            story.results?.let { result ->
                stories.clear()
                stories.addAll(result)
                storiesAdapter.notifyDataSetChanged()
            }

        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { loading ->
            binding.progressCircular.visibility = if (loading) View.VISIBLE else View.GONE
            binding.loading.visibility = if (loading) View.VISIBLE else View.GONE

        })


    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            storiesAdapter = TopStoriesAdapter(requireContext(), stories)
            adapter = storiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}
