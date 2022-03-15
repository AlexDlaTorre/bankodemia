package com.example.bankodemia.core.utils

object HttpUrlConnection {
    // HTTP Status-Code 504: Gateway Timeout.
    public final val HTTP_GATEWAY_TIMEOUT: Int = 504
    // HTTP Status-Code 503: Service Unavailable.
    public final val HTTP_UNAVAILABLE: Int = 503
    // HTTP Status-Code 500: Internal Server Error.
    public final val HTTP_SERVER_ERROR: Int = 500
    // HTTP Status-Code 404: Not Found.
    public final val HTTP_NOT_FOUND: Int = 404
    // HTTP Status-Code 403: Forbidden.
    public final val HTTP_FORBIDDEN: Int = 403
    // HTTP Status-Code 401: Unauthorized.
    public final val HTTP_UNAUTHORIZED: Int = 401
    // HTTP Status-Code 400: Bad Request.
    public final val HTTP_BAD_REQUEST: Int = 400
    // HTTP Status-Code 200: Http Ok.
    public final val HTTP_OK: Int = 200
    // HTTP Status-Code 201: Http Created.
    public final val HTTP_CREATED: Int = 201
    // HTTP Status-Code 202: Http Accepted.
    public final val HTTP_ACCEPTED: Int = 202
}