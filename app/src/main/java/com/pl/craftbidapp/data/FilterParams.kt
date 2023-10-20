package com.pl.craftbidapp.data


class FilterParams(val title: String? = null,
                   val advertiserSurname: String? = null,
                   val winnerName: String? = null,
                   val tags: List<String>? = null,
                   val minPrice: Double? = null,
                   val maxPrice: Double? = null,
                   val pageable: FilterParamPageable? = null,)

