package com.aravindh.cleancode.programs

fun main() {

    //val nums = IntArray(3) //arrayOf(2,7,11,15)
    val nums = intArrayOf(3,3)
    val target = 6

    val output : IntArray = twoSum(nums, target)
    println(output.toList())

}

fun twoSum(nums: IntArray, target: Int) : IntArray {
    val array = IntArray(2)

    for(i in 0 until nums.size) {
        for(j in i+1 until nums.size) {
            val x = nums[i]
            val y = nums[j]

            println("$i $j")
            val add = x + y
            if(add == target) {
                //println("$i $j")
                array[0] = i
                array[1] = j
                return array
            }
        }
    }
    return array
}