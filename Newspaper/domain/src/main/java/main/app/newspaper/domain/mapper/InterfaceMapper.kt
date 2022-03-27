package main.app.newspaper.domain.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}