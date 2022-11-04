package com.example.myassignmentgithub.network.exceptions

import java.io.IOException

class ApiNetworkException(ioException: IOException) : ThrowApiException(ioException)
