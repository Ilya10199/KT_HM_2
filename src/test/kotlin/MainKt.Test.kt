import org.junit.Test

import org.junit.Assert.*


class MainKtTest {

    @Test
    fun transferMastercardUpToTheLimit() {
        val card = "Mastercard"
        val previousAmount = 0
        val amount = 75_000

        val result = calculationCommission(card, previousAmount, amount)
        assertEquals(0, result)
    }

    @Test
    fun transferMastercardOverLimit() {
        val card = "Mastercard"
        val previousAmount = 0
        val amount = 150_000

        val result = calculationCommission(card, previousAmount, amount)
        assertEquals(470, result)
    }

    @Test
    fun testDayLimit() {
        val card = "Mastercard"
        val previousAmount = 0
        val amount = 250_000

        val result = calculationCommission(card, previousAmount, amount)
        assertEquals(-2, result)
    }

    @Test
    fun testMonthlyLimit() {
        val card = "Mastercard"
        val previousAmount = 750_000
        val amount = 50_000

        val result = calculationCommission(card, previousAmount, amount)
        assertEquals(-2, result)
    }

    @Test
    fun commissionByVisa() {
        val card = "Visa"
        val previousAmount = 0
        val amount = 150_000

        val result = calculationCommission(card, previousAmount, amount)
        assertEquals(1125, result)
    }

    @Test
    fun commissionByMir() {
        val card = "Mir"
        val previousAmount = 0
        val amount = 50_000

        val result = calculationCommission(card, previousAmount, amount)
        assertEquals(0, result)

    }
    @Test
    fun minCommissionByVisa() {
        val card = "Visa"
        val previousAmount = 0
        val amount = 1_000

        val result = calculationCommission(card, previousAmount, amount)
        assertEquals(35, result)

    }

    @Test
    fun testErrorTypeCard() {
        val card = "Ptfp"
        val previousAmount = 0
        val amount = 150_000

        val result = calculationCommission(card, previousAmount, amount)
        assertEquals(-1, result)

    }
}