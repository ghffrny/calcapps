package geeapps.com.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.dashboard.*
import net.objecthunter.exp4j.ExpressionBuilder

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        btnOne.setOnClickListener { appendOnExpresstion ("1", true) }
        btnTwo.setOnClickListener { appendOnExpresstion ("2", true) }
        btnThree.setOnClickListener { appendOnExpresstion ("3", true) }
        btnFour.setOnClickListener { appendOnExpresstion ("4", true) }
        btnFive.setOnClickListener { appendOnExpresstion ("5", true) }
        btnSix.setOnClickListener { appendOnExpresstion ("6", true) }
        btnSeven.setOnClickListener { appendOnExpresstion ("7", true) }
        btnEight.setOnClickListener { appendOnExpresstion ("8", true) }
        btnNine.setOnClickListener { appendOnExpresstion ("9", true) }
        btnZero.setOnClickListener { appendOnExpresstion ("0", true) }
        btnDot.setOnClickListener { appendOnExpresstion (".", true) }


        btnPlus.setOnClickListener { appendOnExpresstion ("+", false) }
        btnMin.setOnClickListener { appendOnExpresstion ("-", false) }
        btnMultiply.setOnClickListener { appendOnExpresstion ("*", false) }
        btnDevide.setOnClickListener { appendOnExpresstion ("/", false) }
        btnOpen.setOnClickListener { appendOnExpresstion ("(", false) }
        btnClose.setOnClickListener { appendOnExpresstion (")", false) }

        btnClear.setOnClickListener {
            txtExpression.text = ""
            txtResult.text = ""
        }

        btnDelete.setOnClickListener{
            val string = txtExpression.text.toString()
            if(string.isNotEmpty()){
                txtExpression.text = string.substring(0, string.length-1)
            }
            txtResult.text = ""
        }

        btnEqual.setOnClickListener{
            try{
                val expression = ExpressionBuilder(txtExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult =result.toLong()
                if(result == longResult.toDouble())
                    txtResult.text = longResult.toString()
                else
                    txtResult.text = result.toString()

            } catch (e:Exception){
                Log.d("Exception", "message : " + e.message)
            }
        }
    }

    fun appendOnExpresstion(string: String,canClear: Boolean){

        if(txtResult.text.isNotEmpty()){
            txtExpression.text = ""
        }

        if(canClear){
            txtResult.text = ""
            txtExpression.append(string)
        } else {
            txtExpression.append(txtResult.text)
            txtExpression.append(string)
            txtResult.text = ""
        }
    }
}