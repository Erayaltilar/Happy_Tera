package com.happy_tera.core

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Loading<T>() : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(val errorMessage: String?) : Resource<T>()
}