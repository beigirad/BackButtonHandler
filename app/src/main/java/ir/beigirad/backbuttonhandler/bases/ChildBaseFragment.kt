package ir.beigirad.backbuttonhandler.bases

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by farhad-mbp on 2/13/18.
 */
abstract class ChildBaseFragment : Fragment() {
    abstract val getLayout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout, container, false)
    }

    fun openFrag(fragment: Fragment) {
        (parentFragment as ParentBaseFragment).openFrag(fragment)
    }

}