package com.colinodell.advent2023

// From https://github.com/Kroppeb/AdventOfCodeSolutions2/blob/master/solutions/src/logging.kt
private var _logIndex = 0
fun <T> T.log(meta: String = "") = also { println("%03d %03d:\t$meta\t%s".format(_logIndex / 1000, _logIndex++ % 1000, this)) }
infix fun <T> T.log(meta: Any?): T = this.log(meta.toString())
