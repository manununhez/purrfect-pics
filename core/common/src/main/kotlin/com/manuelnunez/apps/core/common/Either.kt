package com.manuelnunez.apps.core.common

sealed class Either<out L, out R> {
  data class Success<out L>(val value: L) : Either<L, Nothing>()

  data class Error<out R>(val value: R) : Either<Nothing, R>()
}

fun <T> eitherSuccess(data: T) = Either.Success(data)

fun <T> eitherError(error: T) = Either.Error(error)

fun <S, E, C> Either<S, E>.fold(success: (S) -> C, error: (E) -> C): C =
    when (this) {
      is Either.Success -> success(value)
      is Either.Error -> error(value)
    }
