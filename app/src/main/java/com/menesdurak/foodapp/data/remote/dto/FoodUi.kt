package com.menesdurak.foodapp.data.remote.dto

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class FoodUi(
    val name: String,
    val price: String,
    val id: String,
    val image: String,
    var isFavorite: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(id)
        parcel.writeString(image)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FoodUi> {
        override fun createFromParcel(parcel: Parcel): FoodUi {
            return FoodUi(parcel)
        }

        override fun newArray(size: Int): Array<FoodUi?> {
            return arrayOfNulls(size)
        }
    }
}