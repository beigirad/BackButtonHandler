package ir.beigirad.backbuttonhandler

import android.os.Bundle
import android.view.View
import ir.beigirad.backbuttonhandler.bases.ChildBaseFragment
import kotlinx.android.synthetic.main.child2_layout.*

/**
 * Created by farhad-mbp on 2/13/18.
 */
class Child2 : ChildBaseFragment() {
    override val layout: Int
        get() = R.layout.child2_layout

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        child2_btn.setOnClickListener {
            openFrag(Child3())
        }
    }

}