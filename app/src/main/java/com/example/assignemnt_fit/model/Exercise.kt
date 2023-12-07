package com.example.assignemnt_fit.model

data class Exercise (
    val name: String,
    val sets: Int,
    val repetitions: Int,
    val duration: Int,
    val weight: Int,
    val dropSet: Boolean
){

}