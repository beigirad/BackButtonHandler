package ir.beigirad.backbuttonhandler

import android.os.Bundle
import android.view.View
import ir.beigirad.backbuttonhandler.bases.ChildBaseFragment
import kotlinx.android.synthetic.main.child1_layout.*

/**
 * Created by farhad-mbp on 2/13/18.
 */
class Child1 : ChildBaseFragment() {
    override val layout: Int
        get() = R.layout.child1_layout

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button2.setOnClickListener {
            openFrag(Child2())
        }
    }

}