package ir.beigirad.backbuttonhandler

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import ir.beigirad.backbuttonhandler.bases.ParentBaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName


    private var manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(showingFragTag)

        btm_nav.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.tab1 -> showFragment(FragTag.Parent1)

                    R.id.tab2 -> showFragment(FragTag.Parent2)

                    R.id.tab3 -> showFragment(FragTag.Parent3)
                }
                return true
            }
        })
    }

    enum class FragTag {
        Parent1,
        Parent2,
        Parent3
    }

    var showingFragTag: FragTag = FragTag.Parent1
    fun showFragment(fragTag: FragTag, fromStack: Boolean = false) {

        if (manager.findFragmentByTag(fragTag.name) == null) {
            var fragment = when (fragTag) {
                FragTag.Parent1 -> Parent1()
                FragTag.Parent2 -> Parent2()
                FragTag.Parent3 -> Parent3()
            }
            manager.beginTransaction().add(R.id.main_container, fragment, fragTag.name).commit()
        } else {
            manager.beginTransaction().show(manager.findFragmentByTag(fragTag.name)).commit()
        }


        if (showingFragTag != null && !showingFragTag.equals(fragTag)) {
            manager.beginTransaction().hide(manager.findFragmentByTag(showingFragTag.name)).commit()
        }
        showingFragTag = fragTag

        if (fromStack)
            btm_nav.menu.findItem(when (showingFragTag) {
                FragTag.Parent1 -> R.id.tab1
                FragTag.Parent2 -> R.id.tab2
                FragTag.Parent3 -> R.id.tab3
            }).isChecked = true

        Toast.makeText(this, showingFragTag.name, Toast.LENGTH_SHORT).show()

        if (!fromStack)
            stack.push(showingFragTag.name)
    }

    var stack = Stack<String>()
    override fun onBackPressed() {
        if ((manager.findFragmentByTag(showingFragTag.name) as ParentBaseFragment).back()) {
            Log.i(TAG, "parent handles back")
            if (stack.count() > 1) {
                stack.pop()
                showFragment(FragTag.valueOf(stack.peek()!!), true)
            } else
                super.onBackPressed()
        }
    }
}
