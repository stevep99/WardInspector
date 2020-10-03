package demo.wardinspector.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import demo.wardinspector.R
import demo.wardinspector.model.InspectionWithAnswerInfo
import demo.wardinspector.view_model.InspectionsViewModel
import kotlinx.android.synthetic.main.fragment_inspections.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class InspectionsFragment : BaseFragment() {

    lateinit var viewModel: InspectionsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inspections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgress.visibility = View.VISIBLE

        viewModel = ViewModelProvider(requireActivity()).get(InspectionsViewModel::class.java)
        subscribers.add(
            viewModel.requestInspections()
                .doFinally{
                    loadingProgress.visibility = View.GONE
                }
                .subscribe{ populateInspections(it) }
        )
    }

    fun populateInspections(inspections: List<InspectionWithAnswerInfo>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.inspectionsRecyclerView)
        val inspectionListAdapter = InspectionListAdapter(requireActivity(), inspections)

        recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = inspectionListAdapter
        }

        subscribers.add(inspectionListAdapter.getClickedItemObserver()
            .subscribe{ goToQuestions(it) }
        )
    }

    fun goToQuestions(id: Long) {
        findNavController().navigate(R.id.action_InspectionsFragment_to_QuestionsFragmentFragment)
    }
}