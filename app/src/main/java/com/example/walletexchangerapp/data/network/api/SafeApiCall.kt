package com.example.walletexchangerapp.data.network.api

import com.example.walletexchangerapp.domain.model.ResponseResult
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>) = try {
    val response = call.invoke()
    if (response.isSuccessful) {
        ResponseResult.Success(response.body())
    } else {
        ResponseResult.Failure(message = response.message(), code = response.code())
    }
} catch (exception: IOException) {
    ResponseResult.Error(exception)
}