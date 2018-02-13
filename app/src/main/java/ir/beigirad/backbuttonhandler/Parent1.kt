package ir.beigirad.backbuttonhandler

import android.os.Bundle
import android.view.View
import ir.beigirad.backbuttonhandler.bases.ParentBaseFragment

/**
 * Created by farhad-mbp on 2/13/18.
 */
class Parent1 : ParentBaseFragment() {
    override val getLayout: Int
        get() = R.layout.parent1_layout
    override val container: Int
        get() = R.id.frag1_container

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openFrag(Child1())
    }

}