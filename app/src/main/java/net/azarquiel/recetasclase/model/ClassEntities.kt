package net.azarquiel.recetasclase.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "categoria")
data class Categoria(@PrimaryKey
                     var id: Int,
                     var nombre:String):Serializable

@Entity(tableName = "receta", foreignKeys = [ForeignKey(entity = Categoria::class, parentColumns = arrayOf("id"), childColumns = arrayOf("categoria"))])
data class Receta(@PrimaryKey
                  var id: Int,
                  var categoria:Int,
                  var titulo:String,
                  var foto:String,
                  var pasos:String,
                  var ingredientes:String,
                  var comensales:String,
                  var dificultad:String,
                  var precio:String,
                  var tiempo:String):Serializable