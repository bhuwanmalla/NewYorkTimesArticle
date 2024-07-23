package bhuwan.example.newyorktimes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bhuwan.example.newyorktimes.adapter.ArticlesAdapter
import bhuwan.example.newyorktimes.databinding.FragmentArticlesBinding.inflate
import bhuwan.example.newyorktimes.databinding.FragmentArticlesBinding
import bhuwan.example.newyorktimes.models.Doc
import bhuwan.example.newyorktimes.viewmodel.ArticlesViewModel

class ArticlesFragment : Fragment() {

    private lateinit var binding: FragmentArticlesBinding
    private val articles = mutableListOf<Doc>()
    private lateinit var articlesAdapter: ArticlesAdapter
    private lateinit var viewModel: ArticlesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerview()

        viewModel = ViewModelProvider(requireActivity())[ArticlesViewModel::class.java]

        observeData()

    }

    private fun setupRecyclerview(){
        articlesAdapter = ArticlesAdapter(requireContext(), articles)
        binding.recyclerView.apply {
            adapter = articlesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeData(){
        viewModel.articles.observe(viewLifecycleOwner, Observer { articlesData ->
            articlesData?.response?.docs?.let { docs ->
                articles.clear()
                articles.addAll(docs)
                articlesAdapter.notifyDataSetChanged()
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressCircular.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    }




