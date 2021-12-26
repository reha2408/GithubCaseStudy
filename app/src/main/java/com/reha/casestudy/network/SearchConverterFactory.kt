package com.reha.casestudy.network

import com.reha.casestudy.feature.github.data.request.SearchRequest
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class SearchConverterFactory : Converter.Factory() {
    override fun stringConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit) =
        if (type == SearchRequest::class.java) {
            Converter<Any, String> { (it as SearchRequest).name.toString() }
        } else {
            super.stringConverter(type, annotations, retrofit)
        }
}
