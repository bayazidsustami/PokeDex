package com.example.pokedex.data.datasource.remote.utils

import com.example.pokedex.common.ApiResponse
import com.example.pokedex.common.Resource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NetworkResource @Inject constructor() {

    inline fun <T, R> fetchFromServer(
        crossinline fetch: suspend () -> Flow<ApiResponse<T>>,
        crossinline mapper: (T) -> R,
        noinline onSuccess: ((R) -> Unit)? = null,
        noinline onError: ((String) -> Unit)? = null
    ): Flow<Resource<R>> {
        return flow {
            emit(Resource.Loading)
            when (val apiResponse = fetch().first()) {
                is ApiResponse.Success -> {
                    if (onSuccess != null) {
                        onSuccess(mapper(apiResponse.data))
                    }
                    emit(Resource.Success(mapper(apiResponse.data)))
                }
                is ApiResponse.Fail -> {
                    if (onError != null) {
                        onError(apiResponse.message)
                    }
                    emit(Resource.Error(apiResponse.message, apiResponse.errorCode))
                }
            }
        }
    }

    inline fun <ResultType, RequestType> networkBoundResource(
        crossinline loadFromDb: () -> Flow<ResultType>,
        crossinline createCall: () -> Flow<ApiResponse<RequestType>>,
        crossinline shouldFetch: (ResultType?) -> Boolean = {true},
        crossinline saveCallResult : suspend (RequestType) -> Unit
    ): Flow<Resource<ResultType>> {
        return flow {
            emit(Resource.Loading)
            val dbSource = loadFromDb().first()
            if (shouldFetch(dbSource)){
                emit(Resource.Loading)
                when(val apiResponse = createCall().first()){
                    is ApiResponse.Success -> {
                        saveCallResult(apiResponse.data)
                        emitAll(loadFromDb().map { Resource.Success(it) })
                    }
                    is ApiResponse.Fail -> {
                        emit(Resource.Error(apiResponse.message, apiResponse.errorCode))
                    }
                }
            }else{
                emitAll(loadFromDb().map { Resource.Success(it) })
            }
        }
    }
}
