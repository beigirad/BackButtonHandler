package ir.beigirad.backbuttonhandler.bases

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by farhad-mbp on 2/13/18.
 */
abstract class ParentBaseFragment : Fragment() {
    private val TAG = this.javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout, container, false)
    }

    abstract val getLayout: Int
    abstract val container: Int

    fun openFrag(fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(container, fragment).addToBackStack(fragment.toString()).commit()
    }

    fun back(): Boolean {
        Log.i(TAG, "back " + childFragmentManager.backStackEntryCount)
        if (childFragmentManager.backStackEntryCount > 1) {
            childFragmentManager.popBackStack()
            return false // parent should not handle back
        } else
            return true // parent should handle back
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childFragmentManager.addOnBackStackChangedListener(object : FragmentManager.OnBackStackChangedListener {
            override fun onBackStackChanged() {
                Log.i("Parent stack", "count: " + childFragmentManager.backStackEntryCount)
            }
        })
    }


}