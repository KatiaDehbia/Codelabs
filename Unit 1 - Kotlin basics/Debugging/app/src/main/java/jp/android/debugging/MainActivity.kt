package jp.android.debugging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlin.concurrent.thread

private const val TAG : String = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
         Getting the TextView and change its text to "Hello, debugging!" before the call to setContentView()
         This introduces a NullPointerException: findViewById(R.id.hello_world) must not be null
        */
        // val helloTextView: TextView = findViewById(R.id.hello_world)
        // helloTextView.text = "Hello, debugging!"

        setContentView(R.layout.activity_main)
        val helloTextView: TextView = findViewById(R.id.hello_world)
        helloTextView.text = "Hello, debugging!"

        /* This function introduces an an ArithmeticException: divide by zero */
        //division()

        logging()
        displayDenominator()
    }

    fun displayDenominator() {
        val numerator = 60
        var denominator = 4
        thread(start=true) {
            /* The loop only repeat 4 times to avoid a crash*/
            repeat(4) {
                Log.v(TAG, "$denominator")
                // Set division_textview text to the quotient.
                runOnUiThread { findViewById<TextView>(R.id.division_textview).setText("${numerator / denominator}") }
                // Wait for 1 second
                Thread.sleep(1000L)
                denominator--
            }
        }
    }

    fun division() {
        val numerator = 60
        var denominator = 4
        repeat(5) {
            Log.v(TAG, "${numerator / denominator}")
            Log.v(TAG, "$denominator")
            denominator--
        }
    }

    fun logging() {
        Log.wtf(TAG, "What a Terrible Failure: logging things that you're certain will never occur, hence the name")
        Log.e(TAG, "ERROR: a serious error like an app crash")
        Log.w(TAG, "WARN: warns about the potential for serious errors")
        Log.i(TAG, "INFO: reporting technical information, such as an operation succeeding")
        Log.d(TAG, "DEBUG: reporting technical information useful for debugging")
        Log.v(TAG, "VERBOSE: more verbose than DEBUG logs")
    }
}