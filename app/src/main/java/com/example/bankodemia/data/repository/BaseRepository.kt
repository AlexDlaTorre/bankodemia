package com.example.bankodemia.data.repository

abstract class BaseRepository {
    protected abstract fun parseCustomError(responseMessage: String,
                                            responseCode: Int,
                                            errorBOdyJson: String)
}