/*
 * Copyright (c) 2019 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish, 
 * distribute, sublicense, create a derivative work, and/or sell copies of the 
 * Software in any work that is designed, intended, or marketed for pedagogical or 
 * instructional purposes related to programming, coding, application development, 
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works, 
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.tipper.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.raywenderlich.android.tipper.R
import com.raywenderlich.android.tipper.model.BillModel
import com.raywenderlich.android.tipper.model.TipCalculator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  // declare and initialise the tip calculator
  private val calculator = TipCalculator()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setInputValues()
    setButtonClickListener()
  }

  private fun setInputValues() {
    // set default input values
    tip_percentage_input.setText("20")
    number_of_people_input.setText("4")

  }

  private fun setButtonClickListener() {
    // set click listener on the Calculate Tip button
    tip_calculator_button.setOnClickListener {
      calculateTip()
    }
  }

  private fun calculateTip() {
    // add code here to calculate the tip
    val bilAmount = bill_amount_input.text.toString().toDoubleOrNull()
    val tipPercentage = tip_percentage_input.text.toString().toIntOrNull()
    val numberOfPeople = number_of_people_input.text.toString().toIntOrNull()

    if (bilAmount != null && tipPercentage != null && numberOfPeople != null) {
      val bill = calculator.calculate(bilAmount, tipPercentage, numberOfPeople)
      displayBillDetails(bill)
    }
  }

  // add the function to display calculated values.
  private fun displayBillDetails(bill: BillModel) {
    tip_amount.text =     application.getString(R.string.amount_in_dollars, bill.tipAmount)
    total_amount.text = application.getString(R.string.amount_in_dollars, bill.totalAmount)
    amount_per_person.text = application.getString(R.string.amount_in_dollars, bill.amountPerPerson)
  }

}

