package com.hyperswitch.common.types;

import io.vavr.control.Either;
import java.util.Objects;

/**
 * Result type for functional error handling (similar to Rust's Result<T, E>)
 * Uses Vavr's Either for type-safe error handling
 */
public final class Result<T, E> {
    private final Either<E, T> either;

    private Result(Either<E, T> either) {
        this.either = either;
    }

    public static <T, E> Result<T, E> ok(T value) {
        return new Result<>(Either.right(value));
    }

    public static <T, E> Result<T, E> err(E error) {
        return new Result<>(Either.left(error));
    }

    public boolean isOk() {
        return either.isRight();
    }

    public boolean isErr() {
        return either.isLeft();
    }

    public T unwrap() {
        return either.get();
    }

    public E unwrapErr() {
        return either.getLeft();
    }

    public <U> Result<U, E> map(java.util.function.Function<T, U> mapper) {
        return new Result<>(either.map(mapper));
    }

    public <U> Result<T, U> mapErr(java.util.function.Function<E, U> mapper) {
        return new Result<>(either.mapLeft(mapper));
    }

    public <U> Result<U, E> andThen(java.util.function.Function<T, Result<U, E>> mapper) {
        if (isOk()) {
            return mapper.apply(unwrap());
        }
        return Result.err(unwrapErr());
    }

    public Either<E, T> getEither() {
        return either;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result<?, ?> result = (Result<?, ?>) o;
        return Objects.equals(either, result.either);
    }

    @Override
    public int hashCode() {
        return Objects.hash(either);
    }
}

