package example.constraintsetzvaluebug

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var translationZ = -100f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { toggle() }
    }


    private fun toggle(){
        translationZ = -translationZ
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_main)
        constraintSet.setTranslationZ(R.id.background, translationZ)
        /*
            will be dismissed if API < 21 - look at the source of ConstraintSet applyToInternal() :

             if (VERSION.SDK_INT >= 21) {
                view.setTranslationZ(constraint.translationZ);
                if (constraint.applyElevation) {
                    view.setElevation(constraint.elevation)
                }
             }

         */
        constraintSet.applyTo(rootlayout)

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP){
            workaroundToggle()
        }

    }

    /**
     * the latest added view in constraintlayout is the latest rendered view (on top of all others).
     * using this as a workaround for messing with z axis
     */
    private fun workaroundToggle(){
        val container = rootlayout
        val imageView = if(translationZ > 0) background else foreground
        container.removeView(imageView )
        container.addView(imageView )
    }
}
