package ir.beigirad.backbuttonhandler

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import ir.beigirad.backbuttonhandler.bases.ParentBaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"


    private var fragMan = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager.beginTransaction().add(R.id.main_container, showingFrag, showingFrag.javaClass.simpleName).commit()

        showFragment(showingFrag)
        btm_nav.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.action_settings1 -> showFragment(Parent1())

                    R.id.action_settings2 -> showFragment(Parent2())
                    R.id.action_settings3 -> showFragment(Parent3())
                    else -> {

                    }
                }
                return true
            }
        })
    }

    var showingFrag: ParentBaseFragment = Parent1()
    fun showFragment(fragment: ParentBaseFragment) {
        Log.i(TAG, fragment.javaClass.simpleName)
        if (fragMan.findFragmentByTag(fragment.javaClass.simpleName) == null) {
            fragMan.beginTransaction().add(R.id.main_container, fragment, fragment.javaClass.simpleName).addToBackStack(fragment.javaClass.simpleName).commit()
        } else {
            fragMan.beginTransaction().show(fragMan.findFragmentByTag(fragment.javaClass.simpleName)).commit()
        }

        if (showingFrag != null && !showingFrag!!.equals(fragment)) {
            fragMan.beginTransaction().hide(fragMan.findFragmentByTag(showingFrag!!.javaClass.simpleName)).commit()
        }
        showingFrag = fragment
        Log.i(TAG, "showing" + showingFrag!!.javaClass.simpleName)

    }

    override fun onBackPressed() {
        if (showingFrag.back())
            if (fragMan.backStackEntryCount > 1)
                fragMan.popBackStack()
            else
                super.onBackPressed()
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
