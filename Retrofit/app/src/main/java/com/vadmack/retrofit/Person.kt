package com.vadmack.retrofit

class Person(
    var name: String,
    var gender: String,
    var culture: String,
    var born: String
) {
    override fun toString(): String {
        return "Person(name='$name', gender='$gender', culture='$culture', born='$born')"
    }
}

