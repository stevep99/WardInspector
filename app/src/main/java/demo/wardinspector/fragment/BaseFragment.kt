package demo.wardinspector.fragment

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {
    protected val subscribers = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        subscribers.dispose()
    }
}