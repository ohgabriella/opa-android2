package com.example.opa_android2.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produto")
class Produto() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var nome: String? = null
    var preco: String? = null
    var quantidade: String? = null
    var descricao: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        nome = parcel.readString()
        preco = parcel.readString()
        quantidade = parcel.readString()
        descricao = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(nome)
        parcel.writeString(preco)
        parcel.writeString(quantidade)
        parcel.writeString(descricao)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Nome: ${nome} \nPreço: ${preco} \nEstoque: ${quantidade} \nDescrição: ${descricao}";
    }

    companion object CREATOR : Parcelable.Creator<Produto> {
        override fun createFromParcel(parcel: Parcel): Produto {
            return Produto(parcel)
        }

        override fun newArray(size: Int): Array<Produto?> {
            return arrayOfNulls(size)
        }
    }
}