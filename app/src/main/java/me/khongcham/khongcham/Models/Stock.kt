package me.khongcham.khongcham.Models

data class Stock(val id: Int
                 , var name: String
                 , var imageUrl: String?
                 , var pricePerPack: Double
                 , var pricePerUnit: Double
                 , var unitPerPack: Int
                 , var unitName: String
)