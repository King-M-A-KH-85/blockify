package ir.blockify.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RouterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // check account
//        if (Application.getPublicKeys().getBoolean("logging", false))
//            goActivity(MainActivity::class.java)
//        else
//            goActivity(SingActivity::class.java)

        goActivity(AboutActivity::class.java)
        super.onCreate(savedInstanceState)
    }

    private fun goActivity(activity: Class<*>) {
        startActivity(Intent(this@RouterActivity, activity))
        finish()
    }
}