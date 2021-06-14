package com.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class DemoServerEndepoin: DemoServerServiceGrpcKt.DemoServerServiceCoroutineImplBase() {
    override suspend fun saveUser(request: SaveUserRequest): UserResponse {
        return UserResponse.newBuilder()
            .setId(1)
            .setName(request.name)
            .setLastName(request.lastName)
            .build()
    }

    override fun saveUserStream(requests: Flow<SaveUserRequest>): Flow<UserResponse> = flow{
        var id = 1
        requests.collect {
            println("Salvando usuário...")
            emit(
                UserResponse.newBuilder()
                    .setId(id++)
                    .setName(it.name)
                    .build()
            )
            println("Concluído...")

        }
    }
}