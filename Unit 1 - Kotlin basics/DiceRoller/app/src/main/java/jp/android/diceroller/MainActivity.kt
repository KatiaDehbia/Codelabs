package jp.android.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlin.random.Random

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    lateinit var diceImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage = findViewById(R.id.dice_image)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        // Do a dice roll when the app starts
        rollDice()
    }

    /**
     * Create new Dice object with 6 sides
     * Then roll the dice and update the screen with the result.
     */
    fun rollDice() {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val randomInt = dice.roll()
        val drawableRessourceId = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableRessourceId)
        diceImage.contentDescription = randomInt.toString()
    }
}

class Dice(private val numSides: Int) {
    // Do a random dice roll and return the result.
    fun roll(): Int {
        return (1..numSides).random()
    }
}