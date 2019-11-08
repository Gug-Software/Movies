package com.jk.practice.movies.utils.mapper

/**
 * This abstract class is the base for the domain´s DomainMapper
 * @param E The received object for Map
 * @param T The mapped output Object
 */
abstract class Mapper<in E, T> {

    /**
     * Abstract method for map the received object
     */
    abstract fun mapFromTo(from: E): T

}