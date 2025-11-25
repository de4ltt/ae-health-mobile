package feo.health.mapper

interface IMapper<First, Second> {
    fun First.toSecond(): Second
    fun Second.toFirst(): First
}