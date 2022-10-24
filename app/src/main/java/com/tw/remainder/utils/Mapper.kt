package com.tw.remainder.utils

interface Mapper<E,T> {
    fun from(e: E):T
}