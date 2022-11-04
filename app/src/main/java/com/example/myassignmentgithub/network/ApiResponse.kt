package com.example.myassignmentgithub.network

sealed class ApiResponse<DATA_TYPE> {
    class Success<DATA_TYPE>(val data: DATA_TYPE) : ApiResponse<DATA_TYPE>()
    class Failure<DATA_TYPE>(val error: Throwable) : ApiResponse<DATA_TYPE>()
}
