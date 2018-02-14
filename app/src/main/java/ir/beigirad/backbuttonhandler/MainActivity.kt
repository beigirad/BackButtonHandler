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
    val TAG = "MainActivity"


    private var fragMan = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager.beginTransaction().add(R.id.main_container, showingFragTag, showingFragTag.javaClass.simpleName).commit()

        showFragment(showingFragTag)
        btm_nav.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.action_settings1 -> showFragment(FragTag.Parent1)

                    R.id.action_settings2 -> showFragment(FragTag.Parent2)

                    R.id.action_settings3 -> showFragment(FragTag.Parent3)

                    else -> {

                    }
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

        if (fragMan.findFragmentByTag(fragTag.name) == null) {
            var fragment = when (fragTag) {
                FragTag.Parent1 -> Parent1()
                FragTag.Parent2 -> Parent2()
                FragTag.Parent3 -> Parent3()
            }
            fragMan.beginTransaction().add(R.id.main_container, fragment, fragTag.name).commit()
        } else {
            fragMan.beginTransaction().show(fragMan.findFragmentByTag(fragTag.name)).commit()
        }


        if (showingFragTag != null && !showingFragTag.equals(fragTag)) {
            fragMan.beginTransaction().hide(fragMan.findFragmentByTag(showingFragTag.name)).commit()
        }
        showingFragTag = fragTag

        if (fromStack)
            btm_nav.menu.findItem(when (showingFragTag) {
                FragTag.Parent1 -> R.id.action_settings1
                FragTag.Parent2 -> R.id.action_settings2
                FragTag.Parent3 -> R.id.action_settings3
            }).isChecked = true

        Toast.makeText(this, showingFragTag.name, Toast.LENGTH_SHORT).show()

        if (!fromStack)
            stack.push(showingFragTag.name)
    }

    var stack = Stack<String>()

    override fun onBackPressed() {
        if ((fragMan.findFragmentByTag(showingFragTag.name) as ParentBaseFragment).back()) {
            Log.i(TAG, "parent handles back")
            if (stack.count() > 1) {
                stack.pop()
                showFragment(FragTag.valueOf(stack.peek()!!), true)
            } else
                super.onBackPressed()
        }
    }


//region ww
//    override fun onUserLeaveHint() {
//        super.onUserLeaveHint()
//        Log.i(TAG, "onUserLeaveHint")
//    }
//
//    override fun onAttachedToWindow() {
//        super.onAttachedToWindow()
//        Log.i(TAG, "onAttachedToWindow")
//    }
//
//    override fun onAttachFragment(fragment: android.support.v4.app.Fragment?) {
//        super.onAttachFragment(fragment)
//        Log.i(TAG, "onAttachFragment4")
//    }
//
//    override fun onResumeFragments() {
//        super.onResumeFragments()
//        Log.i(TAG, "onResumeFragments")
//    }
//
//    override fun onAttachFragment(fragment: Fragment?) {
//        super.onAttachFragment(fragment)
//        Log.i(TAG, "onAttachFragment")
//    }
//
//    override fun onDetachedFromWindow() {
//        super.onDetachedFromWindow()
//        Log.i(TAG, "onDetachedFromWindow")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.i(TAG, "onRestart")
//    }
//
//    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
//        super.onSaveInstanceState(outState, outPersistentState)
//        Log.i(TAG, "onSaveInstanceState")
//    }
//
//    override fun onStateNotSaved() {
//        super.onStateNotSaved()
//        Log.i(TAG, "onStateNotSaved")
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration?) {
//        super.onConfigurationChanged(newConfig)
//        Log.i(TAG, "onConfigurationChanged")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.i(TAG, "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.i(TAG, "onPause")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.i(TAG, "onStart")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.i(TAG, "onStop")
//    }
//endregion
}
