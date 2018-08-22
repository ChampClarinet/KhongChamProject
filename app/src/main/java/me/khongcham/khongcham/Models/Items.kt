package me.khongcham.khongcham.Models

data class Items(val id: Int, var name: String, var imageUrl: String?, var pricePerUnit: Double, var quantity: Int) {

    constructor(id: Int, name: String, pricePerUnit: Double, quantity: Int) : this(id, name, null, pricePerUnit, quantity)

    fun getTotalPrice(): Double {
        return pricePerUnit * quantity
    }

}