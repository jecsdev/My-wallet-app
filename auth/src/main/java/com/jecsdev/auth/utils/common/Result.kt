package com.jecsdev.auth.utils.common

/**
 * Represents the result of a data operation.
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}