package com.example.core.platform

import io.reactivex.rxjava3.core.Observable

abstract class NetworkInfo {
    abstract fun isConnected(): Observable<Boolean>
}

class NetworkInfoImpl: NetworkInfo() {
    override fun isConnected(): Observable<Boolean> {
        return Observable.fromArray(false)
    }
}