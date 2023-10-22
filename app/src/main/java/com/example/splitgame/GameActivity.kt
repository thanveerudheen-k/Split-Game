package com.example.splitgame

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.splitgame.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var player1LeftHand: ImageView
    private lateinit var player1RightHand: ImageView
    private lateinit var player2LeftHand: ImageView
    private lateinit var player2RightHand: ImageView
    private lateinit var player1Split: Button
    private lateinit var player2Split: Button
    private lateinit var messageTextView: TextView
    private var valueInPlayer1LeftHand = 1
    private var valueInPlayer1RightHand = 1
    private var valueInPlayer2LeftHand = 1
    private var valueInPlayer2RightHand = 1
    private var firstValue = 0
    private var firstMove  = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        player1LeftHand = binding.btnP1L
        player1RightHand = binding.btnP1R
        player2LeftHand = binding.btnP2L
        player2RightHand = binding.btnP2R
        player1Split = binding.btnSplitP1
        player2Split = binding.btnSplitP2
        messageTextView = binding.tvMessage

        player1Split.isVisible = false
        player2Split.isVisible = false
        player2LeftHand.isEnabled = false
        player2RightHand.isEnabled = false
        setMessage("Player 1's move")

        player1LeftHand.setOnClickListener{
            if(firstMove) {
                firstValue = valueInPlayer1LeftHand
                enableDisableBtn()
                firstMove = false
            }else {
                valueInPlayer1LeftHand = addMove(valueInPlayer1LeftHand)
                if(valueInPlayer1LeftHand == 0) player1LeftHand.isVisible = false
                displayHands()
                checkWin()
                if(isSplit(valueInPlayer1LeftHand, valueInPlayer1RightHand)) setVisibleSplitbtn()
                else  player1Split.isVisible = false
                firstMove = true
                setMessage("Player 1's move")
            }
        }
        player1RightHand.setOnClickListener{
            if(firstMove) {
                firstValue = valueInPlayer1RightHand
                enableDisableBtn()
                firstMove = false
            }else {
                valueInPlayer1RightHand = addMove(valueInPlayer1RightHand)
                if(valueInPlayer1RightHand == 0) player1RightHand.isVisible = false
                displayHands()
                checkWin()
                if(isSplit(valueInPlayer1RightHand, valueInPlayer1LeftHand)) setVisibleSplitbtn()
                else  player1Split.isVisible = false
                firstMove = true
                setMessage("Player 1's move")
            }
        }
        player1Split.setOnClickListener {
            val splitValue = valueInPlayer1LeftHand + valueInPlayer1RightHand
            if(splitValue == 2) {
                valueInPlayer1LeftHand = 1
                valueInPlayer1RightHand = 1
            }else{
                valueInPlayer1LeftHand = 2
                valueInPlayer1RightHand = 2
            }
            displayHands()
            player1LeftHand.isVisible = true
            player1RightHand.isVisible = true
        }
        player2LeftHand.setOnClickListener{
            if(firstMove) {
                firstValue = valueInPlayer2LeftHand
                enableDisableBtn(false)
                firstMove = false
            }else {
                valueInPlayer2LeftHand = addMove(valueInPlayer2LeftHand)
                if(valueInPlayer2LeftHand == 0) player2LeftHand.isVisible = false
                displayHands()
                checkWin()
                if(isSplit(valueInPlayer2LeftHand, valueInPlayer2RightHand)) setVisibleSplitbtn(false)
                else  player2Split.isVisible = false
                firstMove = true
                setMessage("Player 2's move")
            }
        }
        player2RightHand.setOnClickListener{
            if(firstMove) {
                firstValue = valueInPlayer2RightHand

                enableDisableBtn(false)
                firstMove = false
            }else {
                valueInPlayer2RightHand = addMove(valueInPlayer2RightHand)
                if(valueInPlayer2RightHand == 0) player2RightHand.isVisible = false
                displayHands()
                checkWin()
                if(isSplit(valueInPlayer2RightHand, valueInPlayer2LeftHand)) setVisibleSplitbtn(false)
                else  player2Split.isVisible = false
                firstMove = true
                setMessage("Player 2's move")
            }
        }
        player2Split.setOnClickListener {
            val splitValue = valueInPlayer2LeftHand + valueInPlayer2RightHand
            if(splitValue == 2) {
                valueInPlayer2LeftHand = 1
                valueInPlayer2RightHand = 1
            }else{
                valueInPlayer2LeftHand = 2
                valueInPlayer2RightHand = 2
            }
            displayHands()
            player2LeftHand.isVisible = true
            player2RightHand.isVisible = true
        }
    }

    private fun addMove(secondValue: Int): Int{
        var value: Int = firstValue + secondValue
        when(value) {
            5 -> value = 0
            6 -> value = 1
            7 -> value = 2
            8 -> value = 3
        }
        return value
    }

    private fun isSplit(splitingValue: Int, setValue: Int): Boolean{
        if (setValue == 0 && (splitingValue % 2 == 0) ) return true
        return false
    }

    private fun setVisibleSplitbtn(player1: Boolean = true) {
        if(player1) {
            player1Split.isEnabled = true
            player1Split.isVisible = true
        }
        else {
            player2Split.isEnabled = true
            player2Split.isVisible = true
        }
    }

    private fun displayHands() {
        when(valueInPlayer1LeftHand) {
            1 -> player1LeftHand.setImageResource(R.drawable.hand1)
            2 -> player1LeftHand.setImageResource(R.drawable.hand2)
            3 -> player1LeftHand.setImageResource(R.drawable.hand3)
            4 -> player1LeftHand.setImageResource(R.drawable.hand4)
        }
        when(valueInPlayer1RightHand) {
            1 -> player1RightHand.setImageResource(R.drawable.hand1)
            2 -> player1RightHand.setImageResource(R.drawable.hand2)
            3 -> player1RightHand.setImageResource(R.drawable.hand3)
            4 -> player1RightHand.setImageResource(R.drawable.hand4)
        }
        when(valueInPlayer2LeftHand) {
            1 -> player2LeftHand.setImageResource(R.drawable.hand1)
            2 -> player2LeftHand.setImageResource(R.drawable.hand2)
            3 -> player2LeftHand.setImageResource(R.drawable.hand3)
            4 -> player2LeftHand.setImageResource(R.drawable.hand4)
        }
        when(valueInPlayer2RightHand) {
            1 -> player2RightHand.setImageResource(R.drawable.hand1)
            2 -> player2RightHand.setImageResource(R.drawable.hand2)
            3 -> player2RightHand.setImageResource(R.drawable.hand3)
            4 -> player2RightHand.setImageResource(R.drawable.hand4)
        }
    }

    private fun enableDisableBtn(player1: Boolean = true) {
        if(player1){
            player1LeftHand.isEnabled = false
            player1RightHand.isEnabled = false
            player1Split.isVisible = false
            player2LeftHand.isEnabled = true
            player2RightHand.isEnabled = true
        }else{
            player2LeftHand.isEnabled = false
            player2RightHand.isEnabled = false
            player2Split.isVisible = false
            player1LeftHand.isEnabled = true
            player1RightHand.isEnabled = true
        }
    }

    private fun setMessage(msg: String) {
        messageTextView.text = msg
    }

    private fun checkWin() {
        if(valueInPlayer1LeftHand == 0 && valueInPlayer1RightHand == 0 ){
            messageTextView.text = R.string.p1win.toString()
            Toast.makeText(application, R.string.p1win.toString(), Toast.LENGTH_LONG).show()
            finish()
        }
        if(valueInPlayer2LeftHand == 0 && valueInPlayer2RightHand == 0 ){
            messageTextView.text = R.string.p2win.toString()
            Toast.makeText(application, R.string.p2win.toString(), Toast.LENGTH_LONG).show()
            finish()
        }
    }
}