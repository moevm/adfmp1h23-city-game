package com.example.adfmp_settings

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.*
import android.view.inputmethod.InputMethodManager
import android.widget.*

class GameActivity : AppCompatActivity() {
    var filledTextField = false
    private val verifier: WordVerifier = LocalMemoryWordVerifier(globalTestDB)
    private val bot = Bot(selectNumberFromDb(globalTestDB, 50), verifier)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide() ?: Log.d("MANUAL","No ActionBar")
        findViewById<View>(R.id.buttonConcede).setOnClickListener { this.finish() }
        findViewById<View>(R.id.buttonHome).setOnClickListener { this.finish() }
        val buttonSend = findViewById<ImageButton>(R.id.buttonSend)
        findViewById<EditText>(R.id.wordInput).addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if(s.isNullOrBlank()) {
                    filledTextField = false
                    buttonSend.setImageResource(R.drawable.send_disabled)
                }
                else {
                    filledTextField = true
                    buttonSend.setImageResource(R.drawable.send)
                }
            }
        })
        val editText = findViewById<EditText>(R.id.wordInput)
        findViewById<ImageButton>(R.id.buttonSend).setOnClickListener {
            if(!filledTextField) return@setOnClickListener
            val input = editText.text
                .trim()
                .toString()
                .replace(Regex("\\s+"), " ")
            processPlayerTurn(input)

        }
    }

    fun processPlayerTurn(input: String) {
        val verdict = verifier.verify(input)
        val errorMessage = verdict.errorMessage()
        if(errorMessage != null) {
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(applicationContext, errorMessage, duration)
            toast.show()
            return
        }
        appendWordToLog(
            input,
            "Игрок 1",
            false
        )
        val botAnswer = bot.reply(input)
        if(botAnswer == null) {
            showEndGameDialog("Победа!", "Робот сдался")
            return
        }
        appendWordToLog(
            botAnswer,
            "Робот",
            true
        )
    }

    fun appendWordToLog(word: String, playerName: String, flushRight: Boolean) {
        val logLayout = findViewById<LinearLayout>(R.id.gameLog)
        val scale = resources.displayMetrics.density

        val wordView = TextView(this)
        wordView.text = word
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.gravity = if(flushRight) Gravity.END else Gravity.START
        wordView.layoutParams = layoutParams
        val paddingTop = wordView.paddingTop
        Log.d("LAYOUTS", "paddingTop = $paddingTop")
        val paddingBottom = wordView.paddingBottom
        wordView.setPaddingRelative(
            dpToPixels(10, scale), paddingTop, dpToPixels(10, scale), paddingBottom
        )
        wordView.setTextColor(Color.BLACK)
        wordView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30F)
        wordView.gravity = if(flushRight) Gravity.END else Gravity.START

        logLayout.addView(wordView)

        val playerNameView = TextView(this)
        playerNameView.text = playerName
        val nameLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        nameLayoutParams.gravity = if(flushRight) Gravity.END else Gravity.START
        playerNameView.layoutParams = nameLayoutParams
        playerNameView.setPaddingRelative(
            dpToPixels(10, scale),
            playerNameView.paddingTop,
            dpToPixels(10, scale),
            playerNameView.paddingBottom
        )
        playerNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15F)
        playerNameView.gravity = if(flushRight) Gravity.END else Gravity.START

        logLayout.addView(playerNameView)

        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
        findViewById<EditText>(R.id.wordInput).setText("")
        val nestedScrollView = findViewById<NestedScrollView>(R.id.scrollView)
        nestedScrollView.post(Runnable {
            run() {
                nestedScrollView.fullScroll(View.FOCUS_DOWN)
            }
        })
    }

    fun showEndGameDialog(title: String, message: String) {
        val modal = AlertDialog.Builder(this)
        modal.setTitle(title)
        modal.setMessage(message)
        modal.setPositiveButton("В меню") { dialog, button ->
            this.finish()
        }
        modal.setCancelable(false)
        modal.show()
    }
}