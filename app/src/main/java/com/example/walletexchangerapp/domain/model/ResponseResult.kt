package com.example.walletexchangerapp.domain.model

import java.lang.Exception

sealed class ResponseResult<out T> {
    class Success<T>(val data: T?) : ResponseResult<T>()
    class Failure(val message: String, val code: Int) : ResponseResult<Nothing>()
    class Error(val exception: Exception?) : ResponseResult<Nothing>()
}