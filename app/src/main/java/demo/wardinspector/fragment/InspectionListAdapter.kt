package demo.wardinspector.fragment

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import demo.wardinspector.databinding.ItemInspectionBinding
import demo.wardinspector.model.InspectionWithAnswerInfo
import io.reactivex.subjects.PublishSubject

class InspectionListAdapter(private val context: Context,
                            private val data: List<InspectionWithAnswerInfo>)
    : RecyclerView.Adapter<InspectionListAdapter.ViewHolder>() {

    private val clickedItemSubject = PublishSubject.create<Long>()

    private val inflater = LayoutInflater.from(context)
    private lateinit var binding: ItemInspectionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspectionListAdapter.ViewHolder {
        binding = ItemInspectionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: InspectionListAdapter.ViewHolder, position: Int) {
        holder.itemView.tag = data[position].inspection.inspectionId
        binding.inspectionWithAnswers = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getClickedItemObserver(): PublishSubject<Long> {
        return clickedItemSubject
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener{clickedItemSubject.onNext(it.tag as Long)}
        }
    }

}
