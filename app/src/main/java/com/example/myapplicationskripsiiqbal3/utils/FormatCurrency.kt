package com.example.myapplicationskripsiiqbal3.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object FormatCurrency {

    fun format(
        amount: Double,
        thousandsSeparator: String = ",",
        decimalSymbol: String = ".",
        use2DigitsDeci: Boolean = false
    ): String {
        val formatRp = DecimalFormatSymbols()
        formatRp.monetaryDecimalSeparator = decimalSymbol.single()
        formatRp.groupingSeparator = thousandsSeparator.single()

        val myCurrencyFormat = DecimalFormat(if (use2DigitsDeci) "#,##0.00" else "#,###")
        myCurrencyFormat.decimalFormatSymbols = formatRp
        myCurrencyFormat.isGroupingUsed = (!(thousandsSeparator == "" && decimalSymbol == ""))

        return myCurrencyFormat.format(amount.toLong())
    }

    fun formatRp(
        amount: Double,
        thousandsSeparator: String = ",",
        decimalSymbol: String = ".",
        use2DigitsDeci: Boolean = false,
        prefix : String = ""
    ): String {
        val formatRp = DecimalFormatSymbols()
        formatRp.monetaryDecimalSeparator = decimalSymbol.single()
        formatRp.groupingSeparator = thousandsSeparator.single()

        val myCurrencyFormat = DecimalFormat(if (use2DigitsDeci) "#,##0.00" else "#,###")
        myCurrencyFormat.decimalFormatSymbols = formatRp
        myCurrencyFormat.isGroupingUsed = (!(thousandsSeparator == "" && decimalSymbol == ""))

        var temp = amount.toLong()
        var symbol = "Rp "
        if (temp < 0) {
            temp *= -1
            symbol = "-Rp "
        }

        return symbol + myCurrencyFormat.format(temp)
    }

    fun formatRpPlus(
        amount: Double,
        thousandsSeparator: String = ",",
        decimalSymbol: String = ".",
        use2DigitsDeci: Boolean = false,
        prefix : String = ""
    ): String {
        val formatRp = DecimalFormatSymbols()
        formatRp.monetaryDecimalSeparator = decimalSymbol.single()
        formatRp.groupingSeparator = thousandsSeparator.single()

        val myCurrencyFormat = DecimalFormat(if (use2DigitsDeci) "#,##0.00" else "#,###")
        myCurrencyFormat.decimalFormatSymbols = formatRp
        myCurrencyFormat.isGroupingUsed = (!(thousandsSeparator == "" && decimalSymbol == ""))

        var newPrefix = prefix

        var temp = amount.toLong()
        if (temp < 0) {
            temp *= -1
            newPrefix = "-Rp "
        }

        return newPrefix + "Rp" + myCurrencyFormat.format(temp)

    }

    /**
     * contoh format yang dapat di convert ke double, sbb :
     * - 1,000,000
     * - 1,000,000.35
     * - Rp100,000
     * - Rp1,000,000.35
     */
    fun formattedStringToDouble(nominal: String?): Double {

        if (!nominal.isNullOrEmpty() && nominal.all { a -> a.isDigit() || a.toString() == "," || a.toString() == "." })
            return nominal.replace("Rp", "").replace(",", "").toDouble()
        return 0.0
    }

    /**
     * untuk menghilakan 0 dibelakang koma, misal :
     * - 3.5 -> 3.5
     * - 3.0 -> 3
     */
    fun formatDecimalOrNot(value: Double): String {

        if (value <= 0.0)
            return ""

        return if ((value).rem(1).equals(0.0)) value.toInt().toString() else  value.toString()
    }
}