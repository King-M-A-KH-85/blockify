package ir.blockify.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import ir.blockify.app.BlockifyApp

class RoutingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        if (getSharedPreferences("app_setting", MODE_PRIVATE).getBoolean(
//                "logging",
//                false
//            )
//        ) {
            startActivity(Intent(BlockifyApp.INSTANCE, MainActivity::class.java))
            finish()
//        } else {
//            startActivity(Intent(BlockifyApp.INSTANCE, LogActivity::class.java))
//            finish()
//        }
        super.onCreate(savedInstanceState)

    }
}
